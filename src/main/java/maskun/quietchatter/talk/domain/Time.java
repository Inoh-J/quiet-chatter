package maskun.quietchatter.talk.domain;

import jakarta.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public record Time(
        Instant hidden
) {
}
