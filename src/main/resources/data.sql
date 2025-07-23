-- Sample data for books table
INSERT INTO public.books (isbn, name, author, details)
VALUES 
('978-0-13-516630-7', 'Effective Java', 'Joshua Bloch', '{"type": "SOFT", "year": 2018, "pages": 412}'),
('978-1-4919-5462-1', 'Spring Boot in Action', 'Craig Walls', '{"year": 2016, "pages": 264}'),
('978-1-6172-9183-9', 'Clean Code', 'Robert C. Martin', '{"type": "HARD", "year": 2008, "pages": 464}')
ON CONFLICT (isbn) DO NOTHING;
