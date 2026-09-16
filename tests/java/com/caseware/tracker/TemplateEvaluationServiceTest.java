package com.caseware.tracker;

import com.caseware.tracker.model.TemplateVersion;
import com.caseware.tracker.service.TemplateEvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateEvaluationServiceTest {

    private TemplateEvaluationService evaluationService;

    @BeforeEach
    void setUp() {
        evaluationService = new TemplateEvaluationService();
    }

    @Test
    void testEvaluateEngagementUpdate_WhenUpdateIsPending() {
        // Arrange
        String engagementId = "eng-123";
        String productId = "prod-tax-2026";
        String currentVersion = "v1.0.0";

        TemplateVersion latestTemplate = new TemplateVersion(
                productId,
                "v2.0.0",
                new HashMap<>(),
                Instant.now()
        );
        String aiSummary = "Updated tax calculation formulas and added compliance validation.";

        // Act
        Map<String, Object> result = evaluationService.evaluateEngagementUpdate(
                engagementId,
                productId,
                currentVersion,
                latestTemplate,
                aiSummary
        );

        // Assert
        assertNotNull(result);
        assertEquals(engagementId, result.get("engagementId"));
        assertEquals(productId, result.get("productId"));
        assertEquals("v1.0.0", result.get("installedVersion"));
        assertEquals("v2.0.0", result.get("latestAvailableVersion"));
        assertTrue((Boolean) result.get("hasPendingUpdate"));
        assertEquals(aiSummary, result.get("summaryOfChanges"));
    }

    @Test
    void testEvaluateEngagementUpdate_WhenEngagementIsUpToDate() {
        // Arrange
        String engagementId = "eng-456";
        String productId = "prod-audit-2026";
        String currentVersion = "v2.0.0";

        TemplateVersion latestTemplate = new TemplateVersion(
                productId,
                "v2.0.0",
                new HashMap<>(),
                Instant.now()
        );
        String aiSummary = "Unused since up to date";

        // Act
        Map<String, Object> result = evaluationService.evaluateEngagementUpdate(
                engagementId,
                productId,
                currentVersion,
                latestTemplate,
                aiSummary
        );

        // Assert
        assertNotNull(result);
        assertEquals("v2.0.0", result.get("installedVersion"));
        assertEquals("v2.0.0", result.get("latestAvailableVersion"));
        assertFalse((Boolean) result.get("hasPendingUpdate"));
        assertEquals("Engagement is up to date.", result.get("summaryOfChanges"));
    }
}
