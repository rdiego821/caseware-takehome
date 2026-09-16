# AI Usage Transparency Report - Caseware Take-Home Test

As part of the evaluation guidelines, this document outlines how AI tools (such as Claude/Gemini) were utilized during the architecture design and targeted implementation of the Engagement Template Update System.

---

## 1. Where AI Helped Me
* **Architecture Brainstorming & Tradeoffs:** AI assisted in rapidly evaluating options for handling the multi-tenant data isolation challenge, contrasting the pros and cons of a real-time fan-out write model versus an on-demand version comparison read model.
* **Boilerplate & Scaffold Generation:** AI accelerated the creation of the Java Spring Boot skeleton, model classes (`TemplateVersion.java`), REST controller boilerplate, and JUnit 5 test templates (`TemplateEvaluationServiceTest.java`), allowing focus to remain strictly on core business logic and architectural correctness.
* **Documentation Structuring:** AI helped structure this design document (`design.md`) clearly, ensuring all sections requested by Caseware (High-Level Architecture, Implementation Plan, Testing, Observability, and Failure Modes) were covered thoroughly.

---

## 2. Where I Corrected or Ignored AI Output
* **Initial Fan-Out Proposal (Corrected):** Initially, generic AI suggestions tended to propose updating every single engagement record inside a customer firm database whenever a product template was published. I explicitly rejected and corrected this approach because it violates the multi-tenant isolation boundaries and creates massive write bottlenecks (*write storms*). Instead, I enforced a decoupled, lightweight metadata index approach.
* **Performance Constraints Alignment (Corrected):** AI initially did not fully account for the hard constraint of ~1 minute per engagement load operation. I forced the design to completely bypass the legacy engagement storage during read paths by relying exclusively on cached lightweight indices and in-memory version comparisons.

---

## 3. How I Would Guide Other Engineers Using AI on This System
* **Contextual Boundaries over Copy-Paste:** Never let AI write system architecture or security boundaries blindly. Engineers must supply explicit domain constraints (such as Caseware's 1-minute load limit or multi-tenant separation) to the prompt context.
* **Security & Data Privacy Guardrails:** Remind engineers that LLMs and AI coding assistants must never be fed proprietary customer financial data, real engagement contents, or sensitive enterprise schemas.
* **Rigorous Review of Asynchronous Flows:** Ensure engineers do not rely on AI for distributed systems debugging without implementing proper idempotency, retry mechanisms (DLQs), and event-driven error handling.

---

## 4. Where AI Should Not Be Trusted in This Domain
* **Business Judgment & Professional Compliance:** AI should never make the final decision to "apply" or "decline" a template update on behalf of an accountant. Caseware's core premise is that AI *augments* professional judgment, but the final defensible conclusion rests entirely with the human practitioner.
* **Complex Financial Calculations & Schema Integrity:** While LLMs are great for generating human-readable diff summaries of JSON files, they should not be trusted to execute financial computations, schema migrations, or direct data transformations without strict deterministic validation tests in Java.