package url.shortener.makeitshort.engines.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URL;

@Service
@Slf4j
public class UrlUtil {

    /**
     * Retrieves localhost url from current request and convert it into String
     * @return String containing localhost url
     */
    public String addLocalHost() {
        String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        int lastIndex = url.lastIndexOf("/");
        return url.substring(0, lastIndex + 1);
    }

    /**
     * Checks if given Url is a valid one
     * @param url String with url to check
     * @return boolean
     */
    public boolean checkIfUrlValid(String url) {
        try {
            new URL(url).toURI();
            log.info("checkIfUrlValid: {} is valid URL", url);
            return true;
        } catch (Exception e) {
            log.warn("checkIfUrlValid: {} is not valid URL", url, e);
            return false;
        }

    }
}
