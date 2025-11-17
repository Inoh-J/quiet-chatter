package maskun.quietchatter.adaptor.web.customer;

import jakarta.persistence.Embeddable;

@Embeddable
public record Message(
        String content
) {
}
