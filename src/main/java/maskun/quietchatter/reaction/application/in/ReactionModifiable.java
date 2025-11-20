package maskun.quietchatter.reaction.application.in;

import maskun.quietchatter.reaction.application.ReactionTarget;

public interface ReactionModifiable {
    void add(ReactionTarget target);

    void remove(ReactionTarget target);
}
