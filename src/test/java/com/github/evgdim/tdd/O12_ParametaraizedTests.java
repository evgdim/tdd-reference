package com.github.evgdim.tdd;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.github.evgdim.tdd.utils.Calculator;

@RunWith(Parameterized.class)
public class O12_ParametaraizedTests {
    @Parameters(name="{index}: sum of ({0} and {1}) should be ={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { 0, 0, 0 }, { 1, 1, 2 }, { 2, 1,3 }, { 3, 2, 5 }, { 4, 3, 7 }, { 5, 5, 10 }, { 6, 8, 14 }  
           });
    }

    @Parameter(0)
    public int a;

    @Parameter(1)
    public int b;
    
    @Parameter(2)
    public int expectedSum;

    @Test
    public void test() {
        assertEquals(expectedSum, Calculator.add(a, b));
    }
}