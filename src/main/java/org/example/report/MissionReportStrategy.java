package org.example.report;

import org.example.model.Mission;

public interface MissionReportStrategy {
    String createReport(Mission mission);
}
