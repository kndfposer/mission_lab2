package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MissionRaw {
    public String missionId;
    public String date;
    public String location;
    public String outcome;
    public Long damageCost;
    public CurseRaw curse;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "sorcerer")
    @JacksonXmlElementWrapper(localName = "sorcerers")
    public List<SorcererRaw> sorcerers = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "technique")
    @JacksonXmlElementWrapper(localName = "techniques")
    public List<TechniqueRaw> techniques = new ArrayList<>();

    public EconomicAssessmentRaw economicAssessment;
    public CivilianImpactRaw civilianImpact;
    public EnemyActivityRaw enemyActivity;
    @JsonProperty("environmentConditions")
    public EnvironmentConditionsRaw environmentConditions;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "event")
    @JacksonXmlElementWrapper(localName = "operationTimeline")
    public List<TimelineEventRaw> operationTimeline = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> operationTags = new ArrayList<>();
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> supportUnits = new ArrayList<>();
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> recommendations = new ArrayList<>();
    public String notes;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> artifactsRecovered = new ArrayList<>();
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> evacuationZones = new ArrayList<>();
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> statusEffects = new ArrayList<>();
}
