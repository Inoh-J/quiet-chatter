package maskun.quietchatter.shared.security;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

class AuthenticatedMemberToken extends AbstractAuthenticationToken {
    private final MemberDetails memberDetails;

    AuthenticatedMemberToken(MemberDetails memberDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.memberDetails = memberDetails;
        setAuthenticated(true);
    }

    static AuthenticatedMemberToken from(MemberDetails memberDetails) {
        Assert.notNull(memberDetails, "memberDetail must not be null");
        return new AuthenticatedMemberToken(memberDetails, memberDetails.getAuthorities());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public MemberDetails getPrincipal() {
        return memberDetails;
    }
}
