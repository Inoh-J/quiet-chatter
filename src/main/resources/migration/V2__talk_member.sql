ALTER TABLE talk
    ADD is_hidden BOOLEAN NOT NULL DEFAULT FALSE;

CREATE INDEX idx_talk_is_hidden ON talk (is_hidden);
