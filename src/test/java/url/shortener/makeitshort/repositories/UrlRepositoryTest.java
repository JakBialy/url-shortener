package url.shortener.makeitshort.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import url.shortener.makeitshort.models.Url;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for {@link UrlRepository}
 */
@DataJpaTest
class UrlRepositoryTest {
    private static final String TEST_URL = "https://www.test-url.co.uk/";
    private static final String TEST_CODE = "BA1_";
    private static final String WRONG_CODE = "SOME_CODE";

    @Autowired
    UrlRepository urlRepository;


    /**
     * {@link UrlRepository#getUrlByCode(String)}
     */
    @Test
    void getUrlByCode() {
        Url testUrl = Url
                .builder()
                .code(TEST_CODE)
                .realUrl(TEST_URL)
                .build();
        urlRepository.save(testUrl);
        assertEquals(testUrl, urlRepository.getUrlByCode(TEST_CODE));
    }

    /**
     * {@link UrlRepository#getUrlByCode(String)}
     */
    @Test
    void getUrlByCode_whenCodeIsIncorrect_shouldReturnNull() {
        Url testUrl = Url
                .builder()
                .code(TEST_CODE)
                .realUrl(TEST_URL)
                .build();
        urlRepository.save(testUrl);
        assertNull(urlRepository.getUrlByCode(WRONG_CODE));
    }


}
