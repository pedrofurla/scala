/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
<<<<<<< HEAD
 * http://creativecommons.org/publicdomain/zero/1.0/
=======
 * http://creativecommons.org/licenses/publicdomain
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 */

package scala.concurrent.forkjoin;

/**
<<<<<<< HEAD
 * A recursive resultless {@link ForkJoinTask}.  This class
 * establishes conventions to parameterize resultless actions as
 * {@code Void} {@code ForkJoinTask}s. Because {@code null} is the
 * only valid value of type {@code Void}, methods such as join always
 * return {@code null} upon completion.
 *
 * <p><b>Sample Usages.</b> Here is a sketch of a ForkJoin sort that
 * sorts a given {@code long[]} array:
 *
 *  <pre> {@code
=======
 * Recursive resultless ForkJoinTasks. This class establishes
 * conventions to parameterize resultless actions as <tt>Void</tt>
 * ForkJoinTasks. Because <tt>null</tt> is the only valid value of
 * <tt>Void</tt>, methods such as join always return <tt>null</tt>
 * upon completion.
 *
 * <p><b>Sample Usages.</b> Here is a sketch of a ForkJoin sort that
 * sorts a given <tt>long[]</tt> array:
 *
 * <pre>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * class SortTask extends RecursiveAction {
 *   final long[] array; final int lo; final int hi;
 *   SortTask(long[] array, int lo, int hi) {
 *     this.array = array; this.lo = lo; this.hi = hi;
 *   }
 *   protected void compute() {
<<<<<<< HEAD
 *     if (hi - lo < THRESHOLD)
 *       sequentiallySort(array, lo, hi);
 *     else {
 *       int mid = (lo + hi) >>> 1;
=======
 *     if (hi - lo &lt; THRESHOLD)
 *       sequentiallySort(array, lo, hi);
 *     else {
 *       int mid = (lo + hi) &gt;&gt;&gt; 1;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *       invokeAll(new SortTask(array, lo, mid),
 *                 new SortTask(array, mid, hi));
 *       merge(array, lo, hi);
 *     }
 *   }
<<<<<<< HEAD
 * }}</pre>
 *
 * You could then sort {@code anArray} by creating {@code new
 * SortTask(anArray, 0, anArray.length-1) } and invoking it in a
 * ForkJoinPool.  As a more concrete simple example, the following
 * task increments each element of an array:
 *  <pre> {@code
=======
 * }
 * </pre>
 *
 * You could then sort anArray by creating <tt>new SortTask(anArray, 0,
 * anArray.length-1) </tt> and invoking it in a ForkJoinPool.
 * As a more concrete simple example, the following task increments
 * each element of an array:
 * <pre>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * class IncrementTask extends RecursiveAction {
 *   final long[] array; final int lo; final int hi;
 *   IncrementTask(long[] array, int lo, int hi) {
 *     this.array = array; this.lo = lo; this.hi = hi;
 *   }
 *   protected void compute() {
<<<<<<< HEAD
 *     if (hi - lo < THRESHOLD) {
 *       for (int i = lo; i < hi; ++i)
 *         array[i]++;
 *     }
 *     else {
 *       int mid = (lo + hi) >>> 1;
=======
 *     if (hi - lo &lt; THRESHOLD) {
 *       for (int i = lo; i &lt; hi; ++i)
 *         array[i]++;
 *     }
 *     else {
 *       int mid = (lo + hi) &gt;&gt;&gt; 1;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *       invokeAll(new IncrementTask(array, lo, mid),
 *                 new IncrementTask(array, mid, hi));
 *     }
 *   }
<<<<<<< HEAD
 * }}</pre>
=======
 * }
 * </pre>
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * <p>The following example illustrates some refinements and idioms
 * that may lead to better performance: RecursiveActions need not be
 * fully recursive, so long as they maintain the basic
 * divide-and-conquer approach. Here is a class that sums the squares
 * of each element of a double array, by subdividing out only the
 * right-hand-sides of repeated divisions by two, and keeping track of
<<<<<<< HEAD
 * them with a chain of {@code next} references. It uses a dynamic
 * threshold based on method {@code getSurplusQueuedTaskCount}, but
 * counterbalances potential excess partitioning by directly
 * performing leaf actions on unstolen tasks rather than further
 * subdividing.
 *
 *  <pre> {@code
 * double sumOfSquares(ForkJoinPool pool, double[] array) {
 *   int n = array.length;
 *   Applyer a = new Applyer(array, 0, n, null);
=======
 * them with a chain of <tt>next</tt> references. It uses a dynamic
 * threshold based on method <tt>surplus</tt>, but counterbalances
 * potential excess partitioning by directly performing leaf actions
 * on unstolen tasks rather than further subdividing.
 *
 * <pre>
 * double sumOfSquares(ForkJoinPool pool, double[] array) {
 *   int n = array.length;
 *   int seqSize = 1 + n / (8 * pool.getParallelism());
 *   Applyer a = new Applyer(array, 0, n, seqSize, null);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *   pool.invoke(a);
 *   return a.result;
 * }
 *
 * class Applyer extends RecursiveAction {
 *   final double[] array;
<<<<<<< HEAD
 *   final int lo, hi;
 *   double result;
 *   Applyer next; // keeps track of right-hand-side tasks
 *   Applyer(double[] array, int lo, int hi, Applyer next) {
 *     this.array = array; this.lo = lo; this.hi = hi;
 *     this.next = next;
 *   }
 *
 *   double atLeaf(int l, int h) {
 *     double sum = 0;
 *     for (int i = l; i < h; ++i) // perform leftmost base step
=======
 *   final int lo, hi, seqSize;
 *   double result;
 *   Applyer next; // keeps track of right-hand-side tasks
 *   Applyer(double[] array, int lo, int hi, int seqSize, Applyer next) {
 *     this.array = array; this.lo = lo; this.hi = hi;
 *     this.seqSize = seqSize; this.next = next;
 *   }
 *
 *   double atLeaf(int l, int r) {
 *     double sum = 0;
 *     for (int i = l; i &lt; h; ++i) // perform leftmost base step
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *       sum += array[i] * array[i];
 *     return sum;
 *   }
 *
 *   protected void compute() {
 *     int l = lo;
 *     int h = hi;
 *     Applyer right = null;
<<<<<<< HEAD
 *     while (h - l > 1 && getSurplusQueuedTaskCount() <= 3) {
 *        int mid = (l + h) >>> 1;
 *        right = new Applyer(array, mid, h, right);
=======
 *     while (h - l &gt; 1 &amp;&amp;
 *        ForkJoinWorkerThread.getEstimatedSurplusTaskCount() &lt;= 3) {
 *        int mid = (l + h) &gt;&gt;&gt; 1;
 *        right = new Applyer(array, mid, h, seqSize, right);
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *        right.fork();
 *        h = mid;
 *     }
 *     double sum = atLeaf(l, h);
 *     while (right != null) {
 *        if (right.tryUnfork()) // directly calculate if not stolen
 *          sum += right.atLeaf(right.lo, right.hi);
 *       else {
<<<<<<< HEAD
 *          right.join();
=======
 *          right.helpJoin();
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *          sum += right.result;
 *        }
 *        right = right.next;
 *      }
 *     result = sum;
 *   }
<<<<<<< HEAD
 * }}</pre>
 *
 * @since 1.7
 * @author Doug Lea
 */
public abstract class RecursiveAction extends ForkJoinTask<Void> {
    private static final long serialVersionUID = 5232453952276485070L;
=======
 * }
 * </pre>
 */
public abstract class RecursiveAction extends ForkJoinTask<Void> {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

    /**
     * The main computation performed by this task.
     */
    protected abstract void compute();

    /**
<<<<<<< HEAD
     * Always returns {@code null}.
     *
     * @return {@code null} always
=======
     * Always returns null
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    public final Void getRawResult() { return null; }

    /**
     * Requires null completion value.
     */
    protected final void setRawResult(Void mustBeNull) { }

    /**
<<<<<<< HEAD
     * Implements execution conventions for RecursiveActions.
=======
     * Implements execution conventions for RecursiveActions
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    protected final boolean exec() {
        compute();
        return true;
    }

}
