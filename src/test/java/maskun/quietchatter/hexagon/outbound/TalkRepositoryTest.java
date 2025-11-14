package maskun.quietchatter.hexagon.outbound;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.instancio.Select.fields;

import java.util.List;
import java.util.UUID;
import maskun.quietchatter.adaptor.jpa.JpaConfig;
import maskun.quietchatter.hexagon.domain.BaseEntity;
import maskun.quietchatter.hexagon.domain.talk.Talk;
import org.instancio.Instancio;
import org.instancio.Model;
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
        final Talk talk = Instancio.of(Talk.class).ignore(fields().declaredIn(BaseEntity.class)).create();

        final Talk saved = repository.save(talk);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getBookId()).isNotNull();
        assertThat(saved.getMemberId()).isNotNull();
        assertThat(saved.getContent()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void findByBookOrderByCreatedAtPage() {
        UUID bookId = UUID.randomUUID();
        Model<Talk> talkModel = Instancio.of(Talk.class)
                .ignore(fields().declaredIn(BaseEntity.class))
                .set(field(Talk::getBookId), bookId).toModel();

        List<Talk> talks = Instancio.ofList(talkModel).size(100).create();
        repository.saveAll(talks);

        Page<Talk> result = repository.findByBookIdOrderByCreatedAtDesc(bookId, PageRequest.of(0, 10));

        assertThat(result.getSize()).isEqualTo(10);
        assertThat(result.getTotalElements()).isEqualTo(100);
        assertThat(result.getTotalPages()).isEqualTo(10);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getContent()).hasSize(10);
    }
}
