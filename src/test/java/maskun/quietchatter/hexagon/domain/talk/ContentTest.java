package maskun.quietchatter.hexagon.domain.talk;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.instancio.Instancio.gen;

import org.junit.jupiter.api.Test;

class ContentTest {

    @Test
    void invariant() {
        final String tooLong = gen().string().length(251).get();
        assertThatIllegalArgumentException().isThrownBy(() -> new Content(tooLong));
    }

}
