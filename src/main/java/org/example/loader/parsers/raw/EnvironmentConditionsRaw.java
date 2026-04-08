package org.example.loader.parsers.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentConditionsRaw {
    private String weather;
    private String timeOfDay;
    private String visibility;
    private Integer cursedEnergyDensity;

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public String getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }
    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }
    public Integer getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(Integer cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
}
