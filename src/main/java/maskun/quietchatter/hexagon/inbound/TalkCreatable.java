package maskun.quietchatter.hexagon.inbound;

import maskun.quietchatter.hexagon.application.value.TalkCreateRequest;
import maskun.quietchatter.hexagon.domain.talk.Talk;

public interface TalkCreatable {

    Talk create(TalkCreateRequest request);
}
