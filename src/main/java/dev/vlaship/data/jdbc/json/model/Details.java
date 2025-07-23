package dev.vlaship.data.jdbc.json.model;

public record Details(
        DetailsType type,
        int pages,
        int year
) {
}
