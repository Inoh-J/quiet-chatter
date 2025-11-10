package maskun.quietchatter.hexagon.application.value;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.talk.Content;

public record TalkCreateRequest(
        UUID bookId,
        Content content) {
}
