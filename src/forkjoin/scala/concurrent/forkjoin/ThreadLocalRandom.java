/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
<<<<<<< HEAD
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package scala.concurrent.forkjoin;

import java.util.Random;

/**
 * A random number generator isolated to the current thread.  Like the
 * global {@link java.util.Random} generator used by the {@link
 * java.lang.Math} class, a {@code ThreadLocalRandom} is initialized
 * with an internally generated seed that may not otherwise be
 * modified. When applicable, use of {@code ThreadLocalRandom} rather
 * than shared {@code Random} objects in concurrent programs will
 * typically encounter much less overhead and contention.  Use of
 * {@code ThreadLocalRandom} is particularly appropriate when multiple
 * tasks (for example, each a {@link ForkJoinTask}) use random numbers
 * in parallel in thread pools.
 *
 * <p>Usages of this class should typically be of the form:
 * {@code ThreadLocalRandom.current().nextX(...)} (where
 * {@code X} is {@code Int}, {@code Long}, etc).
 * When all usages are of this form, it is never possible to
 * accidently share a {@code ThreadLocalRandom} across multiple threads.
 *
 * <p>This class also provides additional commonly used bounded random
 * generation methods.
 *
 * @since 1.7
 * @author Doug Lea
 */
public class ThreadLocalRandom extends Random {
    // same constants as Random, but must be redeclared because private
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    /**
     * The random seed. We can't use super.seed.
=======
 * http://creativecommons.org/licenses/publicdomain
 */

package scala.concurrent.forkjoin;
import java.util.*;

/**
 * A random number generator with the same properties as class {@link
 * Random} but isolated to the current Thread.  Like the global
 * generator used by the {@link java.lang.Math} class, a
 * ThreadLocalRandom is initialized with an internally generated seed
 * that may not otherwise be modified. When applicable, use of
 * ThreadLocalRandom rather than shared Random objects in concurrent
 * programs will typically encounter much less overhead and
 * contention.  ThreadLocalRandoms are particularly appropriate when
 * multiple tasks (for example, each a {@link ForkJoinTask}), use
 * random numbers in parallel in thread pools.
 *
 * <p>Usages of this class should typically be of the form:
 * <code>ThreadLocalRandom.current().nextX(...)</code> (where
 * <code>X</code> is <code>Int</code>, <code>Long</code>, etc).
 * When all usages are of this form, it is never possible to
 * accidently share ThreadLocalRandoms across multiple threads.
 *
 * <p>This class also provides additional commonly used bounded random
 * generation methods.
 */
public class ThreadLocalRandom extends Random {
    // same constants as Random, but must be redeclared because private
    private final static long multiplier = 0x5DEECE66DL;
    private final static long addend = 0xBL;
    private final static long mask = (1L << 48) - 1;

    /**
     * The random seed. We can't use super.seed
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    private long rnd;

    /**
<<<<<<< HEAD
     * Initialization flag to permit calls to setSeed to succeed only
     * while executing the Random constructor.  We can't allow others
     * since it would cause setting seed in one part of a program to
     * unintentionally impact other usages by the thread.
=======
     * Initialization flag to permit the first and only allowed call
     * to setSeed (inside Random constructor) to succeed.  We can't
     * allow others since it would cause setting seed in one part of a
     * program to unintentionally impact other usages by the thread.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    boolean initialized;

    // Padding to help avoid memory contention among seed updates in
    // different TLRs in the common case that they are located near
    // each other.
    private long pad0, pad1, pad2, pad3, pad4, pad5, pad6, pad7;

    /**
     * The actual ThreadLocal
     */
    private static final ThreadLocal<ThreadLocalRandom> localRandom =
        new ThreadLocal<ThreadLocalRandom>() {
            protected ThreadLocalRandom initialValue() {
                return new ThreadLocalRandom();
            }
    };


    /**
     * Constructor called only by localRandom.initialValue.
<<<<<<< HEAD
     */
    ThreadLocalRandom() {
        super();
        initialized = true;
    }

    /**
     * Returns the current thread's {@code ThreadLocalRandom}.
     *
     * @return the current thread's {@code ThreadLocalRandom}
=======
     * We rely on the fact that the superclass no-arg constructor
     * invokes setSeed exactly once to initialize.
     */
    ThreadLocalRandom() {
        super();
    }

