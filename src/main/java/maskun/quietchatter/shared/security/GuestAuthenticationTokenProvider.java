package maskun.quietchatter.shared.security;

import static maskun.quietchatter.member.domain.Role.GUEST;

import lombok.RequiredArgsConstructor;
import maskun.quietchatter.member.application.in.GuestRegistrable;
import maskun.quietchatter.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
class GuestAuthenticationTokenProvider implements GuestAuthenticationProvider {
    private final GuestRegistrable guestRegistrable;

    @Override
    public Authentication create() {
        Member guest = registerNewGuest();
        MemberDetails memberDetails = MemberDetails.from(guest);
        return AuthenticatedMemberToken.from(memberDetails);
    }

    private Member registerNewGuest() {
        Member guest = guestRegistrable.createNewGuest();
        Assert.isTrue(guest.getRole().equals(GUEST), "게스트 역할 예상하였으나 다름");
        return guest;
    }
}
