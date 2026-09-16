# Caseware Take-Home Test: Engagement Template Update Tracker

## 📌 Overview
This repository contains the architecture design, technical documentation, and targeted implementation for the **Engagement Template Update System** designed for Caseware. 

The system enables users to see at a glance which of their engagement files have pending product template updates, providing clear, human-readable summaries of inbound changes without violating critical performance constraints.

---

## 🏛️ Architecture Highlights & Key Decisions
* **CQRS & Event-Driven Architecture (EDA):** Decouples heavy write paths (template publishing and engagement updates) from the read path (dashboard UI), completely avoiding the prohibitive cost of loading monolithic engagement files (which take ~1 minute per file).
* **Shared Template Catalog:** All product versions and AI-generated human-readable diff summaries are stored globally once, drastically saving compute and LLM API costs across firms.
* **Partitioned Engagement Index:** Engagement metadata indices are strictly partitioned per customer firm (tenant isolation) to guarantee security, data privacy, and sub-second dashboard query performance.

---

## 📂 Repository Structure

```text
caseware-takehome/
├── diagrams/                # Architecture diagrams (Draw.io / exported visuals)
├── src/                     # Java Spring Boot source code (Controllers, Services, Models) and unit tests
├── design.md                # Comprehensive architecture design document (Part 1)
├── AI_USAGE.md              # Transparency report on AI tool usage and governance
└── pom.xml                  # Maven configuration file
```

## 🚀 Getting Started & Testing

### Prerequisites
* Java 21+

### Running Unit Tests
To execute the validation tests for the template evaluation and update logic, run the following command in your terminal:

```bash
mvn clean test
```

## 📄 Documentation Reference

* **[Design Document (design.md)](./design.md):** Covers High-Level Architecture, Implementation Plan, Testing Strategy, Observability, and Failure Modes/Tradeoffs.

* **[AI Usage Report (AI_USAGE.md)](./AI_USAGE.md):** Details where AI assisted, where outputs were corrected, and engineering governance guidelines.