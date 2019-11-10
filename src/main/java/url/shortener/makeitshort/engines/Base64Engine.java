package url.shortener.makeitshort.engines;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import url.shortener.makeitshort.engines.abstracts.AbstractEngine;
import url.shortener.makeitshort.engines.utilities.Base64;
import url.shortener.makeitshort.engines.utilities.UrlUtil;
import url.shortener.makeitshort.models.Url;
import url.shortener.makeitshort.repositories.UrlRepository;

import java.util.Random;

@Service("Base64Engine")
@Slf4j
public class Base64Engine extends AbstractEngine implements CodingEngine {

    private Base64 base64;
    private UrlRepository urlRepository;

    public Base64Engine(UrlUtil urlUtil, Base64 base64, UrlRepository urlRepository) {
        super(urlUtil);
        this.base64 = base64;
        this.urlRepository = urlRepository;
    }

    @Override
    public String generateShorterUrl(String url) {
        Random random = new Random();
        String base64String = base64.decimalToBase64URL(random.nextInt(Integer.MAX_VALUE));

        Url newValue = Url.builder()
                .realUrl(url)
                .code(base64String)
                .build();

        try {
            urlRepository.save(newValue);
        }
        catch (DataIntegrityViolationException e) {
            log.info("Code is duplicated, code re-generation", e);
            base64String = generateShorterUrl(url);
        }

        log.info("generateShorterUrl: url - {} was coded", url);
        return base64String;
    }

    @Override
    public String getBackRealUrl(String codedUrl) {
        log.info("getBackRealUrl: code - {} was used", codedUrl);
        return urlRepository.getUrlByCode(codedUrl).getRealUrl();
    }
}
