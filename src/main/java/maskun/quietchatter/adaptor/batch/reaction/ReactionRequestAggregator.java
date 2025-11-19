package maskun.quietchatter.adaptor.batch.reaction;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import maskun.quietchatter.hexagon.application.value.ReactionTarget;

public class ReactionRequestAggregator {

    private final Map<ReactionTarget, ReactionEvent> requests = new HashMap<>();

    public ReactionRequestAggregator(Collection<ReactionEvent> requests) {
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

    public List<ReactionTarget> getInserts() {
        return requests.entrySet().stream()
                .filter(e -> e.getValue().action() == Action.INSERT)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<ReactionTarget> getDeletes() {
        return requests.entrySet().stream()
                .filter(e -> e.getValue().action() == Action.DELETE)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Set<UUID> getTalkIds() {
        return requests.keySet().stream()
                .map(ReactionTarget::talkId)
                .collect(Collectors.toSet());
    }

}
