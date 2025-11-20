package maskun.quietchatter.shared.security;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import maskun.quietchatter.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public record MemberDetails(
        Member member,
        List<? extends GrantedAuthority> authorities
) implements UserDetails {

    public MemberDetails {
        Assert.notNull(member, () -> "멤버는 null이 되어서는 안 됩니다");
        Assert.notNull(member.getId(), () -> "멤버 ID는 null이 되어서는 안 됩니다");
        Assert.notNull(authorities, () -> "Authorities null 이어선 안됩니다");
    }

    public static MemberDetails from(Member member) {
        Assert.notNull(member.getRole(), () -> "인증 멤버의 Role null 일 수 없음");

        String authorityString = "ROLE_" + member.getRole().name();
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authorityString));

        return new MemberDetails(member, authorities);
    }

    public UUID getId() {
        return member.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public String getUsername() {
        return member.getId().toString();
    }
}
