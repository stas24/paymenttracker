/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paymenttracker;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Stanislav
 */
public class PaymentTracker {
    public static void main(String[] args) {
        final PaymentTransformer paymentTransformer = new PaymentTransformer();
        final PersistenceManager persistenceManager = new PersistenceManager();
        
        if (args.length > 0) {
            try {
                persistenceManager.persist(new FileInputStream(new File(args[0])));
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                persistenceManager.out(System.out);
            }
        }, 60*1000, 60*1000);
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if ("quit".equals(line)) {
                break;
            }         
            try {
                Payment payment = paymentTransformer.transform(line);
                persistenceManager.persist(payment);
            } catch(Exception e) {
                System.out.println("Wrong format!");
            }                   
        }
        timer.cancel();
    }
}
