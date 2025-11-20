package maskun.quietchatter.talk.application;

import maskun.quietchatter.book.application.out.BookRepository;
import maskun.quietchatter.member.application.out.MemberRepository;
import maskun.quietchatter.talk.application.in.TalkCreatable;
import maskun.quietchatter.talk.application.in.TalkCreateRequest;
import maskun.quietchatter.talk.application.out.TalkRepository;
import maskun.quietchatter.talk.domain.Talk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class TalkCreateService implements TalkCreatable {
    private final BookRepository bookRepository;
    private final TalkRepository talkRepository;
    private final MemberRepository memberRepository;

    TalkCreateService(BookRepository bookRepository, TalkRepository talkRepository,
                             MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.talkRepository = talkRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Talk create(TalkCreateRequest request) {
        bookRepository.require(request.bookId());
        memberRepository.require(request.memberId());

        Talk talk = new Talk();
        talk.updateBookId(request.bookId());
        talk.updateMemberId(request.memberId());
        talk.update(request.content());
        talk.update(request.time());

        return talkRepository.save(talk);
    }

}
