/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
<<<<<<< HEAD
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package scala.concurrent.forkjoin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
=======
 * http://creativecommons.org/licenses/publicdomain
 */

package scala.concurrent.forkjoin;
import java.util.concurrent.*;
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0

/**
 * A {@link BlockingQueue} in which producers may wait for consumers
 * to receive elements.  A {@code TransferQueue} may be useful for
 * example in message passing applications in which producers
<<<<<<< HEAD
 * sometimes (using method {@link #transfer}) await receipt of
 * elements by consumers invoking {@code take} or {@code poll}, while
 * at other times enqueue elements (via method {@code put}) without
 * waiting for receipt.
 * {@linkplain #tryTransfer(Object) Non-blocking} and
 * {@linkplain #tryTransfer(Object,long,TimeUnit) time-out} versions of
 * {@code tryTransfer} are also available.
 * A {@code TransferQueue} may also be queried, via {@link
 * #hasWaitingConsumer}, whether there are any threads waiting for
 * items, which is a converse analogy to a {@code peek} operation.
 *
 * <p>Like other blocking queues, a {@code TransferQueue} may be
 * capacity bounded.  If so, an attempted transfer operation may
 * initially block waiting for available space, and/or subsequently
 * block waiting for reception by a consumer.  Note that in a queue
 * with zero capacity, such as {@link SynchronousQueue}, {@code put}
 * and {@code transfer} are effectively synonymous.
=======
 * sometimes (using method {@code transfer}) await receipt of
 * elements by consumers invoking {@code take} or {@code poll},
 * while at other times enqueue elements (via method {@code put})
 * without waiting for receipt. Non-blocking and time-out versions of
 * {@code tryTransfer} are also available.  A TransferQueue may also
 * be queried via {@code hasWaitingConsumer} whether there are any
 * threads waiting for items, which is a converse analogy to a
 * {@code peek} operation.
 *
 * <p>Like any {@code BlockingQueue}, a {@code TransferQueue} may be
 * capacity bounded. If so, an attempted {@code transfer} operation
 * may initially block waiting for available space, and/or
 * subsequently block waiting for reception by a consumer.  Note that
 * in a queue with zero capacity, such as {@link SynchronousQueue},
 * {@code put} and {@code transfer} are effectively synonymous.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.7
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */
public interface TransferQueue<E> extends BlockingQueue<E> {
    /**
<<<<<<< HEAD
     * Transfers the element to a waiting consumer immediately, if possible.
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * otherwise returning {@code false} without enqueuing the element.
=======
     * Transfers the specified element if there exists a consumer
     * already waiting to receive it, otherwise returning {@code false}
     * without enqueuing the element.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *
     * @param e the element to transfer
     * @return {@code true} if the element was transferred, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    boolean tryTransfer(E e);

    /**
<<<<<<< HEAD
     * Transfers the element to a consumer, waiting if necessary to do so.
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * else waits until the element is received by a consumer.
     *
     * @param e the element to transfer
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not left enqueued
=======
     * Inserts the specified element into this queue, waiting if
     * necessary for space to become available and the element to be
     * dequeued by a consumer invoking {@code take} or {@code poll}.
     *
     * @param e the element to transfer
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not enqueued.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    void transfer(E e) throws InterruptedException;

    /**
<<<<<<< HEAD
     * Transfers the element to a consumer if it is possible to do so
     * before the timeout elapses.
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * else waits until the element is received by a consumer,
     * returning {@code false} if the specified wait time elapses
     * before the element can be transferred.
=======
     * Inserts the specified element into this queue, waiting up to
     * the specified wait time if necessary for space to become
     * available and the element to be dequeued by a consumer invoking
     * {@code take} or {@code poll}.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     *
     * @param e the element to transfer
     * @param timeout how long to wait before giving up, in units of
     *        {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the
     *        {@code timeout} parameter
     * @return {@code true} if successful, or {@code false} if
     *         the specified waiting time elapses before completion,
<<<<<<< HEAD
     *         in which case the element is not left enqueued
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not left enqueued
=======
     *         in which case the element is not enqueued.
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not enqueued.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    boolean tryTransfer(E e, long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Returns {@code true} if there is at least one consumer waiting
<<<<<<< HEAD
     * to receive an element via {@link #take} or
     * timed {@link #poll(long,TimeUnit) poll}.
=======
     * to dequeue an element via {@code take} or {@code poll}.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * The return value represents a momentary state of affairs.
     *
     * @return {@code true} if there is at least one waiting consumer
     */
    boolean hasWaitingConsumer();

    /**
     * Returns an estimate of the number of consumers waiting to
<<<<<<< HEAD
     * receive elements via {@link #take} or timed
     * {@link #poll(long,TimeUnit) poll}.  The return value is an
     * approximation of a momentary state of affairs, that may be
     * inaccurate if consumers have completed or given up waiting.
     * The value may be useful for monitoring and heuristics, but
     * not for synchronization control.  Implementations of this
     * method are likely to be noticeably slower than those for
     * {@link #hasWaitingConsumer}.
     *
     * @return the number of consumers waiting to receive elements
=======
     * dequeue elements via {@code take} or {@code poll}. The return
     * value is an approximation of a momentary state of affairs, that
     * may be inaccurate if consumers have completed or given up
     * waiting. The value may be useful for monitoring and heuristics,
     * but not for synchronization control. Implementations of this
     * method are likely to be noticeably slower than those for
     * {@link #hasWaitingConsumer}.
     *
     * @return the number of consumers waiting to dequeue elements
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
    int getWaitingConsumerCount();
}
