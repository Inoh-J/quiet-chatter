package maskun.quietchatter.hexagon.domain.book;

import jakarta.persistence.Embeddable;

@Embeddable
public record Author(
        String name
) {
}
