/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
<<<<<<< HEAD
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package scala.concurrent.forkjoin;

import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

/**
 * A thread managed by a {@link ForkJoinPool}, which executes
 * {@link ForkJoinTask}s.
 * This class is subclassable solely for the sake of adding
 * functionality -- there are no overridable methods dealing with
 * scheduling or execution.  However, you can override initialization
 * and termination methods surrounding the main task processing loop.
 * If you do create such a subclass, you will also need to supply a
 * custom {@link ForkJoinPool.ForkJoinWorkerThreadFactory} to use it
 * in a {@code ForkJoinPool}.
 *
 * @since 1.7
 * @author Doug Lea
 */
public class ForkJoinWorkerThread extends Thread {
    /*
     * Overview:
     *
     * ForkJoinWorkerThreads are managed by ForkJoinPools and perform
     * ForkJoinTasks. This class includes bookkeeping in support of
     * worker activation, suspension, and lifecycle control described
     * in more detail in the internal documentation of class
     * ForkJoinPool. And as described further below, this class also
     * includes special-cased support for some ForkJoinTask
     * methods. But the main mechanics involve work-stealing:
     *
     * Work-stealing queues are special forms of Deques that support
     * only three of the four possible end-operations -- push, pop,
     * and deq (aka steal), under the further constraints that push
     * and pop are called only from the owning thread, while deq may
     * be called from other threads.  (If you are unfamiliar with
     * them, you probably want to read Herlihy and Shavit's book "The
     * Art of Multiprocessor programming", chapter 16 describing these
     * in more detail before proceeding.)  The main work-stealing
     * queue design is roughly similar to those in the papers "Dynamic
     * Circular Work-Stealing Deque" by Chase and Lev, SPAA 2005
     * (http://research.sun.com/scalable/pubs/index.html) and
     * "Idempotent work stealing" by Michael, Saraswat, and Vechev,
     * PPoPP 2009 (http://portal.acm.org/citation.cfm?id=1504186).
     * The main differences ultimately stem from gc requirements that
     * we null out taken slots as soon as we can, to maintain as small
     * a footprint as possible even in programs generating huge
     * numbers of tasks. To accomplish this, we shift the CAS
     * arbitrating pop vs deq (steal) from being on the indices
     * ("queueBase" and "queueTop") to the slots themselves (mainly
     * via method "casSlotNull()"). So, both a successful pop and deq
     * mainly entail a CAS of a slot from non-null to null.  Because
     * we rely on CASes of references, we do not need tag bits on
     * queueBase or queueTop.  They are simple ints as used in any
     * circular array-based queue (see for example ArrayDeque).
     * Updates to the indices must still be ordered in a way that
     * guarantees that queueTop == queueBase means the queue is empty,
     * but otherwise may err on the side of possibly making the queue
=======
 * http://creativecommons.org/licenses/publicdomain
 */

package scala.concurrent.forkjoin;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import sun.misc.Unsafe;
import java.lang.reflect.*;

/**
 * A thread managed by a {@link ForkJoinPool}.  This class is
 * subclassable solely for the sake of adding functionality -- there
 * are no overridable methods dealing with scheduling or
 * execution. However, you can override initialization and termination
 * methods surrounding the main task processing loop.  If you do
 * create such a subclass, you will also need to supply a custom
 * ForkJoinWorkerThreadFactory to use it in a ForkJoinPool.
 *
 */
public class ForkJoinWorkerThread extends Thread {
    /*
     * Algorithm overview:
     *
     * 1. Work-Stealing: Work-stealing queues are special forms of
     * Deques that support only three of the four possible
     * end-operations -- push, pop, and deq (aka steal), and only do
     * so under the constraints that push and pop are called only from
     * the owning thread, while deq may be called from other threads.
     * (If you are unfamiliar with them, you probably want to read
     * Herlihy and Shavit's book "The Art of Multiprocessor
     * programming", chapter 16 describing these in more detail before
     * proceeding.)  The main work-stealing queue design is roughly
     * similar to "Dynamic Circular Work-Stealing Deque" by David
     * Chase and Yossi Lev, SPAA 2005
     * (http://research.sun.com/scalable/pubs/index.html).  The main
     * difference ultimately stems from gc requirements that we null
     * out taken slots as soon as we can, to maintain as small a
     * footprint as possible even in programs generating huge numbers
     * of tasks. To accomplish this, we shift the CAS arbitrating pop
     * vs deq (steal) from being on the indices ("base" and "sp") to
     * the slots themselves (mainly via method "casSlotNull()"). So,
     * both a successful pop and deq mainly entail CAS'ing a nonnull
     * slot to null.  Because we rely on CASes of references, we do
     * not need tag bits on base or sp.  They are simple ints as used
     * in any circular array-based queue (see for example ArrayDeque).
     * Updates to the indices must still be ordered in a way that
     * guarantees that (sp - base) > 0 means the queue is empty, but
     * otherwise may err on the side of possibly making the queue
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * appear nonempty when a push, pop, or deq have not fully
     * committed. Note that this means that the deq operation,
     * considered individually, is not wait-free. One thief cannot
     * successfully continue until another in-progress one (or, if
     * previously empty, a push) completes.  However, in the
<<<<<<< HEAD
     * aggregate, we ensure at least probabilistic non-blockingness.
     * If an attempted steal fails, a thief always chooses a different
     * random victim target to try next. So, in order for one thief to
     * progress, it suffices for any in-progress deq or new push on
     * any empty queue to complete.
     *
     * This approach also enables support for "async mode" where local
     * task processing is in FIFO, not LIFO order; simply by using a
     * version of deq rather than pop when locallyFifo is true (as set
     * by the ForkJoinPool).  This allows use in message-passing
     * frameworks in which tasks are never joined.  However neither
     * mode considers affinities, loads, cache localities, etc, so
     * rarely provide the best possible performance on a given
     * machine, but portably provide good throughput by averaging over
     * these factors.  (Further, even if we did try to use such
     * information, we do not usually have a basis for exploiting
     * it. For example, some sets of tasks profit from cache
     * affinities, but others are harmed by cache pollution effects.)
     *
     * When a worker would otherwise be blocked waiting to join a
     * task, it first tries a form of linear helping: Each worker
     * records (in field currentSteal) the most recent task it stole
     * from some other worker. Plus, it records (in field currentJoin)
     * the task it is currently actively joining. Method joinTask uses
     * these markers to try to find a worker to help (i.e., steal back
     * a task from and execute it) that could hasten completion of the
     * actively joined task. In essence, the joiner executes a task
     * that would be on its own local deque had the to-be-joined task
     * not been stolen. This may be seen as a conservative variant of
     * the approach in Wagner & Calder "Leapfrogging: a portable
     * technique for implementing efficient futures" SIGPLAN Notices,
     * 1993 (http://portal.acm.org/citation.cfm?id=155354). It differs
     * in that: (1) We only maintain dependency links across workers
     * upon steals, rather than use per-task bookkeeping.  This may
     * require a linear scan of workers array to locate stealers, but
     * usually doesn't because stealers leave hints (that may become
     * stale/wrong) of where to locate them. This isolates cost to
     * when it is needed, rather than adding to per-task overhead.
     * (2) It is "shallow", ignoring nesting and potentially cyclic
     * mutual steals.  (3) It is intentionally racy: field currentJoin
     * is updated only while actively joining, which means that we
     * miss links in the chain during long-lived tasks, GC stalls etc
     * (which is OK since blocking in such cases is usually a good
     * idea).  (4) We bound the number of attempts to find work (see
     * MAX_HELP) and fall back to suspending the worker and if
     * necessary replacing it with another.
     *
     * Efficient implementation of these algorithms currently relies
     * on an uncomfortable amount of "Unsafe" mechanics. To maintain
     * correct orderings, reads and writes of variable queueBase
     * require volatile ordering.  Variable queueTop need not be
     * volatile because non-local reads always follow those of
     * queueBase.  Similarly, because they are protected by volatile
     * queueBase reads, reads of the queue array and its slots by
     * other threads do not need volatile load semantics, but writes
     * (in push) require store order and CASes (in pop and deq)
     * require (volatile) CAS semantics.  (Michael, Saraswat, and
     * Vechev's algorithm has similar properties, but without support
     * for nulling slots.)  Since these combinations aren't supported
     * using ordinary volatiles, the only way to accomplish these
     * efficiently is to use direct Unsafe calls. (Using external
     * AtomicIntegers and AtomicReferenceArrays for the indices and
     * array is significantly slower because of memory locality and
     * indirection effects.)
     *
     * Further, performance on most platforms is very sensitive to
     * placement and sizing of the (resizable) queue array.  Even
     * though these queues don't usually become all that big, the
     * initial size must be large enough to counteract cache
     * contention effects across multiple queues (especially in the
     * presence of GC cardmarking). Also, to improve thread-locality,
     * queues are initialized after starting.
     */

