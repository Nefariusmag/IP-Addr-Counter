package app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for Application counting unique numbers IP Address from file.
 */
public class AppTest
{
    /**
     * Test validating different variants of IP Addresses.
     */
    @ParameterizedTest
    @MethodSource("provideIPAddressesForTest")
    void validateIPAddressTest(String IPAddress, boolean expected){
        assertEquals(expected, App.thisIsTrueIPAddress(IPAddress));
    }

    /**
     * Generate stream with arguments IP Addresses and expected statuses for parameterized test
     *
     * @return stream with IP Addresses and expected statuses
     */
    private static Stream<Arguments> provideIPAddressesForTest(){
        return Stream.of(
                Arguments.of("1.1.1.1.1", false),
                Arguments.of("we.0.1.0", false),
                Arguments.of("...", false),
                Arguments.of("0.1.a.a", false),
                Arguments.of("1.1.1", false),
                Arguments.of("a.a.a.a", false),
                Arguments.of("999.999.999.999", false),
                Arguments.of("255.255.255.255", true),
                Arguments.of("0.0.0.0", true),
                Arguments.of("192.168.1.1", true)
        );
    }

    /**
     * Test counting IP Addresses from file with validating IP Addresses and without.
     *
     * @throws IOException if file not exists
     */
    @Test
    void countingUniqueIPAddressTest() throws IOException {
        assertEquals(5, App.getCountIPAddressFromFileByBitSet("src/test/resources/list_ip.txt", true));
        assertEquals(6, App.getCountIPAddressFromFileByBitSet("src/test/resources/list_ip.txt", false));
    }

    /**
     * Test get exception that file not exist.
     */
    @Test
    void fileIsNotExistTest() {
        assertThrows(NoSuchFileException.class, () -> {
            App.getCountIPAddressFromFileByBitSet("src/test/resources/la-la-la.txt", true);
        });
    }
}
