package url.shortener.makeitshort.engines.interfaces;

public interface CodingEngine {

    /**
     * Checks if ulr is valid, add a host and
     * passes it into code generation
     * @param url any String
     * @return String value of current id
     */
    String generateShorterUrl(String url);

    /**
     * Generates code, binds it and saves with url
     * @param url any String
     * @return String value of current id
     */
    String processUrlToGetCode(String url);
}