    /**
     * Mask for pool indices encoded as shorts
     */
    private static final int  SMASK  = 0xffff;

    /**
     * Capacity of work-stealing queue array upon initialization.
     * Must be a power of two. Initial size must be at least 4, but is
=======
     * aggregate, we ensure at least probablistic non-blockingness. If
     * an attempted steal fails, a thief always chooses a different
     * random victim target to try next. So, in order for one thief to
     * progress, it suffices for any in-progress deq or new push on
     * any empty queue to complete. One reason this works well here is
     * that apparently-nonempty often means soon-to-be-stealable,
     * which gives threads a chance to activate if necessary before
     * stealing (see below).
     *
     * Efficient implementation of this approach currently relies on
     * an uncomfortable amount of "Unsafe" mechanics. To maintain
     * correct orderings, reads and writes of variable base require
     * volatile ordering.  Variable sp does not require volatile write
     * but needs cheaper store-ordering on writes.  Because they are
     * protected by volatile base reads, reads of the queue array and
     * its slots do not need volatile load semantics, but writes (in
     * push) require store order and CASes (in pop and deq) require
     * (volatile) CAS semantics. Since these combinations aren't
     * supported using ordinary volatiles, the only way to accomplish
     * these effciently is to use direct Unsafe calls. (Using external
     * AtomicIntegers and AtomicReferenceArrays for the indices and
     * array is significantly slower because of memory locality and
     * indirection effects.) Further, performance on most platforms is
     * very sensitive to placement and sizing of the (resizable) queue
     * array.  Even though these queues don't usually become all that
     * big, the initial size must be large enough to counteract cache
     * contention effects across multiple queues (especially in the
     * presence of GC cardmarking). Also, to improve thread-locality,
     * queues are currently initialized immediately after the thread
     * gets the initial signal to start processing tasks.  However,
     * all queue-related methods except pushTask are written in a way
     * that allows them to instead be lazily allocated and/or disposed
     * of when empty. All together, these low-level implementation
     * choices produce as much as a factor of 4 performance
     * improvement compared to naive implementations, and enable the
     * processing of billions of tasks per second, sometimes at the
     * expense of ugliness.
     *
     * 2. Run control: The primary run control is based on a global
     * counter (activeCount) held by the pool. It uses an algorithm
     * similar to that in Herlihy and Shavit section 17.6 to cause
     * threads to eventually block when all threads declare they are
     * inactive. (See variable "scans".)  For this to work, threads
     * must be declared active when executing tasks, and before
     * stealing a task. They must be inactive before blocking on the
     * Pool Barrier (awaiting a new submission or other Pool
     * event). In between, there is some free play which we take
     * advantage of to avoid contention and rapid flickering of the
     * global activeCount: If inactive, we activate only if a victim
     * queue appears to be nonempty (see above).  Similarly, a thread
     * tries to inactivate only after a full scan of other threads.
     * The net effect is that contention on activeCount is rarely a
     * measurable performance issue. (There are also a few other cases
     * where we scan for work rather than retry/block upon
     * contention.)
     *
     * 3. Selection control. We maintain policy of always choosing to
     * run local tasks rather than stealing, and always trying to
     * steal tasks before trying to run a new submission. All steals
     * are currently performed in randomly-chosen deq-order. It may be
     * worthwhile to bias these with locality / anti-locality
     * information, but doing this well probably requires more
     * lower-level information from JVMs than currently provided.
     */

    /**
     * Capacity of work-stealing queue array upon initialization.
     * Must be a power of two. Initial size must be at least 2, but is
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * padded to minimize cache effects.
     */
    private static final int INITIAL_QUEUE_CAPACITY = 1 << 13;

    /**
<<<<<<< HEAD
     * Maximum size for queue array. Must be a power of two
     * less than or equal to 1 << (31 - width of array entry) to
     * ensure lack of index wraparound, but is capped at a lower
     * value to help users trap runaway computations.
     */
    private static final int MAXIMUM_QUEUE_CAPACITY = 1 << 24; // 16M

    /**
     * The work-stealing queue array. Size must be a power of two.
     * Initialized when started (as oposed to when constructed), to
     * improve memory locality.
     */
    ForkJoinTask<?>[] queue;

    /**
     * The pool this thread works in. Accessed directly by ForkJoinTask.
     */
    final ForkJoinPool pool;

    /**
     * Index (mod queue.length) of next queue slot to push to or pop
     * from. It is written only by owner thread, and accessed by other
     * threads only after reading (volatile) queueBase.  Both queueTop
     * and queueBase are allowed to wrap around on overflow, but
     * (queueTop - queueBase) still estimates size.
     */
    int queueTop;
=======
     * Maximum work-stealing queue array size.  Must be less than or
     * equal to 1 << 28 to ensure lack of index wraparound. (This
     * is less than usual bounds, because we need leftshift by 3
     * to be in int range).
     */
    private static final int MAXIMUM_QUEUE_CAPACITY = 1 << 28;

    /**
     * The pool this thread works in. Accessed directly by ForkJoinTask
     */
    final ForkJoinPool pool;

