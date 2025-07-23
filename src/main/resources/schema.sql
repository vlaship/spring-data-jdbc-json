CREATE TABLE IF NOT EXISTS public.books (
    isbn TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    author TEXT NOT NULL,
    details JSONB
);
