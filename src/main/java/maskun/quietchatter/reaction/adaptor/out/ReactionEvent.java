package maskun.quietchatter.reaction.adaptor.out;

import java.util.UUID;
import maskun.quietchatter.hexagon.domain.reaction.Reaction.Type;

record ReactionEvent(UUID talkId, UUID memberId, Type type, Action action) {

}
