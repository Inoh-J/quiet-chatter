package maskun.quietchatter.adaptor.web.reaction;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import maskun.quietchatter.hexagon.application.value.ReactionTarget;
import maskun.quietchatter.hexagon.inbound.ReactionModifiable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reactions")
@RestController
@RequiredArgsConstructor
public class ReactionCommandApi {
    private final ReactionModifiable reactionModifiable;

    @PostMapping
    public ResponseEntity<String> add(@AuthenticationPrincipal UUID memberId,
                                      @RequestBody ReactionWebRequest webRequest) {

        ReactionTarget target = new ReactionTarget(webRequest.talkId(), memberId, webRequest.type());
        reactionModifiable.add(target);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<String> remove(@AuthenticationPrincipal UUID memberId,
                                         @RequestBody ReactionWebRequest webRequest) {
        ReactionTarget target = new ReactionTarget(webRequest.talkId(), memberId, webRequest.type());
        reactionModifiable.remove(target);
        return ResponseEntity.accepted().build();
    }
}
