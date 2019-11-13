package url.shortener.makeitshort.engines.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.repositories.UrlRepository;

@Service
@Slf4j
public class SearchEngine {

    private UrlRepository urlRepository;

    public SearchEngine(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * Gets back stored value(url) by code
     * @param codedUrl code
     * @return stored url
     */
    public String getBackRealUrl(String codedUrl) {
        log.info("getBackRealUrl: code - {} was used", codedUrl);
        return urlRepository.getUrlByCode(codedUrl).getRealUrl();
    }

}
