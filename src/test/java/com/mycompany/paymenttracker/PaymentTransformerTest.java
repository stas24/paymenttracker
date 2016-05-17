/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paymenttracker;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Stanislav
 */
public class PaymentTransformerTest {
    
    PaymentTransformer transformer;
    
    @Before
    public void before() {
        transformer = new PaymentTransformer();
    }
    
    @Test
    public void shouldTransformPayment() throws Exception {
        Payment payment = transformer.transform("USD 100");
        assertEquals(payment.getAmount().toString(), "100");
        assertEquals(payment.getCurrency(), "USD");
    }
    
    @Test(expected=Exception.class)
    public void shouldThrowException() throws Exception {
        transformer.transform("USD RUB");
    }

}