    /**
     * The work-stealing queue array. Size must be a power of two.
     * Initialized when thread starts, to improve memory locality.
     */
    private ForkJoinTask<?>[] queue;

    /**
     * Index (mod queue.length) of next queue slot to push to or pop
     * from. It is written only by owner thread, via ordered store.
     * Both sp and base are allowed to wrap around on overflow, but
     * (sp - base) still estimates size.
     */
    private volatile int sp;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    /**
     * Index (mod queue.length) of least valid queue slot, which is
     * always the next position to steal from if nonempty.
     */
<<<<<<< HEAD
    volatile int queueBase;

    /**
     * The index of most recent stealer, used as a hint to avoid
     * traversal in method helpJoinTask. This is only a hint because a
     * worker might have had multiple steals and this only holds one
     * of them (usually the most current). Declared non-volatile,
     * relying on other prevailing sync to keep reasonably current.
     */
    int stealHint;

    /**
     * Index of this worker in pool array. Set once by pool before
     * running, and accessed directly by pool to locate this worker in
     * its workers array.
     */
    final int poolIndex;

    /**
     * Encoded record for pool task waits. Usages are always
     * surrounded by volatile reads/writes
     */
    int nextWait;

    /**
     * Complement of poolIndex, offset by count of entries of task
     * waits. Accessed by ForkJoinPool to manage event waiters.
     */
    volatile int eventCount;

    /**
     * Seed for random number generator for choosing steal victims.
     * Uses Marsaglia xorshift. Must be initialized as nonzero.
     */
    int seed;

    /**
     * Number of steals. Directly accessed (and reset) by pool when
     * idle.
     */
    int stealCount;

    /**
     * True if this worker should or did terminate
     */
    volatile boolean terminate;

    /**
     * Set to true before LockSupport.park; false on return
     */
    volatile boolean parked;

    /**
     * True if use local fifo, not default lifo, for local polling.
     * Shadows value from ForkJoinPool.
     */
    final boolean locallyFifo;

    /**
     * The task most recently stolen from another worker (or
     * submission queue).  All uses are surrounded by enough volatile
     * reads/writes to maintain as non-volatile.
     */
    ForkJoinTask<?> currentSteal;

    /**
     * The task currently being joined, set only when actively trying
     * to help other stealers in helpJoinTask. All uses are surrounded
     * by enough volatile reads/writes to maintain as non-volatile.
     */
    ForkJoinTask<?> currentJoin;

    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
=======
    private volatile int base;

    /**
     * Activity status. When true, this worker is considered active.
     * Must be false upon construction. It must be true when executing
     * tasks, and BEFORE stealing a task. It must be false before
     * calling pool.sync
     */
    private boolean active;

    /**
     * Run state of this worker. Supports simple versions of the usual
     * shutdown/shutdownNow control.
     */
    private volatile int runState;

    /**
     * Seed for random number generator for choosing steal victims.
     * Uses Marsaglia xorshift. Must be nonzero upon initialization.
     */
    private int seed;

    /**
     * Number of steals, transferred to pool when idle
     */
    private int stealCount;

    /**
     * Index of this worker in pool array. Set once by pool before
     * running, and accessed directly by pool during cleanup etc
     */
    int poolIndex;

    /**
     * The last barrier event waited for. Accessed in pool callback
     * methods, but only by current thread.
     */
    long lastEventCount;

    /**
     * True if use local fifo, not default lifo, for local polling
     */
    private boolean locallyFifo;

    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected ForkJoinWorkerThread(ForkJoinPool pool) {
<<<<<<< HEAD
        super(pool.nextWorkerName());
        this.pool = pool;
        int k = pool.registerWorker(this);
        poolIndex = k;
        eventCount = ~k & SMASK; // clear wait count
        locallyFifo = pool.locallyFifo;
        Thread.UncaughtExceptionHandler ueh = pool.ueh;
        if (ueh != null)
            setUncaughtExceptionHandler(ueh);
        setDaemon(true);
    }

    // Public methods

    /**
     * Returns the pool hosting this thread.
     *
=======
        if (pool == null) throw new NullPointerException();
        this.pool = pool;
        // Note: poolIndex is set by pool during construction
        // Remaining initialization is deferred to onStart
    }

    // Public access methods

    /**
     * Returns the pool hosting this thread
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @return the pool
     */
    public ForkJoinPool getPool() {
        return pool;
    }

    /**
     * Returns the index number of this thread in its pool.  The
     * returned value ranges from zero to the maximum number of
     * threads (minus one) that have ever been created in the pool.
     * This method may be useful for applications that track status or
     * collect results per-worker rather than per-task.
<<<<<<< HEAD
     *
     * @return the index number
=======
     * @return the index number.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    public int getPoolIndex() {
        return poolIndex;
    }

<<<<<<< HEAD
    // Randomization

    /**
     * Computes next value for random victim probes and backoffs.
     * Scans don't require a very high quality generator, but also not
     * a crummy one.  Marsaglia xor-shift is cheap and works well
     * enough.  Note: This is manually inlined in FJP.scan() to avoid
     * writes inside busy loops.
     */
    private int nextSeed() {
        int r = seed;
        r ^= r << 13;
        r ^= r >>> 17;
        r ^= r << 5;
        return seed = r;
    }

    // Run State management
=======
    /**
     * Establishes local first-in-first-out scheduling mode for forked
     * tasks that are never joined.
     * @param async if true, use locally FIFO scheduling
     */
    void setAsyncMode(boolean async) {
        locallyFifo = async;
    }

    // Runstate management

    // Runstate values. Order matters
    private static final int RUNNING     = 0;
    private static final int SHUTDOWN    = 1;
    private static final int TERMINATING = 2;
    private static final int TERMINATED  = 3;

    final boolean isShutdown()    { return runState >= SHUTDOWN;  }
    final boolean isTerminating() { return runState >= TERMINATING;  }
    final boolean isTerminated()  { return runState == TERMINATED; }
    final boolean shutdown()      { return transitionRunStateTo(SHUTDOWN); }
    final boolean shutdownNow()   { return transitionRunStateTo(TERMINATING); }

    /**
     * Transition to at least the given state. Return true if not
     * already at least given state.
     */
    private boolean transitionRunStateTo(int state) {
        for (;;) {
            int s = runState;
            if (s >= state)
                return false;
            if (_unsafe.compareAndSwapInt(this, runStateOffset, s, state))
                return true;
        }
    }

    /**
     * Try to set status to active; fail on contention
     */
    private boolean tryActivate() {
        if (!active) {
            if (!pool.tryIncrementActiveCount())
                return false;
            active = true;
        }
        return true;
    }

    /**
     * Try to set status to active; fail on contention
     */
    private boolean tryInactivate() {
        if (active) {
            if (!pool.tryDecrementActiveCount())
                return false;
            active = false;
        }
        return true;
    }

