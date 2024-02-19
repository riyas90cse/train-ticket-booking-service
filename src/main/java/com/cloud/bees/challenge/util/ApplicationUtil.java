package com.cloud.bees.challenge.util;

import org.apache.commons.lang3.RandomStringUtils;

public class ApplicationUtil {

    private ApplicationUtil() {
        //Do Nothing - Added to Solve implicit public constructor Issue on SonarLint
    }

    public static String generateTrainNo() {
        String prefix = "TR_";
        int length = 3;
        return generateRandomStringWithPrefix(prefix, length);
    }

    public static String generateSeatNo(String sectionName) {
        String prefix = "SC_" + sectionName + "_";
        int length = 3;
        return generateRandomStringWithPrefix(prefix, length);
    }

    public static String generateTicketNo() {
        String prefix = "TK_";
        int length = 5;
        return generateRandomStringWithPrefix(prefix, length);
    }

    public static String generateConfirmationCode() {
        String prefix = "CNF_";
        int length = 5;
        return generateRandomStringWithPrefix(prefix, length);
    }

    public static String generateRandomStringWithPrefix(String prefix, int length) {
        String randomString = RandomStringUtils.random(length, false, true);
        return prefix + randomString;
    }

}
