package maskun.quietchatter.hexagon.application.value;

import java.util.Objects;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.reaction.Reaction.Type;

public record ReactionTarget(UUID talkId, UUID memberId, Type type) {

    public ReactionTarget {
        Objects.requireNonNull(talkId);
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(type);
    }
}
