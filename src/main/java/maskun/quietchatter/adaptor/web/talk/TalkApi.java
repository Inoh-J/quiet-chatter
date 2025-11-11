package maskun.quietchatter.adaptor.web.talk;

import maskun.quietchatter.adaptor.web.shared.IdResponse;
import maskun.quietchatter.hexagon.application.TalkCreateService;
import maskun.quietchatter.hexagon.application.value.TalkCreateRequest;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/talks")
@RestController
public class TalkApi {
    private final TalkCreateService talkCreateService;

    public TalkApi(TalkCreateService talkCreateService) {
        this.talkCreateService = talkCreateService;
    }

    @PostMapping
    public IdResponse post(@RequestBody TalkPostRequest request) {
        TalkCreateRequest talkCreateRequest = request.toTalkCreateRequest();
        Talk posted = talkCreateService.create(talkCreateRequest);
        return new IdResponse(posted.getId());
    }
}
