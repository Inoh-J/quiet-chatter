package maskun.quietchatter.shared.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import maskun.quietchatter.member.application.out.MemberRepository;
import maskun.quietchatter.member.domain.Member;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class MemberDetailServiceTest {
    private MemberDetailService memberDetailService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberDetailService = new MemberDetailService(memberRepository);
    }

    @Test
    void invariant() {
        assertThatIllegalArgumentException().isThrownBy(() -> memberDetailService.loadUserByUsername("notUUID"));
    }

    @Test
    void loadUserByUsername() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(Instancio.create(Member.class)));
        UserDetails userDetails = memberDetailService.loadUserByUsername(UUID.randomUUID().toString());

        assertThat(userDetails).isNotNull();
    }

    @Test
    @DisplayName("없는 유저 예외")
    void notExistingUser() {
        when(memberRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> memberDetailService.loadUserByUsername(UUID.randomUUID().toString())).isInstanceOf(
                UsernameNotFoundException.class);
    }
}
