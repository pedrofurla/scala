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
 * A recursive result-bearing {@link ForkJoinTask}.
 *
 * <p>For a classic example, here is a task computing Fibonacci numbers:
 *
 *  <pre> {@code
 * class Fibonacci extends RecursiveTask<Integer> {
 *   final int n;
 *   Fibonacci(int n) { this.n = n; }
 *   Integer compute() {
 *     if (n <= 1)
=======
 * Recursive result-bearing ForkJoinTasks.
 * <p> For a classic example, here is a task computing Fibonacci numbers:
 *
 * <pre>
 * class Fibonacci extends RecursiveTask&lt;Integer&gt; {
 *   final int n;
 *   Fibonnaci(int n) { this.n = n; }
 *   Integer compute() {
 *     if (n &lt;= 1)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *        return n;
 *     Fibonacci f1 = new Fibonacci(n - 1);
 *     f1.fork();
 *     Fibonacci f2 = new Fibonacci(n - 2);
 *     return f2.compute() + f1.join();
 *   }
<<<<<<< HEAD
 * }}</pre>
=======
 * }
 * </pre>
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * However, besides being a dumb way to compute Fibonacci functions
 * (there is a simple fast linear algorithm that you'd use in
 * practice), this is likely to perform poorly because the smallest
 * subtasks are too small to be worthwhile splitting up. Instead, as
 * is the case for nearly all fork/join applications, you'd pick some
 * minimum granularity size (for example 10 here) for which you always
 * sequentially solve rather than subdividing.
 *
<<<<<<< HEAD
 * @since 1.7
 * @author Doug Lea
 */
public abstract class RecursiveTask<V> extends ForkJoinTask<V> {
    private static final long serialVersionUID = 5232453952276485270L;

    /**
     * The result of the computation.
=======
 */
public abstract class RecursiveTask<V> extends ForkJoinTask<V> {

    /**
     * Empty constructor for use by subclasses.
     */
    protected RecursiveTask() {
    }

    /**
     * The result returned by compute method.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    V result;

    /**
     * The main computation performed by this task.
     */
    protected abstract V compute();

    public final V getRawResult() {
        return result;
    }

    protected final void setRawResult(V value) {
        result = value;
    }

    /**
<<<<<<< HEAD
     * Implements execution conventions for RecursiveTask.
=======
     * Implements execution conventions for RecursiveTask
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    protected final boolean exec() {
        result = compute();
        return true;
    }

}
