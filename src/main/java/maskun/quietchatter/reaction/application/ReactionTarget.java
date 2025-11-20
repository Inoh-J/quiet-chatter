package maskun.quietchatter.reaction.application;

import java.util.Objects;
import java.util.UUID;
import maskun.quietchatter.reaction.domain.Reaction.Type;

public record ReactionTarget(UUID talkId, UUID memberId, Type type) {

    public ReactionTarget {
        Objects.requireNonNull(talkId);
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(type);
    }
}
