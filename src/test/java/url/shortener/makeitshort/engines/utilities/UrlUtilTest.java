package url.shortener.makeitshort.engines.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class UrlUtilTest {

    private UrlUtil urlUtil;

    @BeforeEach
    void setUp() {
        urlUtil = new UrlUtil();
    }

    @ParameterizedTest(name = "checkIfUrlValidSource for url {0} should be {1}")
    @MethodSource("checkIfUrlValidSource")
    void checkIfUrlValid(String url, boolean expected) {
        assertEquals(expected, urlUtil.checkIfUrlValid(url));
    }

    private static Stream<Arguments> checkIfUrlValidSource () {
        return Stream.of(
                Arguments.of("www.google.com", false),
                Arguments.of("blablabla", false),
                Arguments.of("www.gooogle", false),
                Arguments.of(".com", false),
                Arguments.of("https://www.sthaa.com/sth", true),
                Arguments.of("https://www.test-site.com/sth", true)
        );
    }

}