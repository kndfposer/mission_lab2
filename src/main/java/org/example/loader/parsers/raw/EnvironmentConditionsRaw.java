package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentConditionsRaw { public String weather; public String timeOfDay; public String visibility; public Integer cursedEnergyDensity; }
