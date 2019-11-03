package url.shortener.makeitshort.engines;

public interface CodingEngine {

    String generateShorterUrl(String url);
    String getBackRealUrl(String codedUrl);
}
