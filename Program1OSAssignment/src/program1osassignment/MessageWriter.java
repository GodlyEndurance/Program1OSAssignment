/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program1osassignment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author trinhpham
 */
class MessageWriter implements Runnable {
    private final int id;
    private final int delayMillis;
    private final AtomicInteger sharedCounter;   // Exercise 5: shared counter
    
    private final int limitMessages = 5; // This should make it so it would be: # of messages = limitMessages * # of threads

    /**
     * Update the code so that MessageWriter takes a parameter indicating how long to delay instead of always delaying 1 second.  
     * Pass 1 second to the first thread, 2 seconds to the second thread, and so on.
     * @param id The # or label that particular thread is.
     * @param delayMillis The second(s) of delay, based on the id.
     * @param sharedCounter The shared counter that Exercise 5 wanted.
     */
    public MessageWriter(int id, int delayMillis, AtomicInteger sharedCounter) {
        this.id = id;
        this.delayMillis = delayMillis;
        this.sharedCounter = sharedCounter;
    }

    /**
     * Required function to use the interface Runnable.
     */
    @Override
    public void run() {
        // Each thread prints 5 messages (arbitrary number)
        
        
        
        for (int i = 0; i < limitMessages; i++) {
            
            
            // Atomically increment and get the shared counter (for the incrementAndGet() function of the AtomicInteger library)
            int currentCount = sharedCounter.incrementAndGet();
            
            System.out.println("Thread " + id + ": Message " + currentCount);
            

            
            // Exception checking for the delay.
            try {
                // Previously 1 , but now it depends on which # of thread sends the message. E.g., Thread 4 = 4 seconds of delay
                Thread.sleep(delayMillis);
                System.out.println("Delay of current thread: " + delayMillis/1000);
                
            } catch (InterruptedException e) {
                // Thread was interrupted – exit gracefully
                System.out.println("Thread " + id + " interrupted.");
                break;
            }
        }
        System.out.println("Delay of current thread: " + delayMillis/1000);
        
    }
}