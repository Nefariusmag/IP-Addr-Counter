package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.net.Inet4Address;

/**
 * This application start counting unique IP Addresses from a file. Path to the file get from the first argument,
 * also can enable validate syntax the IP Addresses from the file using the second argument.
 *
 * @throws IOException when have problem with access to the file
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        int lengthArgs = args.length;
        if (lengthArgs < 1 || lengthArgs > 2) {
            System.out.println("Need only two argument: the first with path to file with IP Addresses " +
                    "and the second with True or False to enable or disable validate IP addresses " +
                    "(All argument with wrong value will be converted to False");
            System.exit(1);
        }

        String sourceFileName = args[0];
        boolean validateTrue = false;
        if (args.length == 2)
            validateTrue = Boolean.parseBoolean(args[1]);

        long start = System.currentTimeMillis();
        System.out.println("Distinct IP Addresses: "
                + getCountIPAddressFromFileByBitSet(sourceFileName, validateTrue));
        System.out.println("Time spent: " + (System.currentTimeMillis() - start) / 1000 + "s") ;
    }

    /**
     * Get count IP address from file using BitSet
     * @param path need get full path to file with IP addresses
     * @param validateTrue enable or disable validating IP address
     * @throws IOException when have problem with access to the file
     */
    private static int getCountIPAddressFromFileByBitSet(String path, boolean validateTrue) throws IOException {
        IPCounter counter = new IPCounter();
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        String ipAddress;
        int countSkippedLines = 0;
        while ((ipAddress = reader.readLine()) != null) {
            if (validateTrue) {
                if (!thisIsTrueIPAddress(ipAddress)) {
                    continue;
                }
            }
            try {
                counter.add(ipAddress);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                countSkippedLines++;
            }
        }
        if (countSkippedLines != 0) {
            System.out.println("Skipped lines: " + countSkippedLines);
        }
        return counter.count();
    }

    /**
     * Validate IP address is true or not
     * @param IPAddress the one IP address want to check
     * @return the IP address is true or not
     */
    public static boolean thisIsTrueIPAddress(String IPAddress) {
        try {
            return Inet4Address.getByName(IPAddress)
                    .getHostAddress().equals(IPAddress);
        }
        catch (UnknownHostException ex) {
            return false;
        }
    }
}
