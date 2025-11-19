package maskun.quietchatter.hexagon.outbound;

import maskun.quietchatter.hexagon.application.value.ReactionTarget;

public interface ReactionEventPublisher {
    void add(ReactionTarget target);

    void remove(ReactionTarget target);
}
