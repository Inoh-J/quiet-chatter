package maskun.quietchatter.book.adaptor.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

record NaverBookSearchResponse(

        // 검색 결과를 생성한 시간
        @JsonProperty("lastBuildDate")
        String lastBuildDate,

        // 총 검색 결과 개수
        @JsonProperty("total")
        long total,

        // 검색 시작 위치
        @JsonProperty("start")
        int start,

        // 한 번에 표시할 검색 결과 개수
        @JsonProperty("display")
        int display,

        // 개별 검색 결과 (책 목록)
        @JsonProperty("items")
        List<NaverBookItem> items
) {
}
