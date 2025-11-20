package maskun.quietchatter.talk.application.in;

import java.util.UUID;
import maskun.quietchatter.talk.domain.Content;
import maskun.quietchatter.talk.domain.Time;

public record TalkCreateRequest(
        UUID bookId,
        UUID memberId,
        Content content,
        Time time
) {
}
