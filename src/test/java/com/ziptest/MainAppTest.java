package com.ziptest;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class MainAppTest {

    @Test
    public void testMain() {
        System.out.println("Testing main method");
        String[] args = null;
        final InputStream original = System.in;
        String zipCodeRanges = "[94133,94133] [94200,94299] [94600,94699]";
        byte [] zipBytes = zipCodeRanges.getBytes();
        InputStream fips = new ByteArrayInputStream(zipBytes);
        System.setIn(fips);
        MainApp.main(args);
        System.setIn(original);
    }

}