package maskun.quietchatter.shared.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.instancio.Select.field;

import maskun.quietchatter.member.domain.Member;
import maskun.quietchatter.member.domain.Role;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberDetailsTest {
    private MemberDetails memberDetails;

    @BeforeEach
    void setUp() {
        Member member = Instancio.of(Member.class).set(field(Member::getRole), Role.REGULAR).create();
        memberDetails = MemberDetails.from(member);
    }

    @Test
    void invariant() {
        Member idNullMember = Instancio.of(Member.class).ignore(field(Member::getId)).create();
        assertThatIllegalArgumentException().isThrownBy(() -> MemberDetails.from(idNullMember));

        Member roleNullMember = Instancio.of(Member.class).ignore(field(Member::getRole)).create();
        assertThatIllegalArgumentException().isThrownBy(() -> MemberDetails.from(roleNullMember));

    }

    @Test
    void getId() {
        assertThat(memberDetails.getId()).isNotNull();
    }

    @Test
    void getAuthorities() {
        assertThat(memberDetails.getAuthorities()).isNotNull();
        assertThat(memberDetails.getAuthorities())
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_REGULAR"));
    }

    @Test
    void getPassword() {
        assertThat(memberDetails.getPassword()).isBlank();
    }

    @Test
    void getUsername() {
        assertThat(memberDetails.getUsername()).isNotBlank();
        assertThat(memberDetails.getUsername()).isEqualTo(memberDetails.getId().toString());
    }

    @Test
    void member() {
        assertThat(memberDetails.member()).isNotNull();
        assertThat(memberDetails.member().getId()).isEqualTo(memberDetails.getId());

        String roleEnumName = memberDetails.member().getRole().name();
        assertThat(memberDetails.getAuthorities())
                .anyMatch(authority -> authority.getAuthority().contains(roleEnumName));
    }
}
