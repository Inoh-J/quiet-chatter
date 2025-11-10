package maskun.quietchatter.hexagon.outbound;

import static maskun.quietchatter.hexagon.domain.Fixture.book;
import static maskun.quietchatter.hexagon.domain.Fixture.talk;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import maskun.quietchatter.adaptor.jpa.JpaConfig;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(JpaConfig.class)
@ActiveProfiles("test")
class TalkRepositoryTest {
    @Autowired
    private TalkRepository repository;

    @Test
    void save() {
        final Book book = book().asPersisted().create();
        final Talk talk = talk().asNew(book).create();

        final Talk saved = repository.save(talk);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getBook()).isNotNull();
        assertThat(saved.getBook().getId()).isEqualTo(book.getId());
        assertThat(saved.getContent()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void findByBookOrderByCreatedAt() {
        final Book book = book().asPersisted().create();

        final Talk talk1 = talk().asNew(book).create();
        final Talk talk2 = talk().asNew(book).create();
        final Talk talk3 = talk().asNew(book).create();
        final Talk talk4 = talk().asNew(book).create();
        final Talk talk5 = talk().asNew(book).create();

        repository.save(talk1);
        repository.save(talk2);
        repository.save(talk3);
        repository.save(talk4);
        repository.save(talk5);

        final List<Talk> result = repository.findByBookOrderByCreatedAt(book);

        assertThat(result).hasSize(5);
        assertThat(result).containsExactly(talk1, talk2, talk3, talk4, talk5);

    }
}
