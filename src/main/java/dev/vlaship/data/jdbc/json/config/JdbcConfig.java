package dev.vlaship.data.jdbc.json.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import dev.vlaship.data.jdbc.json.model.Details;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JdbcConfig extends AbstractJdbcConfiguration {

    private final ObjectMapper objectMapper;

    @Override
    protected List<Object> userConverters() {
        return List.of(
                new JdbcConfig.PGobjectToDetailsConverter(objectMapper),
                new JdbcConfig.DetailsToJsonConverter(objectMapper)
        );
    }

    @Component
    @ReadingConverter
    @RequiredArgsConstructor
    public static class PGobjectToDetailsConverter implements Converter<PGobject, Details> {

        private final ObjectMapper objectMapper;

        @Override
        public Details convert(PGobject source) {
            try {
                if (source == null || source.getValue() == null) {
                    return null;
                }
                return objectMapper.readValue(source.getValue(), Details.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Failed to convert PGobject to Details: " + e.getMessage(), e);
            }
        }
    }

    @Component
    @WritingConverter
    @RequiredArgsConstructor
    public static class DetailsToJsonConverter implements Converter<Details, PGobject> {

        private final ObjectMapper objectMapper;

        @Override
        public PGobject convert(Details source) {
            try {
                var pgObject = new PGobject();
                pgObject.setType("jsonb");
                pgObject.setValue(objectMapper.writeValueAsString(source));
                return pgObject;
            } catch (JsonProcessingException | SQLException e) {
                throw new IllegalArgumentException("Failed to convert Details to PGobject: " + e.getMessage(), e);
            }
        }
    }
}
