package dev.vlaship.data.jdbc.json.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "books", schema = "public")
public record Book(
        @Id
        String isbn,

        @Column("name")
        String name,

        @Column("author")
        String author,

        @Column("details")
        Details details
) {
}
