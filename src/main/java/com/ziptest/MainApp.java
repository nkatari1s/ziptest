package com.ziptest;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This is the main application start file.
 *
 * While running the application please provide the input as following format
 * [11111,11111] [12345,12360] ....
 */
public class MainApp {
    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        logger.info("Please enter the zip code ranges in format like [11111,11111] [12345,12360]");
        String zipCodeRanges = scanner.nextLine();

        logger.info("zip codes range enter by user / system "+ zipCodeRanges);
        ZipcodeRangeUtility utility = new ZipcodeRangeUtility();

        try {
            logger.info("Validating the zip code ranges enter by user");
            List<ZipcodeRange> validCodeRanges = utility.getValidCodeRanges(zipCodeRanges);
            logger.info("Validation of the zip code ranges finished");

            validCodeRanges.sort(Comparator.comparingInt(ZipcodeRange::getStartZipCodeRange));
            logger.info("Merging the zip code ranges started");
            List<ZipcodeRange> ranges = utility.mergeZipcodes(validCodeRanges);
            logger.info("Merging the zip code ranges finished");

            logger.info("Following is the zip code ranges after merging.");
            ranges.stream().forEach(e -> System.out.println("[" + e.getStartZipCodeRange() + "," + e.getEndZipCodeRange() + "]"));
        } catch (IllegalArgumentException e) {
            logger.warning(e.getMessage());
        }
    }
}
