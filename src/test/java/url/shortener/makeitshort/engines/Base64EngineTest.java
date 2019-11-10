package url.shortener.makeitshort.engines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import url.shortener.makeitshort.engines.utilities.Base64;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link Base64Engine}
 */
@DataJpaTest
class Base64EngineTest {
    private static final String TEST_URL = "https://www.test-url.co.uk/";
    private static final String TEST_LOCALHOST = "null";
    private static final String MESSAGE_URL_INVALID = "given URL is not valid! Check your address";
    private static final String TEST_CODE = "BAAQ";
    private static final String TEST_CODE_2 = "BACQ";

    @Autowired
    UrlRepository urlRepository;

    @Mock
    private UrlUtil urlUtil;

    @Mock
    private Base64 base64;

    private Base64Engine base64Engine;

    @BeforeEach
    void setUp() {
        base64Engine = new Base64Engine(urlUtil, base64, urlRepository);
    }

    /**
     * {@link Base64Engine#generateShorterUrl(String)}
     */
    @Test
    void generateShorterUrl_whenUrlIsValid_ShouldReturnCode() {
         when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(true);
         when(base64.decimalToBase64URL(anyInt())).thenReturn(TEST_CODE);
         assertFalse(base64Engine.processUrlToGetCode(TEST_URL).isEmpty());
    }

    // This case is not working due to lack of threw DataIntegrityViolationException using JPA tests
//    /**
//     * {@link Base64Engine#generateShorterUrl(String)}
//     */
//    @Test
//    void generateShorterUrl_whenUrlIsValidAndCodeExists_ShouldUseRecursionToReGenerateCode() {
//        Url url = Url
//                .builder()
//                .realUrl(TEST_URL)
//                .code(TEST_CODE)
//                .build();
//        urlRepository.save(url);
//
//        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(true);
//        when(base64.decimalToBase64URL(anyInt())).thenReturn(TEST_CODE, TEST_CODE_2);
//        assertFalse(base64Engine.processUrlToGetCode(TEST_URL).isEmpty());
//    }

    /**
     * {@link Base64Engine#generateShorterUrl(String)}
     */
    @Test
    void generateShorterUrl_whenUrlIsInvalid_ShouldReturnErrorMessage() {
        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(false);
        assertEquals(MESSAGE_URL_INVALID, base64Engine.processUrlToGetCode(TEST_URL));
    }

    /**
     * {@link Base64Engine#getBackRealUrl(String)}
     */
    @Test
    void getBackRealUrl() {
        when(urlUtil.checkIfUrlValid(TEST_URL)).thenReturn(true);
        when(base64.decimalToBase64URL(anyInt())).thenReturn(TEST_CODE);
        String code = base64Engine.processUrlToGetCode(TEST_URL);

        String realUrl = base64Engine.getBackRealUrl(code.replace(TEST_LOCALHOST, ""));

        assertEquals(TEST_URL, realUrl);
    }
}