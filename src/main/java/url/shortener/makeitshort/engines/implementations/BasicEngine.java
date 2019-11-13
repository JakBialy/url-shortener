package url.shortener.makeitshort.engines.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.engines.abstracts.AbstractEngine;
import url.shortener.makeitshort.engines.interfaces.CodingEngine;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

@Service("BasicEngine")
@Slf4j
public class BasicEngine extends AbstractEngine implements CodingEngine {

    public BasicEngine(UrlUtil urlUtil, UrlRepository urlRepository) {
        super(urlUtil, urlRepository);
    }

    @Override
    public String generateShorterUrl(String url) {
        Url newValue = Url.builder()
                .realUrl(url)
                .build();
        urlRepository.save(newValue);
        newValue.setCode("~" + String.valueOf(newValue.getId()));
        urlRepository.save(newValue);

        log.info("generateShorterUrl: url - {} was coded", url);
        return newValue.getCode();
    }

}
