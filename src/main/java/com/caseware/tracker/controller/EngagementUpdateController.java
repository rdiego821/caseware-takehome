package com.caseware.tracker.controller;

import com.caseware.tracker.model.TemplateVersion;
import com.caseware.tracker.service.TemplateEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/engagements")
public class EngagementUpdateController {
    private final TemplateEvaluationService evaluationService;

    public EngagementUpdateController(TemplateEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/{engagementId}/pending-updates")
    public ResponseEntity<Map<String, Object>> getPendingUpdates(
            @PathVariable String engagementId,
            @RequestParam String productId,
            @RequestParam String currentVersion) {

        // Mocking retrieval from global catalog and index (In production, fetched via repositories)
        TemplateVersion latestTemplate = new TemplateVersion(
                productId,
                "v2.1.0",
                new HashMap<>(),
                Instant.now()
        );

        String mockAiSummary = "Added mandatory compliance checks for Section 4 and updated financial risk formulas.";

        Map<String, Object> response = evaluationService.evaluateEngagementUpdate(
                engagementId,
                productId,
                currentVersion,
                latestTemplate,
                mockAiSummary
        );

        return ResponseEntity.ok(response);
    }
}
