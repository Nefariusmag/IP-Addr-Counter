package com.yourcodereview.ipcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.net.Inet4Address;

/**
 * This application start counting unique IP Addresses from a file. Path to the file get from the first argument,
 * also can enable validate syntax the IP Addresses from the file using the second argument.
 */
public class App
{
    private static final int NUMBER_ARGUMENTS_FOR_ENABLE_IP_VALIDATE = 2;
    public static void main( String[] args ) {

        if (!validateLengthArguments(args.length))
            System.exit(1);

        boolean validateTrue = false;
        if (args.length == NUMBER_ARGUMENTS_FOR_ENABLE_IP_VALIDATE)
            validateTrue = Boolean.parseBoolean(args[1]);

        long start = System.currentTimeMillis();
        String sourceFileName = args[0];
        try {
            System.out.println("Distinct IP Addresses: "
                    + getCountIPAddressFromFileByBitSet(sourceFileName, validateTrue));
        } catch (IOException e) {
            System.out.println("Couldn't have access to the file or the file not exist.");
        }

        System.out.println("Time spent: " + (System.currentTimeMillis() - start) / 1000 + "s") ;
    }

    /**
     * Get count IP address from file using BitSet.
     *
     * @param path need get full path to file with IP addresses
     * @param validateTrue enable or disable validating IP address
     * @throws IOException when have problem with access to the file
     */
    public static long getCountIPAddressFromFileByBitSet(String path, boolean validateTrue) throws IOException {
        IPCounter counter = new IPCounter();
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        String ipAddress;
        int countSkippedLines = 0;
        int countWrongIPAddresses = 0;
        while ((ipAddress = reader.readLine()) != null) {
            if (validateTrue && !thisIsTrueIPAddress(ipAddress)) {
                countWrongIPAddresses++;
                continue;
            }
            try {
                counter.add(ipAddress);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                countSkippedLines++;
            }
        }
        reader.close();
        if (countSkippedLines != 0) {
            System.out.println("Number lines couldn't be parsed: " + countSkippedLines);
        }
        if (countWrongIPAddresses != 0) {
            System.out.println("Number wrong IP Addresses: " + countWrongIPAddresses);
        }
        return counter.count();
    }

    /**
     * Validate number arguments for the application, check have argument for path to file
     * and for enable/disable validate IP (optional)
     *
     * @param lengthArg number arguments for the application
     * @return have enough arguments or not
     */
    public static boolean validateLengthArguments(int lengthArg) {
        if (lengthArg < 1 || lengthArg > 2) {
            System.out.println("Need only two argument: the first with path to file with IP Addresses " +
                    "and the second with True or False to enable or disable validate IP addresses " +
                    "(All argument with wrong value will be converted to False");
            return false;
        }
        return true;
    }

    /**
     * Validate IP address is true or not.
     *
     * @param ipAddress the one IP address want to check
     * @return the IP address is true or not
     */
    public static boolean thisIsTrueIPAddress(String ipAddress) {
        try {
            return Inet4Address.getByName(ipAddress)
                    .getHostAddress().equals(ipAddress);
        }
        catch (UnknownHostException ex) {
            return false;
        }
    }
}
