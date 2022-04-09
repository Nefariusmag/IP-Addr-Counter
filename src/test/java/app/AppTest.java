package app;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for Application counting unique numbers IP Address from file.
 */
public class AppTest 
{
    /**
     * Test validating different variants of IP Addresses.
     */
    @Test
    public void validateIPAddressTest(){
        assertFalse(App.thisIsTrueIPAddress("1.1.1.1.1"));
        assertFalse(App.thisIsTrueIPAddress("we.0.1.0"));
        assertFalse(App.thisIsTrueIPAddress("..."));
        assertFalse(App.thisIsTrueIPAddress("0.1.a.a"));
        assertFalse(App.thisIsTrueIPAddress("1.1.1"));
        assertFalse(App.thisIsTrueIPAddress("a.a.a.a"));
        assertTrue(App.thisIsTrueIPAddress("255.255.255.255"));
        assertTrue(App.thisIsTrueIPAddress("0.0.0.0"));
        assertTrue(App.thisIsTrueIPAddress("192.168.1.1"));
        assertFalse(App.thisIsTrueIPAddress("999.999.999.999"));
    }

    /**
     * Test counting IP Addresses from file with validating IP Addresses and without.
     *
     * @throws IOException if file not exists
     */
    @Test
    public void countingUniqueIPAddressTest() throws IOException {
        assertEquals(5, App.getCountIPAddressFromFileByBitSet("src/test/resources/list_ip.txt", true));
        assertEquals(6, App.getCountIPAddressFromFileByBitSet("src/test/resources/list_ip.txt", false));
    }


    /**
     * Test get exception that file not exist.
     *
     */
    @Test(expected = NoSuchFileException.class)
    public void fileIsNotExistTest() throws IOException {
        App.getCountIPAddressFromFileByBitSet("src/test/resources/la-la-la.txt", true);
    }
}
