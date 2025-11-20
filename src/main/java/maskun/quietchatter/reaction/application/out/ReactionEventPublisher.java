package maskun.quietchatter.reaction.application.out;

import maskun.quietchatter.reaction.application.ReactionTarget;

public interface ReactionEventPublisher {
    void add(ReactionTarget target);

    void remove(ReactionTarget target);
}
