package maskun.quietchatter;

import static org.instancio.Select.field;

import maskun.quietchatter.member.domain.Member;
import maskun.quietchatter.member.domain.Role;
import maskun.quietchatter.shared.security.MemberDetails;
import org.instancio.Instancio;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestSecurityConfig {

    public static final String TEST_GUEST = "testGuest";
    public static final String TEST_REGULAR = "testRegular";
    public static final String TEST_MANAGER = "testManager";

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> switch (username) {
            case TEST_GUEST -> getGuest();
            case TEST_REGULAR -> getRegular();
            case TEST_MANAGER -> getAdmin();
            default -> throw new UsernameNotFoundException(username);
        };
    }

    private UserDetails getGuest() {
        Member member = Instancio.of(Member.class).set(field(Member::getRole), Role.GUEST).create();
        return MemberDetails.from(member);
    }

    private UserDetails getRegular() {
        Member member = Instancio.of(Member.class).set(field(Member::getRole), Role.REGULAR).create();
        return MemberDetails.from(member);
    }

    private UserDetails getAdmin() {
        Member member = Instancio.of(Member.class).set(field(Member::getRole), Role.MANAGER).create();
        return MemberDetails.from(member);
    }
}
