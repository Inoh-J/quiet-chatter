package maskun.quietchatter.adaptor.web.talk;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.talk.Talk;

/**
 * DTO for {@link maskun.quietchatter.hexagon.domain.talk.Talk}
 */
public record TalkResponse(
        UUID id,
        Instant createdAt,
        UUID bookId,
        String content) implements Serializable {

    public static TalkResponse from(Talk talk) {
        return new TalkResponse(
                talk.getId(),
                talk.getCreatedAt(),
                talk.getBookId(),
                talk.getContent().value()
        );
    }
}


