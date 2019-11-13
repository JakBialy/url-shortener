package url.shortener.makeitshort.engines.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import url.shortener.makeitshort.engines.interfaces.CodingEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * test class for {@link CodingService}
 */
@ExtendWith(SpringExtension.class)
class CodingServiceTest {
    private static final String TEST_URL = "https://www.google.com/";
    private static final String BASE_64_SERVICE = "Base64Engine";
    private static final String BASIC_SERVICE = "BasicEngine";
    private static final String WRONG_SERVICE_NAME = "XXXX-XXXX";

    @Mock
    Base64Engine base64Engine;

    @Mock
    BasicEngine basicEngine;

    private CodingService codingService;

    @BeforeEach
    void setUp() {
        codingService = new CodingService(base64Engine, basicEngine);
    }

    /**
     * {@link CodingService#processUrlToGetCode(String, String)}
     */
    @Test
    void processUrlToGetCode_whenRequestedServiceTypeIsBase64_ShouldUseIt() {
        codingService.processUrlToGetCode(TEST_URL, BASE_64_SERVICE);
        verify(base64Engine, times(1)).processUrlToGetCode(TEST_URL);
    }

    /**
     * {@link CodingService#processUrlToGetCode(String, String)}
     */
    @Test
    void processUrlToGetCode_whenRequestedServiceTypeIsBasic_ShouldUseIt() {
        codingService.processUrlToGetCode(TEST_URL, BASIC_SERVICE);
        verify(basicEngine, times(1)).processUrlToGetCode(TEST_URL);
    }

    /**
     * {@link CodingService#processUrlToGetCode(String, String)}
     */
    @Test
    void processUrlToGetCode_whenRequestedServiceTypeIsUnknown_ShouldReturnNull() {
        assertNull(codingService.processUrlToGetCode(WRONG_SERVICE_NAME, TEST_URL));
    }
}