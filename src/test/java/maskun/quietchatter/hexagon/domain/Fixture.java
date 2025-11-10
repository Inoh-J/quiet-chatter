package maskun.quietchatter.hexagon.domain;

import maskun.quietchatter.hexagon.domain.book.BookFixture;
import maskun.quietchatter.hexagon.domain.talk.TalkFixture;

public class Fixture {
    private static final TalkFixture TALK_FIXTURE = new TalkFixture();
    private static final BookFixture BOOK_FIXTURE = new BookFixture();
    private Fixture() {
    }

    public static TalkFixture talk() {
        return TALK_FIXTURE;
    }

    public static BookFixture book() {
        return BOOK_FIXTURE;
    }

}
