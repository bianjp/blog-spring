DROP TABLE IF EXISTS post;
CREATE TABLE post (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  publish_date DATE,
  slug VARCHAR(100) NOT NULL,
  content TEXT NOT NULL,
  content_html TEXT,
  excerpt TEXT,
  status INTEGER NOT NULL DEFAULT 0, -- -1: deleted, 0: draft, 1: published, 2: unpublished
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS pretty_post_url ON post(publish_date, slug) WHERE status = 1;
CREATE INDEX IF NOT EXISTS idx_status_on_post ON post(status);

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password CHAR(60) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS username ON "user"(username);

-- password: admin
-- Note: Change your password immediately after deploying to production
INSERT INTO "user"(username, password) VALUES ('admin', '$2a$10$BZ6QlrTU7DrcksywIAe/ReE.dPROBM/OL962RwxsJeroxgvB.iJZ6');
