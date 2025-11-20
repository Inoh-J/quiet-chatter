package maskun.quietchatter.shared.security;

import org.springframework.security.core.Authentication;

public interface GuestAuthenticationProvider {
    Authentication create();
}
