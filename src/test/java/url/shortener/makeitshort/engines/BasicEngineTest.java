package url.shortener.makeitshort.engines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.repositories.UrlRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for
 * {@link BasicEngine}
 */
@DataJpaTest
class BasicEngineTest {

    private static final String TEST_URL = "https://www.test-url.co.uk/";
    private static final String TEST_LOCALHOST = "null";
    private static final String MESSAGE_URL_INVALID = "given URL is not valid! Check your address";

    @Autowired
    private UrlRepository urlRepository;

    @Mock
    private UrlUtil urlUtil;

    private BasicEngine basicEngine;

    @BeforeEach
    void setUp() {
        basicEngine = new BasicEngine(urlUtil, urlRepository);
    }

    /**
     * {@link BasicEngine#generateShorterUrl(String)}
     */
    @Test
    void generateShorterUrl_whenUrlIsValid_ShouldReturnCode() {
        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(true);
        assertFalse(basicEngine.processUrlToGetCode(TEST_URL).isEmpty());
    }

    /**
     * {@link BasicEngine#generateShorterUrl(String)}
     */
    @Test
    void generateShorterUrl_whenUrlIsInvalid_ShouldReturnErrorMessage() {
        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(false);
        assertEquals(MESSAGE_URL_INVALID, basicEngine.processUrlToGetCode(TEST_URL));
    }

    /**
     * {@link BasicEngine#getBackRealUrl(String)}
     */
    @Test
    void getBackRealUrl() {
        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(true);
        String code = basicEngine.processUrlToGetCode(TEST_URL);

        String realUrl = basicEngine.getBackRealUrl(code.replace(TEST_LOCALHOST, ""));

        assertEquals(TEST_URL, realUrl);
    }
}