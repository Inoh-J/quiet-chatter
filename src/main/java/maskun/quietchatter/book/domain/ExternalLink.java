package maskun.quietchatter.book.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record ExternalLink(
        String url
) {
}
