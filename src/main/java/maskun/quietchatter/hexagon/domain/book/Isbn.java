package maskun.quietchatter.hexagon.domain.book;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Isbn(
        @Column(name = "isbn")
        String value
) {
}
