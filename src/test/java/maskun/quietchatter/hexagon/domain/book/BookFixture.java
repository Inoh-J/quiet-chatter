package maskun.quietchatter.hexagon.domain.book;

import static org.instancio.Instancio.of;

import java.util.Locale;
import maskun.quietchatter.hexagon.domain.BaseEntity;
import net.datafaker.Faker;
import org.instancio.InstancioApi;
import org.instancio.Select;

public class BookFixture {

    private static final Faker faker = new Faker(Locale.KOREA);

    public static InstancioApi<Book> newBook() {
        return initBook()
                .ignore(Select.fields().declaredIn(BaseEntity.class));
    }

    private static InstancioApi<Book> initBook() {
        return of(Book.class)
                .set(Select.field(Book::getTitle), new Title(faker.book().title()))
                .set(Select.field(Book::getIsbn), new Isbn(faker.code().isbn13()));
    }

    public static InstancioApi<Book> book() {
        return initBook();
    }

}
