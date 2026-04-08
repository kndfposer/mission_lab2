package org.example.model;

import org.example.model.enums.Visibility;

public class EnvironmentConditions {
    private String weather;
    private String timeOfDay;
    private Visibility visibility = Visibility.UNKNOWN;
    private Integer cursedEnergyDensity;

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public String getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
    public Integer getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(Integer cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
}
