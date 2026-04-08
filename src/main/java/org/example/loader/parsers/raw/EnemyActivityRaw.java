package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnemyActivityRaw {
    public String behaviorType;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "targetPriority")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<String> targetPriority = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "pattern")
    @JacksonXmlElementWrapper(localName = "attackPatterns")
    public List<String> attackPatterns = new ArrayList<>();

    public String mobility;
    public String escalationRisk;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "measure")
    @JacksonXmlElementWrapper(localName = "countermeasuresUsed")
    public List<String> countermeasuresUsed = new ArrayList<>();
}
