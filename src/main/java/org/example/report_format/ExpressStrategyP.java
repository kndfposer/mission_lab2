package org.example.report_format;

import org.example.model.Mission;

public class ExpressStrategyP implements Strategy_p {
    @Override
    public String generate(Mission mission) {
        return "Миссия " + mission.getMissionId()
                + " | " + mission.getDate()
                + " | " + mission.getOutcome()
                + " | " + mission.getLocation();
    }
}
