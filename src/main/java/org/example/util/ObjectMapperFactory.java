package org.example.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class ObjectMapperFactory {
    private ObjectMapperFactory() {
    }

    public static ObjectMapper jsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        configure(mapper);
        return mapper;
    }

    public static ObjectMapper yamlMapper() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        configure(mapper);
        return mapper;
    }

    public static XmlMapper xmlMapper() {
        XmlMapper mapper = new XmlMapper();
        configure(mapper);
        return mapper;
    }

    private static void configure(ObjectMapper mapper) {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
    }
}
