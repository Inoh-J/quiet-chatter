package maskun.quietchatter.reaction.application.in;

public interface ReactionModifiable {
    void add(ReactionTarget target);

    void remove(ReactionTarget target);
}
