package maskun.quietchatter.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import maskun.quietchatter.hexagon.domain.value.BookId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();

        converters.addAll(getBookIdConverter());

        return new MongoCustomConversions(converters);
    }

    private List<Converter<?, ?>> getBookIdConverter() {
        Converter<UUID, BookId> reader = new Converter<>() {
            @Override
            public BookId convert(UUID source) {
                return new BookId(source);
            }
        };

        Converter<BookId, UUID> writer = new Converter<>() {
            @Override
            public UUID convert(BookId source) {
                return source.value();
            }
        };
        return List.of(reader, writer);
    }
}
