package maskun.quietchatter.adaptor.web.talk;

import java.util.Objects;
import java.util.UUID;
import maskun.quietchatter.hexagon.application.value.TalkCreateRequest;
import maskun.quietchatter.hexagon.domain.talk.Content;

public record TalkPostRequest(
        UUID bookId,
        String content) {

    public TalkPostRequest {
        Objects.requireNonNull(bookId);
        Objects.requireNonNull(content);
    }

    public TalkCreateRequest toTalkCreateRequest() {
        return new TalkCreateRequest(bookId, new Content(content));
    }
}
