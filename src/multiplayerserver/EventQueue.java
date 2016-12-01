/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayerserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Mitchell
 */
public class EventQueue {
    
    private final ConcurrentLinkedQueue q;
    private final Lock lock = new ReentrantLock();
    
    /**
     * Constructs an event Queue.
     */
    public EventQueue() {
        q = new ConcurrentLinkedQueue();
    }

    /**
     * Returns the queue.
     * @return
     */
    public ConcurrentLinkedQueue getQ() {
        return q;
    }
    
    /**
     * This method implements the concept of pushing an item onto the queue.
     * @param me
     */
    public void push(Object me) {
        q.offer(me);
    }
    
    /**
     * This method implements the concept of popping the next item off the queue.
     * @return
     */
    public Object pop() {
        Object returnObj;
        
        // Locks the shared resource
        try {
            lock.tryLock(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception: " + ex.getMessage());
        }
        
        while ( q.isEmpty() ) {}
        
        returnObj = q.poll();
        System.out.println("Out to client: " + returnObj.toString());
        lock.unlock();
        return returnObj;
    }
    
    /**
     * Returns true if the queue is empty.
     * @return
     */
    public synchronized Boolean isEmpty() {
        return q.isEmpty();
    }
}
