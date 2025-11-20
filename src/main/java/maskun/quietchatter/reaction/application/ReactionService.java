package maskun.quietchatter.reaction.application;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.member.application.out.MemberRepository;
import maskun.quietchatter.reaction.application.in.ReactionModifiable;
import maskun.quietchatter.reaction.application.in.ReactionTarget;
import maskun.quietchatter.reaction.application.out.ReactionEventPublisher;
import maskun.quietchatter.talk.application.out.TalkRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ReactionService implements ReactionModifiable {
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
