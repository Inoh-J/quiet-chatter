package maskun.quietchatter.hexagon.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import maskun.quietchatter.hexagon.domain.Book;
import maskun.quietchatter.hexagon.domain.value.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BookServiceImplTest implements MyTestContainers {
    @Autowired
    private BookServiceImpl bookService;

    @Test
    void save() {
        Title title = new Title("Test book");
        Book book = bookService.Save(title);
        assertNotNull(book);
        assertEquals(title, book.getTitle());
    }
}
