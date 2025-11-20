package maskun.quietchatter.customer.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Message(
        String content
) {
}
