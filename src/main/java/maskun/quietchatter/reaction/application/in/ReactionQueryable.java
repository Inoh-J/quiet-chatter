package maskun.quietchatter.reaction.application.in;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import maskun.quietchatter.reaction.domain.Reaction;

public interface ReactionQueryable {
    List<Reaction> getAllBy(UUID memberId, Collection<UUID> talkIds);
}
