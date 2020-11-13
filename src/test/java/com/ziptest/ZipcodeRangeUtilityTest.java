package com.ziptest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZipcodeRangeUtilityTest {

    ZipcodeRangeUtility utility;
    @BeforeEach
     void prepare() {
        utility = new ZipcodeRangeUtility();
    }
    @Test
    void shouldAbleToPrepareValidInput() {
        String zipCodeRanges = "[94133,94133] [94200,94299] [94600,94699]";
        List<ZipcodeRange> validCodeRanges = utility.getValidCodeRanges(zipCodeRanges);
        assertFalse(validCodeRanges.isEmpty());
        assertTrue(validCodeRanges.size() == 3);
        assertTrue(validCodeRanges.get(0).getStartZipCodeRange() == 94133);
        assertTrue(validCodeRanges.get(0).getEndZipCodeRange() == 94133);
    }

    @Test
    void shouldFailAbleToPrepareValidInput() {
        String zipCodeRanges = "[94133,92478] [94200,94299] [94600,94699]";
        assertThrows(IllegalArgumentException.class, () -> utility.getValidCodeRanges(zipCodeRanges));
    }

    @Test
    void shouldFailForInValidZip() {
        String zipCodeRanges = "[94133,94133] [942002,94299] [94600,94699]";
        assertThrows(IllegalArgumentException.class, () -> utility.getValidCodeRanges(zipCodeRanges));
    }

    @Test
    void shouldFailForInValidZipRange() {
        String zipCodeRanges = "[94133,94133] [94299, 94200] [94600,94699]";
        assertThrows(IllegalArgumentException.class, () -> utility.getValidCodeRanges(zipCodeRanges));

        String zipCodeRangesLessThan5 = "[94133,94133] [9420,94299] [94600,94699]";
        assertThrows(IllegalArgumentException.class, () -> utility.getValidCodeRanges(zipCodeRangesLessThan5));
    }
    @Test
    void shouldAbleToMergeZipCodes() {
        String zipCodeRanges = "[94133,94133] [94200,94299] [94226,94399]";
        List<ZipcodeRange> validCodeRanges = utility.getValidCodeRanges(zipCodeRanges);
        assertNotNull(validCodeRanges);
        assertEquals(validCodeRanges.size(), 3);
        validCodeRanges.sort(Comparator.comparingInt(ZipcodeRange::getStartZipCodeRange));
        List<ZipcodeRange> mergeZipcodes = utility.mergeZipcodes(validCodeRanges);

        assertNotNull(mergeZipcodes);
        assertFalse(mergeZipcodes.isEmpty());
        assertEquals(mergeZipcodes.size(), 2);
        assertEquals(mergeZipcodes.get(0).getStartZipCodeRange(), 94133);
        assertEquals(mergeZipcodes.get(0).getEndZipCodeRange(), 94133);
        assertEquals(mergeZipcodes.get(1).getStartZipCodeRange(), 94200);
        assertEquals(mergeZipcodes.get(1).getEndZipCodeRange(), 94399);

    }
}