package maskun.quietchatter.hexagon.inbound;

import maskun.quietchatter.hexagon.application.value.ReactionTarget;

public interface ReactionModifiable {
    void add(ReactionTarget target);

    void remove(ReactionTarget target);
}
