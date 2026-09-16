package com.caseware.tracker.model;

import java.time.Instant;
import java.util.Map;

public class TemplateVersion {
    private String productId;
    private String versionId;
    private Map<String, Object> schemaData;
    private Instant publishedAt;

    public TemplateVersion(String productId, String versionId, Map<String, Object> schemaData, Instant publishedAt) {
        this.productId = productId;
        this.versionId = versionId;
        this.schemaData = schemaData;
        this.publishedAt = publishedAt;
    }

    public String getProductId() { return productId; }
    public String getVersionId() { return versionId; }
    public Map<String, Object> getSchemaData() { return schemaData; }
    public Instant getPublishedAt() { return publishedAt; }
}
