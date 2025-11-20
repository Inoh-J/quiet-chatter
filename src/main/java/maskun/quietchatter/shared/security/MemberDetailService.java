package maskun.quietchatter.shared.security;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import maskun.quietchatter.member.application.out.MemberRepository;
import maskun.quietchatter.member.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Member> found = memberRepository.findById(UUID.fromString(memberId));

        if (found.isEmpty()) {
            throw new UsernameNotFoundException("Member not found for id: " + memberId);
        }

        return MemberDetails.from(found.get());
    }
}
