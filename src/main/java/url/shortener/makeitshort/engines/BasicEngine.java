package url.shortener.makeitshort.engines;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

@Service
@Slf4j
public class BasicEngine implements CodingEngine {

    private final UrlRepository urlRepository;

    public BasicEngine(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * Very naive implementation where code is based on auto incrementation in database
     * @param url any String
     * @return String value of current id
     */
    @Override
    public String generateShorterUrl(String url) {
        log.info("generateShorterUrl: url - {} was coded", url);

        Url newValue = Url.builder()
                .realUrl(url)
                .build();
        urlRepository.save(newValue);

        return String.valueOf(newValue.getId());
    }

    /**
     * Getting back stored value by code
     * @param coded code
     * @return stored url
     */
    @Override
    public String getBackRealUrl(String coded) {
        log.info("getBackRealUrl: code - {} was used", coded);
        return urlRepository.getOne(Long.valueOf(coded)).getRealUrl();
    }
}
