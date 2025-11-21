/**
 * API 응답의 페이지네이션 구조를 나타냅니다.
 * @template T
 */
export class Page {
    /**
     * @param {object} pageData - API의 원시 페이지 객체.
     * @param {function(object): T} contentMapper - 원시 콘텐츠 항목을 클래스로 매핑하는 함수.
     */
    constructor(pageData, contentMapper) {
        /** @type {T[]} */
        this.content = pageData.content.map(contentMapper);
        this.pageInfo = {
            first: pageData.page.first,
            last: pageData.page.last,
            number: pageData.page.number,
            totalPages: pageData.page.totalPages,
        };
    }
}

/**
 * 책을 나타냅니다.
 */
export class Book {
    constructor({id, title, author, isbn, description, thumbnailImageUrl, externalLinkUrl}) {
        this.id = id;
        this.title = title || '제목 없음';
        this.author = author || '저자 미상';
        this.isbn = isbn || 'ISBN 정보 없음';
        this.description = description || '설명 없음';
        this.thumbnailImageUrl = thumbnailImageUrl || null;
        this.externalLinkUrl = externalLinkUrl || null;
    }
}

/**
 * 북톡을 나타냅니다.
 */
export class Talk {
    constructor({id, bookId, content, createdAt, like_count, support_count, didILike, didISupport}) {
        this.id = id;
        this.bookId = bookId;
        this.content = content || '';
        this.createdAt = new Date(createdAt);
        this.like_count = like_count || 0;
        this.support_count = support_count || 0;
        this.didILike = didILike || false;
        this.didISupport = didISupport || false;
    }

    /**
     * 생성 날짜를 현지화된 문자열로 반환합니다.
     * @returns {string}
     */
    getFormattedCreatedAt() {
        return this.createdAt.toLocaleString();
    }
}
