package url.shortener.makeitshort.engines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BasicEngineTest {

    private static final String TEST_URL = "www.google.com";

    @Autowired
    UrlRepository urlRepository;

    private BasicEngine basicEngine;

    @BeforeEach
    void setUp() {
        basicEngine = new BasicEngine(urlRepository);
    }

    @Test
    void generateShorterUrl() {
        assertFalse(basicEngine.generateShorterUrl(TEST_URL).isEmpty());
    }

    /**
     * {@link BasicEngine#getBackRealUrl(String)}
     */
    @Test
    void getBackRealUrl() {
        // GIVEN
        Url newUrl = Url
                .builder()
                .realUrl(TEST_URL)
                .build();
        urlRepository.save(newUrl);

        // WHEN
        String realUrl = basicEngine.getBackRealUrl(String.valueOf(newUrl.getId()));

        // THEN
        assertEquals(newUrl.getRealUrl(), realUrl);
    }
}