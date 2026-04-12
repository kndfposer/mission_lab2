package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EconomicAssessmentRaw { public Long totalDamageCost; public Long infrastructureDamage; public Long commercialDamage; public Long transportDamage; public Integer recoveryEstimateDays; public Boolean insuranceCovered; }
