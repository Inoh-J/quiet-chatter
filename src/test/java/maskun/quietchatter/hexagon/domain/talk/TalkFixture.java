package maskun.quietchatter.hexagon.domain.talk;

import java.util.Locale;
import maskun.quietchatter.hexagon.domain.BaseEntity;
import maskun.quietchatter.hexagon.domain.book.Book;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.instancio.Select;
import org.springframework.lang.Nullable;

public class TalkFixture {

    public InstancioApi<Talk> asNew(@Nullable Book book) {
        return asNew().set(Select.field(Talk::getBook), book);
    }

    public InstancioApi<Talk> asNew() {
        return init().ignore(Select.fields().declaredIn(BaseEntity.class));
    }

    private static InstancioApi<Talk> init() {
        final String talkContent = new Faker(Locale.KOREA)
                .text()
                .text(1, Content.MAX_LENGTH);

        return Instancio.of(Talk.class)
                .set(Select.field(Talk::getContent), new Content(talkContent));
    }

    public InstancioApi<Talk> asPersisted(@Nullable Book book) {
        return init().set(Select.field(Talk::getBook), book);
    }
}
