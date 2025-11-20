package maskun.quietchatter.talk.application.in;

import maskun.quietchatter.talk.domain.Talk;

public interface TalkCreatable {

    Talk create(TalkCreateRequest request);
}
