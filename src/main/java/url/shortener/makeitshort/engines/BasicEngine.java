package url.shortener.makeitshort.engines;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.engines.abstracts.AbstractEngine;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

@Service("BasicEngine")
@Slf4j
public class BasicEngine extends AbstractEngine implements CodingEngine {
    private final UrlRepository urlRepository;

    public BasicEngine(UrlUtil urlUtil, UrlRepository urlRepository) {
        super(urlUtil);
        this.urlRepository = urlRepository;
    }

    @Override
    public String generateShorterUrl(String url) {
        Url newValue = Url.builder()
                .realUrl(url)
                .build();
        urlRepository.save(newValue);
        newValue.setCode(String.valueOf(newValue.getId()));
        urlRepository.save(newValue);

        log.info("generateShorterUrl: url - {} was coded", url);
        return newValue.getCode();
    }

    @Override
    public String getBackRealUrl(String codedUrl) {
        log.info("getBackRealUrl: code - {} was used", codedUrl);
        return urlRepository.getUrlByCode((codedUrl)).getRealUrl();
    }
}
