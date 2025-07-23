package dev.vlaship.data.jdbc.json.repository;

import dev.vlaship.data.jdbc.json.model.Book;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, String> {
}
