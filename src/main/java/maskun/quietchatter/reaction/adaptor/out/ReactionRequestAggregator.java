package maskun.quietchatter.reaction.adaptor.out;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import maskun.quietchatter.reaction.application.ReactionTarget;

class ReactionRequestAggregator {

    private final Map<ReactionTarget, ReactionEvent> requests = new HashMap<>();

    ReactionRequestAggregator(Collection<ReactionEvent> requests) {
        requests.forEach(this::put);
    }

    private void put(ReactionEvent request) {
        ReactionTarget target = new ReactionTarget(request.talkId(), request.memberId(), request.type());

        ReactionEvent computed = requests.computeIfAbsent(target, k -> request);

        if (computed.action() != request.action()) {
            //상쇄
            requests.remove(target);
        }
    }

    List<ReactionTarget> getInserts() {
        return requests.entrySet().stream()
                .filter(e -> e.getValue().action() == Action.INSERT)
                .map(Map.Entry::getKey)
                .toList();
    }

    List<ReactionTarget> getDeletes() {
        return requests.entrySet().stream()
                .filter(e -> e.getValue().action() == Action.DELETE)
                .map(Map.Entry::getKey)
                .toList();
    }

}
