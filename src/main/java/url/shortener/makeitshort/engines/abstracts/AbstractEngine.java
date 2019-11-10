package url.shortener.makeitshort.engines.abstracts;

import lombok.extern.slf4j.Slf4j;
import url.shortener.makeitshort.engines.CodingEngine;
import url.shortener.makeitshort.engines.utilities.UrlUtil;

@Slf4j
public abstract class AbstractEngine implements CodingEngine {

    private UrlUtil urlUtil;

    public AbstractEngine(UrlUtil urlUtil) {
        this.urlUtil = urlUtil;
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
