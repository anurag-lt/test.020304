package model;

import java.util.Date;

/**
 * Represents a change request within the system, tracking the lifecycle and details of a change proposal.
 */
public class ChangeRequests {

    /**
     * Unique identifier for each change request record.
     */
    private int id;

    /**
     * Unique identifier for each change request for tracking and reference.
     */
    private String changeRequestNumber;

    /**
     * The date when the change request was submitted. Important for tracking the process duration.
     */
    private Date submissionDate;

    /**
     * The current status of the change request, indicating its progress through predefined stages.
     */
    private ChangeRequestStatuses changeRequestStatus;

    /**
     * Categorizes the organizational areas impacted by the change, aiding in impact assessment.
     */
    private AreasAffected areasAffected;

    /**
     * Descriptive explanation for why the change is being proposed.
     */
    private String reasonForChange;

    /**
     * Details the anticipated effects the change will have on operations, compliance, or safety.
     */
    private String expectedImpact;

    /**
     * Outlines the specific actions proposed to implement the change.
     */
    private String proposedActions;

    /**
     * Indicates when the change request was evaluated for its validity and potential impact.
     */
    private Date evaluationDate;

    /**
     * Specifies the date when the proposed change is intended to be or was actually implemented.
     */
    private Date implementationDate;

    /**
     * Links a change request to a specific deviation record that triggered the change.
     */
    private DeviationRecords fkDeviationRecordId;

    /**
     * Associates a change request with a CAPA record if the change arose from corrective actions.
     */
    private CapaRecords fkCapaRecordId;

    // Insert here getters and setters

    /**
     * Enum representing the possible statuses of a change request.
     */
    public enum ChangeRequestStatuses {
        SUBMITTED,
        UNDER_REVIEW,
        APPROVED,
        REJECTED,
        IMPLEMENTED
    }

    /**
     * Enum representing different areas that can be affected by a change request.
     */
    public enum AreasAffected {
        OPERATIONS,
        QUALITY_CONTROL,
        PRODUCTION,
        SUPPLY_CHAIN
    }

    // Constructors, getters, and setters are omitted for brevity
}