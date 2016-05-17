/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paymenttracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author Stanislav
 */
public class PersistenceManager {
    
    private final HashMap<String, BigDecimal> summary = new HashMap<>();
    
    public synchronized void persist(Payment payment) {
        if ( ! payment.getAmount().equals(BigDecimal.ZERO)) {
            if (summary.containsKey(payment.getCurrency())) {
                summary.put(payment.getCurrency(), summary.get(payment.getCurrency()).add(payment.getAmount()) );
            } else {
                summary.put(payment.getCurrency(), payment.getAmount());
            }
        }
    }
    
    public void persist(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line;
        PaymentTransformer paymentTransformer = new PaymentTransformer();
        
        while ((line = in.readLine()) != null) {
            try {
                Payment payment = paymentTransformer.transform(line);
                persist(payment);
            } catch (Exception e) {
                System.out.println("Wrong format!");
            }
        }
    }
    
    public void out(PrintStream stream) {
        for(Map.Entry e: summary.entrySet()) {
            if (!e.getValue().equals(BigDecimal.ZERO)) {
                stream.printf("%s\t%s\n", e.getKey(), e.getValue().toString());
            }
        }
    }
    
}
