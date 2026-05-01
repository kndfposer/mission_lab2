package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.Visibility;

@Entity
@Table(name = "environment_conditions")
public class EnvironmentConditions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String weather;
    private String timeOfDay;
    @Enumerated(EnumType.STRING)
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
