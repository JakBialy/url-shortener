package url.shortener.makeitshort.engines.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for {@link SearchEngine}
 */
@DataJpaTest
class SearchEngineTest {
    private static final String TEST_URL = "https://www.google.com/";
    private static final String TEST_CODE = "aB8JK";

    @Autowired
    private UrlRepository urlRepository;

    private SearchEngine searchEngine;

    @BeforeEach
    void setUp() {
        searchEngine = new SearchEngine(urlRepository);
    }

    /**
     * {@link SearchEngine#getBackRealUrl(String)}
     */
    @Test
    void getBackRealUrl_whenUrlExist_shouldReturnIt() {
        Url url = Url
                .builder()
                .code(TEST_CODE)
                .realUrl(TEST_URL)
                .build();
        urlRepository.save(url);

        assertEquals(TEST_URL, searchEngine.getBackRealUrl(url.getCode()));
    }

    /**
     * {@link SearchEngine#getBackRealUrl(String)}
     */
    // TODO shouldn't throw null, can be handled with optional and later on error should be displayed for a user
    @Test
    void getBackRealUrl_whenUrlExist_shouldReturnNull() {
        assertThrows(NullPointerException.class, () -> searchEngine.getBackRealUrl(TEST_CODE));
    }

}