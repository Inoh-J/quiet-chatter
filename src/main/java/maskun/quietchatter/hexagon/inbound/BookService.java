package maskun.quietchatter.hexagon.inbound;

import maskun.quietchatter.hexagon.domain.Book;
import maskun.quietchatter.hexagon.domain.value.Title;

public interface BookService {
    Book Save(Title title);
}
