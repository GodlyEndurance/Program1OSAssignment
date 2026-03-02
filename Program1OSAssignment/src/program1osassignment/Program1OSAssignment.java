/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package program1osassignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author trinhpham
 */
public class Program1OSAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // Exercise 3 (modified): Get number of threads from user input (interactive)
        int numThreads = 0;
        Scanner scanner = new Scanner(System.in);

        // Loop until a valid positive integer is entered
        /**
         * Note: Exercise 3 wants as many threads asked by the user, so we do not cap it. 
         */
        
        numThreads = getNumThreadsFromUser(numThreads);
        

        // Exercise 5: Shared counter (thread-safe)
        AtomicInteger sharedCounter = new AtomicInteger(0);

        List<Thread> threads = new ArrayList<>();
        
        int delaySeconds = 0;

        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            // Exercise 4: Delay = (i+1) seconds
            delaySeconds = i + 1;
            
            MessageWriter writer = new MessageWriter(i + 1, delaySeconds * 1000, sharedCounter);
            Thread t = new Thread(writer);
            t.start();
            threads.add(t);
        }

        // Wait for all threads to finish 
        try {
            for (Thread t : threads) {
                t.join();
                
            }
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting.");
        }

        // System.exit(0);   // (Optional – now it's safe to uncomment)
    }
    
    // Static method – can be called directly from main
    private static int getNumThreadsFromUser(int numThreads) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Note that the number of messages per thread is an arbitrary value of 5.\n");
        System.out.print("Note that when we delay a thread, other threads can still operate. Initially, they are not delayed.\n");
        
        
        while (numThreads <= 0) {
            System.out.print("Enter the number of threads to create: ");
            if (scanner.hasNextInt()) {
                numThreads = scanner.nextInt();
                if (numThreads <= 0) {
                    System.out.println("Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        scanner.close();
        return numThreads;
    }
    
}
