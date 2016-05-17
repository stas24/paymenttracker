/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paymenttracker;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Stanislav
 */
public class PersistenceManagerTest {
    
    PersistenceManager manager;
    
    @Before
    public void before() {
        manager = new PersistenceManager();
    }
    
    @Test
    public void testSummary() throws Exception {
        PaymentTransformer transformer = new PaymentTransformer();
        manager.clear();
        
        manager.persist(transformer.transform("USD 100"));
        manager.persist(transformer.transform("USD -200"));
        manager.persist(transformer.transform("USD 100"));
        
        assertEquals(manager.getSummary().get("USD"), BigDecimal.ZERO);
    }
}
