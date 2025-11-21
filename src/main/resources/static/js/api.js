// JSON 응답을 위한 간단한 에러 핸들러
function handleJsonResponse(response) {
    if (!response.ok) {
        // JSON 본문에서 오류 메시지 구문 분석 시도
        return response.json().then(err => {
            throw new Error(err.message || 'API 요청에 실패했습니다.');
        }).catch(() => {
            // 본문이 JSON이 아니거나 다른 오류가 발생할 경우를 위한 대비책
            throw new Error(`HTTP ${response.status}: API 요청에 실패했습니다.`);
        });
    }
    return response.json();
}

/**
 * 키워드로 책을 검색합니다.
 * @param {string} keyword - 검색 키워드.
 * @param {number} page - 가져올 페이지 번호 (0-기준).
 * @returns {Promise<object>} 검색 결과 페이지 객체로 귀결되는 프로미스.
 */
export function searchBooks(keyword, page = 0) {
    return fetch(`/api/books?keyword=${keyword}&page=${page}`)
        .then(handleJsonResponse);
}

/**
 * 단일 도서의 상세 정보를 가져옵니다.
 * @param {string|number} bookId - 책의 ID.
 * @returns {Promise<object>} 도서 상세 객체로 귀결되는 프로미스.
 */
export function getBookDetails(bookId) {
    return fetch(`/api/books/${bookId}`)
        .then(handleJsonResponse);
}

/**
 * ID로 여러 권의 책을 가져옵니다.
 * @param {Array<string|number>} bookIds - 책 ID의 배열.
 * @returns {Promise<Array<object>>} 도서 객체의 배열로 귀결되는 프로미스.
 */
export function getBooksByIds(bookIds) {
    if (!bookIds || bookIds.length === 0) {
        return Promise.resolve([]);
    }
    return fetch(`/api/books?id=${bookIds.join(',')}`)
        .then(handleJsonResponse);
}

/**
 * 특정 도서에 대한 북톡을 가져옵니다.
 * @param {string|number} bookId - 책의 ID.
 * @param {number} page - 가져올 페이지 번호 (0-기준).
 * @returns {Promise<object>} 북톡 페이지 객체로 귀결되는 프로미스.
 */
export function getTalks(bookId, page = 0) {
    return fetch(`/api/talks?bookId=${bookId}&page=${page}&size=6&sort=createdAt,desc`)
        .then(handleJsonResponse);
}

/**
 * 추천 북톡을 가져옵니다.
 * @returns {Promise<Array<object>>} 추천 북톡 배열로 귀결되는 프로미스.
 */
export function getRecommendedTalks() {
    return fetch('/api/talks/recommend')
        .then(handleJsonResponse);
}

/**
 * 책에 대한 새로운 북톡을 게시합니다.
 * @param {string|number} bookId - 책의 ID.
 * @param {string} content - 북톡의 내용.
 * @returns {Promise<object>} 새로 생성된 북톡 객체로 귀결되는 프로미스.
 */
export function postTalk(bookId, content) {
    const now = new Date();
    now.setUTCMonth(now.getUTCMonth() + 12);
    const hiddenTimestamp = now.toISOString();

    return fetch('/api/talks', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({bookId, content, hidden: hiddenTimestamp})
    }).then(handleJsonResponse);
}

/**
 * 북톡에서 리액션을 추가하거나 제거하는 것을 처리합니다.
 * @param {string|number} talkId - 북톡의 ID.
 * @param {'LIKE' | 'SUPPORT'} reactionType - 리액션의 종류.
 * @param {boolean} hasReacted - 사용자가 이미 리액션을 했으면 true, 그렇지 않으면 false.
 * @returns {Promise<Response>} 원시 fetch 응답으로 귀결되는 프로미스.
 */
export function handleReaction(talkId, reactionType, hasReacted) {
    const method = hasReacted ? 'DELETE' : 'POST';
    return fetch(`/api/reactions`, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({type: reactionType, talkId: talkId})
    });
}
