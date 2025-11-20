package maskun.quietchatter.talk.adaptor.in;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

record TalkCreateWebRequest(
        UUID bookId,
        String content,
        Instant hidden
) {

    TalkCreateWebRequest {
        Objects.requireNonNull(bookId);
        Objects.requireNonNull(content);
    }
}