    /**
     * Returns the current Thread's ThreadLocalRandom
     * @return the current Thread's ThreadLocalRandom
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    public static ThreadLocalRandom current() {
        return localRandom.get();
    }

    /**
<<<<<<< HEAD
     * Throws {@code UnsupportedOperationException}.  Setting seeds in
     * this generator is not supported.
     *
=======
     * Throws UnsupportedOperationException. Setting seeds in this
     * generator is unsupported.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @throws UnsupportedOperationException always
     */
    public void setSeed(long seed) {
        if (initialized)
            throw new UnsupportedOperationException();
<<<<<<< HEAD
=======
        initialized = true;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        rnd = (seed ^ multiplier) & mask;
    }

    protected int next(int bits) {
<<<<<<< HEAD
        rnd = (rnd * multiplier + addend) & mask;
        return (int) (rnd >>> (48-bits));
=======
        return (int)((rnd = (rnd * multiplier + addend) & mask) >>> (48-bits));
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    /**
     * Returns a pseudorandom, uniformly distributed value between the
     * given least value (inclusive) and bound (exclusive).
<<<<<<< HEAD
     *
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param least the least value returned
     * @param bound the upper bound (exclusive)
     * @throws IllegalArgumentException if least greater than or equal
     * to bound
     * @return the next value
     */
    public int nextInt(int least, int bound) {
        if (least >= bound)
            throw new IllegalArgumentException();
        return nextInt(bound - least) + least;
    }

    /**
     * Returns a pseudorandom, uniformly distributed value
<<<<<<< HEAD
     * between 0 (inclusive) and the specified value (exclusive).
     *
=======
     * between 0 (inclusive) and the specified value (exclusive)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param n the bound on the random number to be returned.  Must be
     *        positive.
     * @return the next value
     * @throws IllegalArgumentException if n is not positive
     */
    public long nextLong(long n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        // Divide n by two until small enough for nextInt. On each
        // iteration (at most 31 of them but usually much less),
        // randomly choose both whether to include high bit in result
        // (offset) and whether to continue with the lower vs upper
        // half (which makes a difference only if odd).
        long offset = 0;
        while (n >= Integer.MAX_VALUE) {
            int bits = next(2);
            long half = n >>> 1;
<<<<<<< HEAD
            long nextn = ((bits & 2) == 0) ? half : n - half;
=======
            long nextn = ((bits & 2) == 0)? half : n - half;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
            if ((bits & 1) == 0)
                offset += n - nextn;
            n = nextn;
        }
<<<<<<< HEAD
        return offset + nextInt((int) n);
=======
        return offset + nextInt((int)n);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    }

    /**
     * Returns a pseudorandom, uniformly distributed value between the
     * given least value (inclusive) and bound (exclusive).
<<<<<<< HEAD
     *
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param least the least value returned
     * @param bound the upper bound (exclusive)
     * @return the next value
     * @throws IllegalArgumentException if least greater than or equal
     * to bound
     */
    public long nextLong(long least, long bound) {
        if (least >= bound)
            throw new IllegalArgumentException();
        return nextLong(bound - least) + least;
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code double} value
<<<<<<< HEAD
     * between 0 (inclusive) and the specified value (exclusive).
     *
=======
     * between 0 (inclusive) and the specified value (exclusive)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param n the bound on the random number to be returned.  Must be
     *        positive.
     * @return the next value
     * @throws IllegalArgumentException if n is not positive
     */
    public double nextDouble(double n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        return nextDouble() * n;
    }

    /**
     * Returns a pseudorandom, uniformly distributed value between the
     * given least value (inclusive) and bound (exclusive).
<<<<<<< HEAD
     *
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @param least the least value returned
     * @param bound the upper bound (exclusive)
     * @return the next value
     * @throws IllegalArgumentException if least greater than or equal
     * to bound
     */
    public double nextDouble(double least, double bound) {
        if (least >= bound)
            throw new IllegalArgumentException();
        return nextDouble() * (bound - least) + least;
    }

<<<<<<< HEAD
    private static final long serialVersionUID = -5851777807851030925L;
=======
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
