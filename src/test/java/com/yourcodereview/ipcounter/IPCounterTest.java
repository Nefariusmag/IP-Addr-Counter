package com.yourcodereview.ipcounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IPCounterTest {

    /**
     * Test to check one IP Address couldn't add twice.
     */
    @Test
    void dountCountOneIPTwiceTest() {
        IPCounter ipCounter = new IPCounter();
        ipCounter.add("1.1.1.1");
        ipCounter.add("1.1.1.1");
        assertEquals(1, ipCounter.count());
    }

    /**
     * Test to check different IP Addresses counting right.
     */
    @Test
    void countSeveralIPTest() {
        IPCounter ipCounter = new IPCounter();
        ipCounter.add("1.1.1.1");
        ipCounter.add("1.1.1.2");
        assertEquals(2, ipCounter.count());
    }

    /**
     * Test to check method count make sum the First Set and the Second Set correctly.
     */
    @Test
    void countingCorrectTest() {
        IPCounter ipCounter = new IPCounter();
        ipCounter.add("1.1.1.1");
        ipCounter.add("1.1.1.2");
        assertEquals(ipCounter.count(),
                (ipCounter.getTheFirstSetSize() + ipCounter.getTheSecondSetSize()));
    }

    /**
     * Test to check a low IP Address came to the Fist Set, and a big IP Address came to the Second Set.
     */
    @Test
    void IPSaveToCorrectSetTest() {
        IPCounter ipCounter = new IPCounter();
        ipCounter.add("1.1.1.1");
        ipCounter.add("0.0.0.0");
        ipCounter.add("255.255.255.254");
        ipCounter.add("255.255.255.255");
        assertEquals(2, ipCounter.getTheFirstSetSize());
        assertEquals(2, ipCounter.getTheSecondSetSize());
    }
}
