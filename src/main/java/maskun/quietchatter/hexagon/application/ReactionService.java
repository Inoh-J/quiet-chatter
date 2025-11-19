package maskun.quietchatter.hexagon.application;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.hexagon.application.value.ReactionTarget;
import maskun.quietchatter.hexagon.inbound.ReactionModifiable;
import maskun.quietchatter.hexagon.outbound.MemberRepository;
import maskun.quietchatter.hexagon.outbound.ReactionEventPublisher;
import maskun.quietchatter.hexagon.outbound.TalkRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionService implements ReactionModifiable {
    private final ReactionEventPublisher reactionEventPublisher;
    private final MemberRepository memberRepository;
    private final TalkRepository talkRepository;

    @Override
    public void add(ReactionTarget target) {
        talkRepository.require(target.talkId());
        memberRepository.require(target.memberId());

        reactionEventPublisher.add(target);
    }

    @Override
    public void remove(ReactionTarget target) {

        talkRepository.require(target.talkId());
        memberRepository.require(target.memberId());

        reactionEventPublisher.remove(target);
    }
}
