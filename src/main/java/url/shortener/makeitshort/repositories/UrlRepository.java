package url.shortener.makeitshort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import url.shortener.makeitshort.models.Url;

@Repository
public interface UrlRepository extends JpaRepository <Url, Long> {

    Url getUrlByCode(String code);
}
