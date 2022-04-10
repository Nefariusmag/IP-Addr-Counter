package app;

import java.util.BitSet;

/**
 * Count unique IP Address.
 */
public class IPCounter {
    private final BitSet theFirstSet;
    private final BitSet theSecondSet;

    /**
     * Getter to get value of the First Set
     */
    public BitSet getTheFirstSet() {
        return theFirstSet;
    }

    /**
     * Getter to get value of the Second Set
     */
    public BitSet getTheSecondSet() {
        return theSecondSet;
    }

    /**
     * When create object this class create two BitSet with max value of Integer.
     */
    public IPCounter() {
        this.theFirstSet = new BitSet(Integer.MAX_VALUE);
        this.theSecondSet = new BitSet(Integer.MAX_VALUE);
    }

    /**
     * Add new value to our Sets.
     * When the method start, String IP Address convert to Long number
     * Next the Long number convert to Integer and put to the first Set like bit index,
     * but if the number is more Integer Max Value then put to the second Set.
     * When try to put the same IP Address to the Sets, the BitSet can see that the bit index marked like used.
     *
     * @param IPAddress IP Address that want to add
     * @throws NumberFormatException when came IP has letters
     * @throws ArrayIndexOutOfBoundsException when came IP is not x.x.x.x
     */
    public void add(String IPAddress) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        long IPAddressAsLong = getIPAddressAsLong(IPAddress);
        /*
        Because IP Address converted to integer could be more Integer.MAX_VALUE so use two BitSet.
        when number is lower that Integer.MAX_VALUE when the number saved to theFirstSet, others to theSecondSet
         */
        if (IPAddressAsLong < Integer.MAX_VALUE) {
            theFirstSet.set((int) IPAddressAsLong);
        } else {
            int bitIndex = (int) (IPAddressAsLong - Integer.MAX_VALUE);
            /*
            When IP address is 255.255.255.255 get problem with IndexOutOfBoundsException
            so use little hack with change number index.
            */
            theSecondSet.set(bitIndex == 0 ? bitIndex : bitIndex - 1);
        }
    }

    /**
     * To convert an IP address to integer notation, each section of the IP address (separated by ".")
     * is multiplied by 256x. In this case, x represents the position of the section from right to left,
     * starting with 0. Here is an example using this formula:
     * 192.168.1.1 = (192 * 256^3) + (168 * 256^2) + (1 * 256^1) + (1 * 256^0)
     *
     * @param IPAddress IP Address
     * @return uniq convert IP Address to long
     * @throws NumberFormatException when came IP has letters
     * @throws ArrayIndexOutOfBoundsException when came IP is not x.x.x.x
     */
    private long getIPAddressAsLong(String IPAddress) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        long IPAddressAsLong = 0;
        String[] partsIPAddress = IPAddress.split("\\.");
        for (int numberPartIPAddress = 0; numberPartIPAddress < 4; numberPartIPAddress++) {
            int partIPAddressAsInt = Integer.parseInt(partsIPAddress[numberPartIPAddress]);
            IPAddressAsLong += (partIPAddressAsInt * Math.pow(256, (3 - numberPartIPAddress)));
        }
        return IPAddressAsLong;
    }

    /**
     * Calculate number of unique IP Address. Sum bit indexes of the first set and the second set.
     *
     * @return count unique number of IP Addresses
     */
    public int count() {
        return this.theFirstSet.cardinality() + this.theSecondSet.cardinality();
    }
}
