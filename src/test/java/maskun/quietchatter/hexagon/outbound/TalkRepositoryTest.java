package maskun.quietchatter.hexagon.outbound;

import static maskun.quietchatter.hexagon.domain.Fixture.book;
import static maskun.quietchatter.hexagon.domain.Fixture.talk;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import maskun.quietchatter.adaptor.jpa.JpaConfig;
import maskun.quietchatter.hexagon.domain.book.Book;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    void findByBookOrderByCreatedAtPage() {
        final Book book = book().asPersisted().create();

        List<Talk> talks = IntStream.range(0, 100).mapToObj(i -> talk().asNew(book).create()).toList();
        repository.saveAll(talks);

        Page<Talk> result = repository.findByBookOrderByCreatedAt(book, PageRequest.of(0, 10));

        assertThat(result.getSize()).isEqualTo(10);
        assertThat(result.getTotalElements()).isEqualTo(100);
        assertThat(result.getTotalPages()).isEqualTo(10);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getContent()).hasSize(10);
    }

    @Test
    void findByBookOrderByCreatedAtPageNoBook() {
        final Book book = book().asPersisted().create();

        List<Talk> talks = IntStream.range(0, 100).mapToObj(i -> talk().asNew(book).create()).toList();
        repository.saveAll(talks);

        final Book otherBook = book().asPersisted().create();
        Page<Talk> result = repository.findByBookOrderByCreatedAt(otherBook, PageRequest.of(0, 10));

        assertThat(result.getSize()).isEqualTo(10);
        assertThat(result.getTotalElements()).isEqualTo(0);
        assertThat(result.getTotalPages()).isEqualTo(0);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getContent()).hasSize(0);
    }
}
