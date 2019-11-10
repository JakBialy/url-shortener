package url.shortener.makeitshort.engines;

public interface CodingEngine {

    /**
     * Checks if ulr is valid, add a host and
     * passes it into code generation
     * @param url any String
     * @return String value of current id
     */
    String generateShorterUrl(String url);

    /**
     * Gets back stored value(url) by code
     * @param codedUrl code
     * @return stored url
     */
    String getBackRealUrl(String codedUrl);

    /**
     * Generates code, binds it and saves with url
     * @param url any String
     * @return String value of current id
     */
    String processUrlToGetCode(String url);
}
