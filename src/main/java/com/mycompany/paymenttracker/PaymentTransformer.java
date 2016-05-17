/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paymenttracker;

import java.math.BigDecimal;

/**
 *
 * @author Stanislav
 */
public class PaymentTransformer {
    public Payment transform(String line) throws Exception
    {
        try {
            String[] parameters = line.split(" ", 2);
            return new Payment(parameters[0], new BigDecimal(parameters[1]));
        } catch(NumberFormatException e) {
            throw new Exception(e);
        }
    }
}