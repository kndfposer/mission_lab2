package org.example.service;

import org.example.model.Mission;
import org.example.report_format.ExpressStrategyP;
import org.example.report_format.FieldMemoStrategyP;
import org.example.report_format.Strategy_p;
import org.example.report_format.SummaryStrategyP;
import org.springframework.stereotype.Service;

@Service
public class ReportDeckService {
    public String render(Mission mission, String type) {
        Strategy_p strategy = switch ((type == null ? "summary" : type).toLowerCase()) {
            case "express" -> new ExpressStrategyP();
            case "memo" -> new FieldMemoStrategyP();
            default -> new SummaryStrategyP();
        };
        return strategy.generate(mission);
    }
}
