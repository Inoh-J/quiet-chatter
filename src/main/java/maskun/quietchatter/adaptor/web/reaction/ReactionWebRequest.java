package maskun.quietchatter.adaptor.web.reaction;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.reaction.Reaction.Type;

public record ReactionWebRequest(
        UUID talkId,
        Type type
) {
}
