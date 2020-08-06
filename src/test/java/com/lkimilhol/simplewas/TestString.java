package com.lkimilhol.simplewas;

import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestString {
    @Test
    public void TestStringWriter(){
        String test = "test";
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(test);
        assertEquals(test, stringWriter.toString());
    }
}
