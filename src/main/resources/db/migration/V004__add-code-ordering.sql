ALTER TABLE ordering ADD code VARCHAR(36) NOT NULL AFTER id;
UPDATE ordering SET code = UUID();
ALTER TABLE ordering ADD CONSTRAINT uk_ordering_code UNIQUE(code);