    /**
     * Computes next value for random victim probe. Scans don't
     * require a very high quality generator, but also not a crummy
     * one. Marsaglia xor-shift is cheap and works well.
     */
    private static int xorShift(int r) {
        r ^= r << 1;
        r ^= r >>> 3;
        r ^= r << 10;
        return r;
    }

    // Lifecycle methods

    /**
     * This method is required to be public, but should never be
     * called explicitly. It performs the main run loop to execute
     * ForkJoinTasks.
     */
    public void run() {
        Throwable exception = null;
        try {
            onStart();
            pool.sync(this); // await first pool event
            mainLoop();
        } catch (Throwable ex) {
            exception = ex;
        } finally {
            onTermination(exception);
        }
    }

    /**
     * Execute tasks until shut down.
     */
    private void mainLoop() {
        while (!isShutdown()) {
            ForkJoinTask<?> t = pollTask();
            if (t != null || (t = pollSubmission()) != null)
                t.quietlyExec();
            else if (tryInactivate())
                pool.sync(this);
        }
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    /**
     * Initializes internal state after construction but before
     * processing any tasks. If you override this method, you must
<<<<<<< HEAD
     * invoke {@code super.onStart()} at the beginning of the method.
=======
     * invoke super.onStart() at the beginning of the method.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * Initialization requires care: Most fields must have legal
     * default values, to ensure that attempted accesses from other
     * threads work correctly even before this thread starts
     * processing tasks.
     */
    protected void onStart() {
<<<<<<< HEAD
        queue = new ForkJoinTask<?>[INITIAL_QUEUE_CAPACITY];
        int r = pool.workerSeedGenerator.nextInt();
        seed = (r == 0) ? 1 : r; //  must be nonzero
    }

    /**
     * Performs cleanup associated with termination of this worker
     * thread.  If you override this method, you must invoke
     * {@code super.onTermination} at the end of the overridden method.
     *
     * @param exception the exception causing this thread to abort due
     * to an unrecoverable error, or {@code null} if completed normally
     */
    protected void onTermination(Throwable exception) {
        try {
            terminate = true;
            cancelTasks();
            pool.deregisterWorker(this, exception);
=======
        // Allocate while starting to improve chances of thread-local
        // isolation
        queue = new ForkJoinTask<?>[INITIAL_QUEUE_CAPACITY];
        // Initial value of seed need not be especially random but
        // should differ across workers and must be nonzero
        int p = poolIndex + 1;
        seed = p + (p << 8) + (p << 16) + (p << 24); // spread bits
    }

    /**
     * Perform cleanup associated with termination of this worker
     * thread.  If you override this method, you must invoke
     * super.onTermination at the end of the overridden method.
     *
     * @param exception the exception causing this thread to abort due
     * to an unrecoverable error, or null if completed normally.
     */
    protected void onTermination(Throwable exception) {
        // Execute remaining local tasks unless aborting or terminating
        while (exception == null &&  !pool.isTerminating() && base != sp) {
            try {
                ForkJoinTask<?> t = popTask();
                if (t != null)
                    t.quietlyExec();
            } catch(Throwable ex) {
                exception = ex;
            }
        }
        // Cancel other tasks, transition status, notify pool, and
        // propagate exception to uncaught exception handler
        try {
            do;while (!tryInactivate()); // ensure inactive
            cancelTasks();
            runState = TERMINATED;
            pool.workerTerminated(this);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        } catch (Throwable ex) {        // Shouldn't ever happen
            if (exception == null)      // but if so, at least rethrown
                exception = ex;
        } finally {
            if (exception != null)
<<<<<<< HEAD
                UNSAFE.throwException(exception);
        }
    }

    /**
     * This method is required to be public, but should never be
     * called explicitly. It performs the main run loop to execute
     * {@link ForkJoinTask}s.
     */
    public void run() {
        Throwable exception = null;
        try {
            onStart();
            pool.work(this);
        } catch (Throwable ex) {
            exception = ex;
        } finally {
            onTermination(exception);
        }
    }

    /*
     * Intrinsics-based atomic writes for queue slots. These are
     * basically the same as methods in AtomicReferenceArray, but
     * specialized for (1) ForkJoinTask elements (2) requirement that
     * nullness and bounds checks have already been performed by
     * callers and (3) effective offsets are known not to overflow
     * from int to long (because of MAXIMUM_QUEUE_CAPACITY). We don't
     * need corresponding version for reads: plain array reads are OK
     * because they are protected by other volatile reads and are
     * confirmed by CASes.
     *
     * Most uses don't actually call these methods, but instead
     * contain inlined forms that enable more predictable
     * optimization.  We don't define the version of write used in
     * pushTask at all, but instead inline there a store-fenced array
     * slot write.
     *
     * Also in most methods, as a performance (not correctness) issue,
     * we'd like to encourage compilers not to arbitrarily postpone
     * setting queueTop after writing slot.  Currently there is no
     * intrinsic for arranging this, but using Unsafe putOrderedInt
     * may be a preferable strategy on some compilers even though its
     * main effect is a pre-, not post- fence. To simplify possible
     * changes, the option is left in comments next to the associated
     * assignments.
     */

    /**
     * CASes slot i of array q from t to null. Caller must ensure q is
     * non-null and index is in range.
     */
    private static final boolean casSlotNull(ForkJoinTask<?>[] q, int i,
                                             ForkJoinTask<?> t) {
        return UNSAFE.compareAndSwapObject(q, (i << ASHIFT) + ABASE, t, null);
    }

    /**
     * Performs a volatile write of the given task at given slot of
     * array q.  Caller must ensure q is non-null and index is in
     * range. This method is used only during resets and backouts.
     */
    private static final void writeSlot(ForkJoinTask<?>[] q, int i,
                                        ForkJoinTask<?> t) {
        UNSAFE.putObjectVolatile(q, (i << ASHIFT) + ABASE, t);
    }

    // queue methods

    /**
     * Pushes a task. Call only from this thread.
     *
     * @param t the task. Caller must ensure non-null.
     */
    final void pushTask(ForkJoinTask<?> t) {
        ForkJoinTask<?>[] q; int s, m;
        if ((q = queue) != null) {    // ignore if queue removed
            long u = (((s = queueTop) & (m = q.length - 1)) << ASHIFT) + ABASE;
            UNSAFE.putOrderedObject(q, u, t);
            queueTop = s + 1;         // or use putOrderedInt
            if ((s -= queueBase) <= 2)
                pool.signalWork();
            else if (s == m)
                growQueue();
        }
    }

    /**
     * Creates or doubles queue array.  Transfers elements by
     * emulating steals (deqs) from old array and placing, oldest
     * first, into new array.
     */
    private void growQueue() {
        ForkJoinTask<?>[] oldQ = queue;
        int size = oldQ != null ? oldQ.length << 1 : INITIAL_QUEUE_CAPACITY;
        if (size > MAXIMUM_QUEUE_CAPACITY)
            throw new RejectedExecutionException("Queue capacity exceeded");
        if (size < INITIAL_QUEUE_CAPACITY)
            size = INITIAL_QUEUE_CAPACITY;
        ForkJoinTask<?>[] q = queue = new ForkJoinTask<?>[size];
        int mask = size - 1;
        int top = queueTop;
        int oldMask;
        if (oldQ != null && (oldMask = oldQ.length - 1) >= 0) {
            for (int b = queueBase; b != top; ++b) {
                long u = ((b & oldMask) << ASHIFT) + ABASE;
                Object x = UNSAFE.getObjectVolatile(oldQ, u);
                if (x != null && UNSAFE.compareAndSwapObject(oldQ, u, x, null))
                    UNSAFE.putObjectVolatile
                        (q, ((b & mask) << ASHIFT) + ABASE, x);
            }
        }
=======
                ForkJoinTask.rethrowException(exception);
        }
    }

    // Intrinsics-based support for queue operations.

    /**
     * Add in store-order the given task at given slot of q to
     * null. Caller must ensure q is nonnull and index is in range.
     */
    private static void setSlot(ForkJoinTask<?>[] q, int i,
                                ForkJoinTask<?> t){
        _unsafe.putOrderedObject(q, (i << qShift) + qBase, t);
    }

    /**
     * CAS given slot of q to null. Caller must ensure q is nonnull
     * and index is in range.
     */
    private static boolean casSlotNull(ForkJoinTask<?>[] q, int i,
                                       ForkJoinTask<?> t) {
        return _unsafe.compareAndSwapObject(q, (i << qShift) + qBase, t, null);
    }

    /**
     * Sets sp in store-order.
     */
    private void storeSp(int s) {
        _unsafe.putOrderedInt(this, spOffset, s);
    }

    // Main queue methods

    /**
     * Pushes a task. Called only by current thread.
     * @param t the task. Caller must ensure nonnull
     */
    final void pushTask(ForkJoinTask<?> t) {
        ForkJoinTask<?>[] q = queue;
        int mask = q.length - 1;
        int s = sp;
        setSlot(q, s & mask, t);
        storeSp(++s);
        if ((s -= base) == 1)
            pool.signalWork();
        else if (s >= mask)
            growQueue();
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    /**
     * Tries to take a task from the base of the queue, failing if
<<<<<<< HEAD
     * empty or contended. Note: Specializations of this code appear
     * in locallyDeqTask and elsewhere.
     *
     * @return a task, or null if none or contended
     */
    final ForkJoinTask<?> deqTask() {
        ForkJoinTask<?> t; ForkJoinTask<?>[] q; int b, i;
        if (queueTop != (b = queueBase) &&
            (q = queue) != null && // must read q after b
            (i = (q.length - 1) & b) >= 0 &&
            (t = q[i]) != null && queueBase == b &&
            UNSAFE.compareAndSwapObject(q, (i << ASHIFT) + ABASE, t, null)) {
            queueBase = b + 1;
=======
     * either empty or contended.
     * @return a task, or null if none or contended.
     */
    final ForkJoinTask<?> deqTask() {
        ForkJoinTask<?> t;
        ForkJoinTask<?>[] q;
        int i;
        int b;
        if (sp != (b = base) &&
            (q = queue) != null && // must read q after b
            (t = q[i = (q.length - 1) & b]) != null &&
            casSlotNull(q, i, t)) {
            base = b + 1;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            return t;
        }
        return null;
    }

    /**
<<<<<<< HEAD
     * Tries to take a task from the base of own queue.  Called only
     * by this thread.
     *
     * @return a task, or null if none
     */
    final ForkJoinTask<?> locallyDeqTask() {
        ForkJoinTask<?> t; int m, b, i;
        ForkJoinTask<?>[] q = queue;
        if (q != null && (m = q.length - 1) >= 0) {
            while (queueTop != (b = queueBase)) {
                if ((t = q[i = m & b]) != null &&
                    queueBase == b &&
                    UNSAFE.compareAndSwapObject(q, (i << ASHIFT) + ABASE,
                                                t, null)) {
                    queueBase = b + 1;
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Returns a popped task, or null if empty.
     * Called only by this thread.
     */
    private ForkJoinTask<?> popTask() {
        int m;
        ForkJoinTask<?>[] q = queue;
        if (q != null && (m = q.length - 1) >= 0) {
            for (int s; (s = queueTop) != queueBase;) {
                int i = m & --s;
                long u = (i << ASHIFT) + ABASE; // raw offset
                ForkJoinTask<?> t = q[i];
                if (t == null)   // lost to stealer
                    break;
                if (UNSAFE.compareAndSwapObject(q, u, t, null)) {
                    queueTop = s; // or putOrderedInt
                    return t;
                }
=======
     * Returns a popped task, or null if empty. Ensures active status
     * if nonnull. Called only by current thread.
     */
    final ForkJoinTask<?> popTask() {
        int s = sp;
        while (s != base) {
            if (tryActivate()) {
                ForkJoinTask<?>[] q = queue;
                int mask = q.length - 1;
                int i = (s - 1) & mask;
                ForkJoinTask<?> t = q[i];
                if (t == null || !casSlotNull(q, i, t))
                    break;
                storeSp(s - 1);
                return t;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            }
        }
        return null;
    }

    /**
<<<<<<< HEAD
     * Specialized version of popTask to pop only if topmost element
     * is the given task. Called only by this thread.
     *
     * @param t the task. Caller must ensure non-null.
     */
    final boolean unpushTask(ForkJoinTask<?> t) {
        ForkJoinTask<?>[] q;
        int s;
        if ((q = queue) != null && (s = queueTop) != queueBase &&
            UNSAFE.compareAndSwapObject
            (q, (((q.length - 1) & --s) << ASHIFT) + ABASE, t, null)) {
            queueTop = s; // or putOrderedInt
=======
     * Specialized version of popTask to pop only if
     * topmost element is the given task. Called only
     * by current thread while active.
     * @param t the task. Caller must ensure nonnull
     */
    final boolean unpushTask(ForkJoinTask<?> t) {
        ForkJoinTask<?>[] q = queue;
        int mask = q.length - 1;
        int s = sp - 1;
        if (casSlotNull(q, s & mask, t)) {
            storeSp(s);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            return true;
        }
        return false;
    }

    /**
<<<<<<< HEAD
     * Returns next task, or null if empty or contended.
     */
    final ForkJoinTask<?> peekTask() {
        int m;
        ForkJoinTask<?>[] q = queue;
        if (q == null || (m = q.length - 1) < 0)
            return null;
        int i = locallyFifo ? queueBase : (queueTop - 1);
        return q[i & m];
    }

    // Support methods for ForkJoinPool

    /**
     * Runs the given task, plus any local tasks until queue is empty
     */
    final void execTask(ForkJoinTask<?> t) {
        currentSteal = t;
        for (;;) {
            if (t != null)
                t.doExec();
            if (queueTop == queueBase)
                break;
            t = locallyFifo ? locallyDeqTask() : popTask();
        }
        ++stealCount;
        currentSteal = null;
    }

    /**
     * Removes and cancels all tasks in queue.  Can be called from any
     * thread.
     */
    final void cancelTasks() {
        ForkJoinTask<?> cj = currentJoin; // try to cancel ongoing tasks
        if (cj != null && cj.status >= 0)
            cj.cancelIgnoringExceptions();
        ForkJoinTask<?> cs = currentSteal;
        if (cs != null && cs.status >= 0)
            cs.cancelIgnoringExceptions();
        while (queueBase != queueTop) {
            ForkJoinTask<?> t = deqTask();
            if (t != null)
                t.cancelIgnoringExceptions();
        }
    }

    /**
     * Drains tasks to given collection c.
     *
     * @return the number of tasks drained
     */
    final int drainTasksTo(Collection<? super ForkJoinTask<?>> c) {
        int n = 0;
        while (queueBase != queueTop) {
            ForkJoinTask<?> t = deqTask();
            if (t != null) {
                c.add(t);
                ++n;
            }
        }
        return n;
    }

    // Support methods for ForkJoinTask

    /**
     * Returns an estimate of the number of tasks in the queue.
     */
    final int getQueueSize() {
        return queueTop - queueBase;
    }

    /**
     * Gets and removes a local task.
     *
     * @return a task, if available
     */
    final ForkJoinTask<?> pollLocalTask() {
        return locallyFifo ? locallyDeqTask() : popTask();
    }

    /**
     * Gets and removes a local or stolen task.
     *
     * @return a task, if available
     */
    final ForkJoinTask<?> pollTask() {
        ForkJoinWorkerThread[] ws;
        ForkJoinTask<?> t = pollLocalTask();
        if (t != null || (ws = pool.workers) == null)
            return t;
        int n = ws.length; // cheap version of FJP.scan
        int steps = n << 1;
        int r = nextSeed();
        int i = 0;
        while (i < steps) {
            ForkJoinWorkerThread w = ws[(i++ + r) & (n - 1)];
            if (w != null && w.queueBase != w.queueTop && w.queue != null) {
                if ((t = w.deqTask()) != null)
                    return t;
                i = 0;
            }
=======
     * Returns next task.
     */
    final ForkJoinTask<?> peekTask() {
        ForkJoinTask<?>[] q = queue;
        if (q == null)
            return null;
        int mask = q.length - 1;
        int i = locallyFifo? base : (sp - 1);
        return q[i & mask];
    }

    /**
     * Doubles queue array size. Transfers elements by emulating
     * steals (deqs) from old array and placing, oldest first, into
     * new array.
     */
    private void growQueue() {
        ForkJoinTask<?>[] oldQ = queue;
        int oldSize = oldQ.length;
        int newSize = oldSize << 1;
        if (newSize > MAXIMUM_QUEUE_CAPACITY)
            throw new RejectedExecutionException("Queue capacity exceeded");
        ForkJoinTask<?>[] newQ = queue = new ForkJoinTask<?>[newSize];

        int b = base;
        int bf = b + oldSize;
        int oldMask = oldSize - 1;
        int newMask = newSize - 1;
        do {
            int oldIndex = b & oldMask;
            ForkJoinTask<?> t = oldQ[oldIndex];
            if (t != null && !casSlotNull(oldQ, oldIndex, t))
                t = null;
            setSlot(newQ, b & newMask, t);
        } while (++b != bf);
        pool.signalWork();
    }

    /**
     * Tries to steal a task from another worker. Starts at a random
     * index of workers array, and probes workers until finding one
     * with non-empty queue or finding that all are empty.  It
     * randomly selects the first n probes. If these are empty, it
     * resorts to a full circular traversal, which is necessary to
     * accurately set active status by caller. Also restarts if pool
     * events occurred since last scan, which forces refresh of
     * workers array, in case barrier was associated with resize.
     *
     * This method must be both fast and quiet -- usually avoiding
     * memory accesses that could disrupt cache sharing etc other than
     * those needed to check for and take tasks. This accounts for,
     * among other things, updating random seed in place without
     * storing it until exit.
     *
     * @return a task, or null if none found
     */
    private ForkJoinTask<?> scan() {
        ForkJoinTask<?> t = null;
        int r = seed;                    // extract once to keep scan quiet
        ForkJoinWorkerThread[] ws;       // refreshed on outer loop
        int mask;                        // must be power 2 minus 1 and > 0
        outer:do {
            if ((ws = pool.workers) != null && (mask = ws.length - 1) > 0) {
                int idx = r;
                int probes = ~mask;      // use random index while negative
                for (;;) {
                    r = xorShift(r);     // update random seed
                    ForkJoinWorkerThread v = ws[mask & idx];
                    if (v == null || v.sp == v.base) {
                        if (probes <= mask)
                            idx = (probes++ < 0)? r : (idx + 1);
                        else
                            break;
                    }
                    else if (!tryActivate() || (t = v.deqTask()) == null)
                        continue outer;  // restart on contention
                    else
                        break outer;
                }
            }
        } while (pool.hasNewSyncEvent(this)); // retry on pool events
        seed = r;
        return t;
    }

    /**
     * gets and removes a local or stolen a task
     * @return a task, if available
     */
    final ForkJoinTask<?> pollTask() {
        ForkJoinTask<?> t = locallyFifo? deqTask() : popTask();
        if (t == null && (t = scan()) != null)
            ++stealCount;
        return t;
    }

    /**
     * gets a local task
     * @return a task, if available
     */
    final ForkJoinTask<?> pollLocalTask() {
        return locallyFifo? deqTask() : popTask();
    }

    /**
     * Returns a pool submission, if one exists, activating first.
     * @return a submission, if available
     */
    private ForkJoinTask<?> pollSubmission() {
        ForkJoinPool p = pool;
        while (p.hasQueuedSubmissions()) {
            ForkJoinTask<?> t;
            if (tryActivate() && (t = p.pollSubmission()) != null)
                return t;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        }
        return null;
    }

<<<<<<< HEAD
    /**
     * The maximum stolen->joining link depth allowed in helpJoinTask,
     * as well as the maximum number of retries (allowing on average
     * one staleness retry per level) per attempt to instead try
     * compensation.  Depths for legitimate chains are unbounded, but
     * we use a fixed constant to avoid (otherwise unchecked) cycles
     * and bound staleness of traversal parameters at the expense of
     * sometimes blocking when we could be helping.
     */
    private static final int MAX_HELP = 16;

    /**
     * Possibly runs some tasks and/or blocks, until joinMe is done.
     *
     * @param joinMe the task to join
     * @return completion status on exit
     */
    final int joinTask(ForkJoinTask<?> joinMe) {
        ForkJoinTask<?> prevJoin = currentJoin;
        currentJoin = joinMe;
        for (int s, retries = MAX_HELP;;) {
            if ((s = joinMe.status) < 0) {
                currentJoin = prevJoin;
                return s;
            }
            if (retries > 0) {
                if (queueTop != queueBase) {
                    if (!localHelpJoinTask(joinMe))
                        retries = 0;           // cannot help
                }
                else if (retries == MAX_HELP >>> 1) {
                    --retries;                 // check uncommon case
                    if (tryDeqAndExec(joinMe) >= 0)
                        Thread.yield();        // for politeness
                }
                else
                    retries = helpJoinTask(joinMe) ? MAX_HELP : retries - 1;
            }
            else {
                retries = MAX_HELP;           // restart if not done
                pool.tryAwaitJoin(joinMe);
            }
        }
    }

    /**
     * If present, pops and executes the given task, or any other
     * cancelled task
     *
     * @return false if any other non-cancelled task exists in local queue
     */
    private boolean localHelpJoinTask(ForkJoinTask<?> joinMe) {
        int s, i; ForkJoinTask<?>[] q; ForkJoinTask<?> t;
        if ((s = queueTop) != queueBase && (q = queue) != null &&
            (i = (q.length - 1) & --s) >= 0 &&
            (t = q[i]) != null) {
            if (t != joinMe && t.status >= 0)
                return false;
            if (UNSAFE.compareAndSwapObject
                (q, (i << ASHIFT) + ABASE, t, null)) {
                queueTop = s;           // or putOrderedInt
                t.doExec();
            }
        }
        return true;
    }

    /**
     * Tries to locate and execute tasks for a stealer of the given
     * task, or in turn one of its stealers, Traces
     * currentSteal->currentJoin links looking for a thread working on
     * a descendant of the given task and with a non-empty queue to
     * steal back and execute tasks from.  The implementation is very
     * branchy to cope with potential inconsistencies or loops
     * encountering chains that are stale, unknown, or of length
     * greater than MAX_HELP links.  All of these cases are dealt with
     * by just retrying by caller.
     *
     * @param joinMe the task to join
     * @param canSteal true if local queue is empty
     * @return true if ran a task
     */
    private boolean helpJoinTask(ForkJoinTask<?> joinMe) {
        boolean helped = false;
        int m = pool.scanGuard & SMASK;
        ForkJoinWorkerThread[] ws = pool.workers;
        if (ws != null && ws.length > m && joinMe.status >= 0) {
            int levels = MAX_HELP;              // remaining chain length
            ForkJoinTask<?> task = joinMe;      // base of chain
            outer:for (ForkJoinWorkerThread thread = this;;) {
                // Try to find v, the stealer of task, by first using hint
                ForkJoinWorkerThread v = ws[thread.stealHint & m];
                if (v == null || v.currentSteal != task) {
                    for (int j = 0; ;) {        // search array
                        if ((v = ws[j]) != null && v.currentSteal == task) {
                            thread.stealHint = j;
                            break;              // save hint for next time
                        }
                        if (++j > m)
                            break outer;        // can't find stealer
                    }
                }
                // Try to help v, using specialized form of deqTask
                for (;;) {
                    ForkJoinTask<?>[] q; int b, i;
                    if (joinMe.status < 0)
                        break outer;
                    if ((b = v.queueBase) == v.queueTop ||
                        (q = v.queue) == null ||
                        (i = (q.length-1) & b) < 0)
                        break;                  // empty
                    long u = (i << ASHIFT) + ABASE;
                    ForkJoinTask<?> t = q[i];
                    if (task.status < 0)
                        break outer;            // stale
                    if (t != null && v.queueBase == b &&
                        UNSAFE.compareAndSwapObject(q, u, t, null)) {
                        v.queueBase = b + 1;
                        v.stealHint = poolIndex;
                        ForkJoinTask<?> ps = currentSteal;
                        currentSteal = t;
                        t.doExec();
                        currentSteal = ps;
                        helped = true;
                    }
                }
                // Try to descend to find v's stealer
                ForkJoinTask<?> next = v.currentJoin;
                if (--levels > 0 && task.status >= 0 &&
                    next != null && next != task) {
                    task = next;
                    thread = v;
                }
                else
                    break;  // max levels, stale, dead-end, or cyclic
            }
        }
        return helped;
    }

    /**
     * Performs an uncommon case for joinTask: If task t is at base of
     * some workers queue, steals and executes it.
     *
     * @param t the task
     * @return t's status
     */
    private int tryDeqAndExec(ForkJoinTask<?> t) {
        int m = pool.scanGuard & SMASK;
        ForkJoinWorkerThread[] ws = pool.workers;
        if (ws != null && ws.length > m && t.status >= 0) {
            for (int j = 0; j <= m; ++j) {
                ForkJoinTask<?>[] q; int b, i;
                ForkJoinWorkerThread v = ws[j];
                if (v != null &&
                    (b = v.queueBase) != v.queueTop &&
                    (q = v.queue) != null &&
                    (i = (q.length - 1) & b) >= 0 &&
                    q[i] ==  t) {
                    long u = (i << ASHIFT) + ABASE;
                    if (v.queueBase == b &&
                        UNSAFE.compareAndSwapObject(q, u, t, null)) {
                        v.queueBase = b + 1;
                        v.stealHint = poolIndex;
                        ForkJoinTask<?> ps = currentSteal;
                        currentSteal = t;
                        t.doExec();
                        currentSteal = ps;
                    }
                    break;
                }
            }
        }
        return t.status;
    }

    /**
     * Implements ForkJoinTask.getSurplusQueuedTaskCount().  Returns
     * an estimate of the number of tasks, offset by a function of
     * number of idle workers.
     *
     * This method provides a cheap heuristic guide for task
     * partitioning when programmers, frameworks, tools, or languages
     * have little or no idea about task granularity.  In essence by
     * offering this method, we ask users only about tradeoffs in
     * overhead vs expected throughput and its variance, rather than
     * how finely to partition tasks.
     *
     * In a steady state strict (tree-structured) computation, each
     * thread makes available for stealing enough tasks for other
     * threads to remain active. Inductively, if all threads play by
     * the same rules, each thread should make available only a
     * constant number of tasks.
     *
     * The minimum useful constant is just 1. But using a value of 1
     * would require immediate replenishment upon each steal to
     * maintain enough tasks, which is infeasible.  Further,
     * partitionings/granularities of offered tasks should minimize
     * steal rates, which in general means that threads nearer the top
     * of computation tree should generate more than those nearer the
     * bottom. In perfect steady state, each thread is at
     * approximately the same level of computation tree. However,
     * producing extra tasks amortizes the uncertainty of progress and
     * diffusion assumptions.
     *
     * So, users will want to use values larger, but not much larger
     * than 1 to both smooth over transient shortages and hedge
     * against uneven progress; as traded off against the cost of
     * extra task overhead. We leave the user to pick a threshold
     * value to compare with the results of this call to guide
     * decisions, but recommend values such as 3.
     *
     * When all threads are active, it is on average OK to estimate
     * surplus strictly locally. In steady-state, if one thread is
     * maintaining say 2 surplus tasks, then so are others. So we can
     * just use estimated queue length (although note that (queueTop -
     * queueBase) can be an overestimate because of stealers lagging
     * increments of queueBase).  However, this strategy alone leads
     * to serious mis-estimates in some non-steady-state conditions
     * (ramp-up, ramp-down, other stalls). We can detect many of these
     * by further considering the number of "idle" threads, that are
     * known to have zero queued tasks, so compensate by a factor of
     * (#idle/#active) threads.
     */
    final int getEstimatedSurplusTaskCount() {
        return queueTop - queueBase - pool.idlePerActive();
    }

    /**
     * Runs tasks until {@code pool.isQuiescent()}. We piggyback on
     * pool's active count ctl maintenance, but rather than blocking
     * when tasks cannot be found, we rescan until all others cannot
     * find tasks either. The bracketing by pool quiescerCounts
     * updates suppresses pool auto-shutdown mechanics that could
     * otherwise prematurely terminate the pool because all threads
     * appear to be inactive.
     */
    final void helpQuiescePool() {
        boolean active = true;
        ForkJoinTask<?> ps = currentSteal; // to restore below
        ForkJoinPool p = pool;
        p.addQuiescerCount(1);
        for (;;) {
            ForkJoinWorkerThread[] ws = p.workers;
            ForkJoinWorkerThread v = null;
            int n;
            if (queueTop != queueBase)
                v = this;
            else if (ws != null && (n = ws.length) > 1) {
                ForkJoinWorkerThread w;
                int r = nextSeed(); // cheap version of FJP.scan
                int steps = n << 1;
                for (int i = 0; i < steps; ++i) {
                    if ((w = ws[(i + r) & (n - 1)]) != null &&
                        w.queueBase != w.queueTop) {
                        v = w;
                        break;
                    }
                }
            }
            if (v != null) {
                ForkJoinTask<?> t;
                if (!active) {
                    active = true;
                    p.addActiveCount(1);
                }
                if ((t = (v != this) ? v.deqTask() :
                     locallyFifo ? locallyDeqTask() : popTask()) != null) {
                    currentSteal = t;
                    t.doExec();
                    currentSteal = ps;
                }
            }
            else {
                if (active) {
                    active = false;
                    p.addActiveCount(-1);
                }
                if (p.isQuiescent()) {
                    p.addActiveCount(1);
                    p.addQuiescerCount(-1);
                    break;
                }
=======
    // Methods accessed only by Pool

    /**
     * Removes and cancels all tasks in queue.  Can be called from any
     * thread.
     */
    final void cancelTasks() {
        ForkJoinTask<?> t;
        while (base != sp && (t = deqTask()) != null)
            t.cancelIgnoringExceptions();
    }

    /**
     * Drains tasks to given collection c
     * @return the number of tasks drained
     */
    final int drainTasksTo(Collection<ForkJoinTask<?>> c) {
        int n = 0;
        ForkJoinTask<?> t;
        while (base != sp && (t = deqTask()) != null) {
            c.add(t);
            ++n;
        }
        return n;
    }

    /**
     * Get and clear steal count for accumulation by pool.  Called
     * only when known to be idle (in pool.sync and termination).
     */
    final int getAndClearStealCount() {
        int sc = stealCount;
        stealCount = 0;
        return sc;
    }

    /**
     * Returns true if at least one worker in the given array appears
     * to have at least one queued task.
     * @param ws array of workers
     */
    static boolean hasQueuedTasks(ForkJoinWorkerThread[] ws) {
        if (ws != null) {
            int len = ws.length;
            for (int j = 0; j < 2; ++j) { // need two passes for clean sweep
                for (int i = 0; i < len; ++i) {
                    ForkJoinWorkerThread w = ws[i];
                    if (w != null && w.sp != w.base)
                        return true;
                }
            }
        }
        return false;
    }

    // Support methods for ForkJoinTask

    /**
     * Returns an estimate of the number of tasks in the queue.
     */
    final int getQueueSize() {
        int n = sp - base;
        return n < 0? 0 : n; // suppress momentarily negative values
    }

    /**
     * Returns an estimate of the number of tasks, offset by a
     * function of number of idle workers.
     */
    final int getEstimatedSurplusTaskCount() {
        // The halving approximates weighting idle vs non-idle workers
        return (sp - base) - (pool.getIdleThreadCount() >>> 1);
    }

    /**
     * Scan, returning early if joinMe done
     */
    final ForkJoinTask<?> scanWhileJoining(ForkJoinTask<?> joinMe) {
        ForkJoinTask<?> t = pollTask();
        if (t != null && joinMe.status < 0 && sp == base) {
            pushTask(t); // unsteal if done and this task would be stealable
            t = null;
        }
        return t;
    }

    /**
     * Runs tasks until pool isQuiescent
     */
    final void helpQuiescePool() {
        for (;;) {
            ForkJoinTask<?> t = pollTask();
            if (t != null)
                t.quietlyExec();
            else if (tryInactivate() && pool.isQuiescent())
                break;
        }
        do;while (!tryActivate()); // re-activate on exit
    }

    // Temporary Unsafe mechanics for preliminary release
    private static Unsafe getUnsafe() throws Throwable {
        try {
            return Unsafe.getUnsafe();
        } catch (SecurityException se) {
            try {
                return java.security.AccessController.doPrivileged
                    (new java.security.PrivilegedExceptionAction<Unsafe>() {
                        public Unsafe run() throws Exception {
                            return getUnsafePrivileged();
                        }});
            } catch (java.security.PrivilegedActionException e) {
                throw e.getCause();
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            }
        }
    }

<<<<<<< HEAD
    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long ABASE;
    private static final int ASHIFT;

    static {
        int s;
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class a = ForkJoinTask[].class;
            ABASE = UNSAFE.arrayBaseOffset(a);
            s = UNSAFE.arrayIndexScale(a);
        } catch (Exception e) {
            throw new Error(e);
        }
        if ((s & (s-1)) != 0)
            throw new Error("data type scale not a power of two");
        ASHIFT = 31 - Integer.numberOfLeadingZeros(s);
    }

=======
    private static Unsafe getUnsafePrivileged()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    private static long fieldOffset(String fieldName)
            throws NoSuchFieldException {
        return _unsafe.objectFieldOffset
            (ForkJoinWorkerThread.class.getDeclaredField(fieldName));
    }

    static final Unsafe _unsafe;
    static final long baseOffset;
    static final long spOffset;
    static final long runStateOffset;
    static final long qBase;
    static final int qShift;
    static {
        try {
            _unsafe = getUnsafe();
            baseOffset = fieldOffset("base");
            spOffset = fieldOffset("sp");
            runStateOffset = fieldOffset("runState");
            qBase = _unsafe.arrayBaseOffset(ForkJoinTask[].class);
            int s = _unsafe.arrayIndexScale(ForkJoinTask[].class);
            if ((s & (s-1)) != 0)
                throw new Error("data type scale not a power of two");
            qShift = 31 - Integer.numberOfLeadingZeros(s);
        } catch (Throwable e) {
            throw new RuntimeException("Could not initialize intrinsics", e);
        }
    }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
