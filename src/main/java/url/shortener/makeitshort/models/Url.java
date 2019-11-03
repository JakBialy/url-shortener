package url.shortener.makeitshort.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "urls", indexes = {@Index(name = "ids",  columnList="id", unique = true),
        @Index(name = "codes", columnList="code", unique = false)})
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column(unique = true)
    String code;

    @Column(length=1000)
    String realUrl;

}


