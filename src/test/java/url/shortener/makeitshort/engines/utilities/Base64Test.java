package url.shortener.makeitshort.engines.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Base64}
 */
class Base64Test {

    private Base64 base64;

    @BeforeEach
    void setUp() {
        base64 = new Base64();
    }

    /**
     * {@link Base64#decimalToBase64URL(int)}
     * @param decimal decimal value to convert
     * @param result expected value in base64
     */
    @ParameterizedTest(name = "decimalToBase64URL should convert {0} into base64 value: {1}")
    @MethodSource("decimalToBase64URLSource")
    void decimalToBase64URL(int decimal, String result) {
        assertEquals(result, base64.decimalToBase64URL(decimal));
    }

    private static Stream<Arguments> decimalToBase64URLSource () {
        return Stream.of(
                Arguments.of(0, "A"),
                Arguments.of(1, "B"),
                Arguments.of(64, "BA"),
                Arguments.of(999, "Pn"),
                Arguments.of(999_99, "Yaf"),
                Arguments.of(9_999_999, "mJZ_"),
                Arguments.of(Integer.MAX_VALUE, "B_____")
        );
    }

}