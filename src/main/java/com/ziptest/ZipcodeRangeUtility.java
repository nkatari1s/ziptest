package com.ziptest;

import java.util.LinkedList;
import java.util.List;

/**
 * This is an Utility which class process the list of ZipCode Range provide by the user or system and
 * it validate the input provided by user or system and merge the zip code ranges which is required .
 *
 */
public class ZipcodeRangeUtility {

    /**
     * This method prepare valid zip code input, throws exception if its incorrect
     * @param zipcodeRanges
     * @return
     * @throws IllegalArgumentException
     */
    public List<ZipcodeRange> getValidCodeRanges(String zipcodeRanges) throws IllegalArgumentException {
        String [] zipCodeRanges = zipcodeRanges.split("\\s+");
        List<ZipcodeRange> zipcodesRanges = new LinkedList<ZipcodeRange>();
        for (String zipRange : zipCodeRanges) {
            String[] zip = zipRange.replaceAll("\\[|\\]", "").split(",");
            zipcodesRanges.add(validateAndGetZipCodeRange(zip));
        }
        return zipcodesRanges;
    }

    /**
     * Utility Method which validate zip code length and there sequence
     * @param zipCodeRanges
     * @return
     * @throws IllegalArgumentException
     */
    private ZipcodeRange validateAndGetZipCodeRange(String [] zipCodeRanges) throws IllegalArgumentException {
        if (zipCodeRanges.length != 2)
            throw new IllegalArgumentException(zipCodeRanges[0] + "Zipcode should have start and end range");
        int startRange = Integer.parseInt(zipCodeRanges[0]);
        int endRange = Integer.parseInt(zipCodeRanges[1]);
        ZipcodeRange zipcodeRange;
        validateZipcodeRange(startRange, endRange);
        zipcodeRange = new ZipcodeRange(startRange, endRange);
        return zipcodeRange;
    }

    /**
     * Utility method which validate the start and end range of zip code provided
     * @param startRange
     * @param endRange
     * @return
     */
    private boolean validateZipcodeRange(int startRange, int endRange) {
        if (!checkZipCodeLength(startRange) || !checkZipCodeLength(endRange))
            throw new IllegalArgumentException(startRange + " " + endRange + ": " + "Zipcode should have 5 digits");
        if (startRange > endRange)
            throw new IllegalArgumentException(
                    startRange + " " + endRange + ":  " + "Zipcode start Range should be less than end range");
        return true;
    }

    /**
     * validate the zip code length. The approach used is Dividing the power of 2 which is more performant
     *
     * @param zipcode
     * @return
     */
    private boolean checkZipCodeLength(int zipcode) {
        int length = 1;
        if (zipcode >= 100000000) {
            length += 8;
            zipcode /= 100000000;
        }
        if (zipcode >= 10000) {
            length += 4;
            zipcode /= 10000;
        }
        if (zipcode >= 100) {
            length += 2;
            zipcode /= 100;
        }
        if (zipcode >= 10) {
            length += 1;
        }
        if (length == 5)
            return true;
        else
            return false;
    }

    /**
     * Prepare the final zip code range list which can be used.
     * logic here used if endRange is falling between the start and end range in the linked list if it falls
     * then it rearrange them
     * @param zipcodeRanges
     * @return
     */
    public List<ZipcodeRange> mergeZipcodes(List<ZipcodeRange> zipcodeRanges) {
        List<ZipcodeRange> mergedZipcodeList = new LinkedList<>();
        ZipcodeRange zipcode = null;
        for (ZipcodeRange zipcodeInterval : zipcodeRanges) {
            if (zipcode == null)
                zipcode = zipcodeInterval;
            else {
                if (zipcode.getEndZipCodeRange() >= zipcodeInterval.getStartZipCodeRange()) {
                    zipcode.setEndZipCodeRange(Math.max(zipcode.getEndZipCodeRange(), zipcodeInterval.getEndZipCodeRange()));
                } else {
                    mergedZipcodeList.add(zipcode);
                    zipcode = zipcodeInterval;
                }
            }
        }
        mergedZipcodeList.add(zipcode);
        return mergedZipcodeList;
    }
}
