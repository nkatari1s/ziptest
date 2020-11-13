package com.ziptest;

import java.util.Objects;

/**
 * Domain class to hold the single zip range i.e. startZipRange and endZipRange
 */
public class ZipcodeRange {

    private int startZipCodeRange;
    private int endZipCodeRange;

    public ZipcodeRange(int startZipCodeRange, int endZipCodeRange) {
        this.startZipCodeRange = startZipCodeRange;
        this.endZipCodeRange = endZipCodeRange;
    }

    public int getStartZipCodeRange() {
        return startZipCodeRange;
    }

    public int getEndZipCodeRange() {
        return endZipCodeRange;
    }

    public void setEndZipCodeRange(int endZipCodeRange) {
        this.endZipCodeRange = endZipCodeRange;
    }
}
