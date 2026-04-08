package org.example.model;

public class EnvironmentConditions {
    private String weather;
    private String timeOfDay;
    private VisibilityLevel visibility;
    private Integer cursedEnergyDensity;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public VisibilityLevel getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityLevel visibility) {
        this.visibility = visibility;
    }

    public Integer getCursedEnergyDensity() {
        return cursedEnergyDensity;
    }

    public void setCursedEnergyDensity(Integer cursedEnergyDensity) {
        this.cursedEnergyDensity = cursedEnergyDensity;
    }
}
