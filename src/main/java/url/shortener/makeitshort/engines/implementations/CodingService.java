package url.shortener.makeitshort.engines.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.engines.interfaces.CodingEngine;

@Service
@Slf4j
public class CodingService {
    private static final String BASE_64_SERVICE = "Base64Engine";
    private static final String BASIC_SERVICE = "BasicEngine";

    private CodingEngine base64Engine;
    private CodingEngine basicEngine;

    public CodingService(@Qualifier("Base64Engine") CodingEngine base64Engine,
                         @Qualifier("BasicEngine") CodingEngine basicEngine) {
        this.base64Engine = base64Engine;
        this.basicEngine = basicEngine;
    }

    /**
     * Handle choosing right service for url coding
     * @param url website address to process
     * @param type requested type of engine
     * @return code for URL access or null if engine type incorrect
     */
    public Object processUrlToGetCode(String url, String type) {
        if (type.equals(BASE_64_SERVICE)) {
            return base64Engine.processUrlToGetCode(url);
        } else if (type.equals(BASIC_SERVICE)) {
            return basicEngine.processUrlToGetCode(url);
        } else {
            log.warn("getEngine: Wrong type of engine requested: {}", type);
            return null;
        }
    }

}
