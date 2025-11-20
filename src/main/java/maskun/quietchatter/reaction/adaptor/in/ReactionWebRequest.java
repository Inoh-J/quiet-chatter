package maskun.quietchatter.reaction.adaptor.in;

import java.util.UUID;
import maskun.quietchatter.reaction.domain.Reaction.Type;

record ReactionWebRequest(
        UUID talkId,
        Type type
) {
}
