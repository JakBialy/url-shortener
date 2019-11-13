package url.shortener.makeitshort.engines.abstracts;

import lombok.extern.slf4j.Slf4j;
import url.shortener.makeitshort.engines.interfaces.CodingEngine;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.repositories.UrlRepository;

@Slf4j
public abstract class AbstractEngine implements CodingEngine {

    private UrlUtil urlUtil;
    protected UrlRepository urlRepository;

    public AbstractEngine(UrlUtil urlUtil, UrlRepository urlRepository) {
        this.urlUtil = urlUtil;
        this.urlRepository = urlRepository;
    }

    @Override
    public String processUrlToGetCode(String url) {
        if (urlUtil.checkIfUrlValid(url)) {
            String code = generateShorterUrl(url);
            return urlUtil.addLocalHost() + code;
        } else {
            String warn = "given URL is not valid! Check your address";
            log.warn(warn);
            return warn;
        }
    }

}
