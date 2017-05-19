package com.unittest.utils;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(MyRunner.class)
public class JunitSample {
    @Test
    public void testListener(){
    	assertTrue(true);
    }

    @Test
    public void testFalseAssertion(){
        assertTrue(false);
    }

    @Ignore
    @Test
    public void testIgnore(){

    }

    @Test
    public void testException(){
        throw new RuntimeException();
    }
}
