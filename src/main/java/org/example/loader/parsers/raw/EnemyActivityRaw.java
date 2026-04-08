package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnemyActivityRaw {
    private String behaviorType;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "targetPriority")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> targetPriority = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "pattern")
    @JacksonXmlElementWrapper(localName = "attackPatterns")
    private List<String> attackPatterns = new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlProperty(localName = "measure")
    @JacksonXmlElementWrapper(localName = "countermeasuresUsed")
    private List<String> countermeasuresUsed = new ArrayList<>();

    private String mobility;
    private String escalationRisk;

    public String getBehaviorType() { return behaviorType; }
    public void setBehaviorType(String behaviorType) { this.behaviorType = behaviorType; }
    public List<String> getTargetPriority() { return targetPriority; }
    public void setTargetPriority(List<String> targetPriority) { this.targetPriority = targetPriority; }
    public List<String> getAttackPatterns() { return attackPatterns; }
    public void setAttackPatterns(List<String> attackPatterns) { this.attackPatterns = attackPatterns; }
    public List<String> getCountermeasuresUsed() { return countermeasuresUsed; }
    public void setCountermeasuresUsed(List<String> countermeasuresUsed) { this.countermeasuresUsed = countermeasuresUsed; }
    public String getMobility() { return mobility; }
    public void setMobility(String mobility) { this.mobility = mobility; }
    public String getEscalationRisk() { return escalationRisk; }
    public void setEscalationRisk(String escalationRisk) { this.escalationRisk = escalationRisk; }
}
