package model;

import java.time.LocalDate;

/**
 * Represents the CAPA (Corrective and Preventive Action) record entity.
 * This class holds information about CAPA records including their source, proposed actions, status, and relevant dates.
 */
public class CapaRecords {

    /**
     * Primary key for the CAPA records table, unique identifier for each CAPA record.
     */
    private int id;
    
    /**
     * Details the specific issue or non-conformity identified that requires corrective and preventive action.
     */
    private String issueDescription;
    
    /**
     * Specifies the origin trigger of the CAPA.
     */
    private CapaSources capaSource;
    
    /**
     * Outlines the corrective and preventive measures proposed to address the identified issue.
     */
    private String proposedActions;
    
    /**
     * Tracks the lifecycle stage of the CAPA.
     */
    private CapaStatuses capaStatus;
    
    /**
     * Designates the staff member responsible for implementing the actions.
     */
    private Personnel assignedPersonnel;
    
    /**
     * Specifies the target completion date for the corrective and preventive actions to be implemented.
     */
    private LocalDate dueDate;
    
    /**
     * Records the actual date when the CAPA was resolved or closed.
     */
    private LocalDate completionDate;

    /**
     * Enum for CAPA sources options.
     */
    public enum CapaSources {
        DEVIATIONS, AUDIT_FINDINGS, OTHER_QUALITY_PROCESSES
    }

    /**
     * Enum for CAPA status options.
     */
    public enum CapaStatuses {
        OPEN, UNDER_REVIEW, APPROVED, CLOSED
    }

    // Getters and Setters

    /**
     * Gets the ID of the CAPA record.
     * @return the CAPA record ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the CAPA record.
     * @param id the CAPA record ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the issue description of the CAPA record.
     * @return the CAPA issue description.
     */
    public String getIssueDescription() {
        return issueDescription;
    }

    /**
     * Sets the issue description of the CAPA record.
     * @param issueDescription the CAPA issue description to set.
     */
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    /**
     * Gets the source of the CAPA record.
     * @return the CAPA source.
     */
    public CapaSources getCapaSource() {
        return capaSource;
    }

    /**
     * Sets the source of the CAPA record.
     * @param capaSource the CAPA source to set.
     */
    public void setCapaSource(CapaSources capaSource) {
        this.capaSource = capaSource;
    }

    /**
     * Gets the proposed actions of the CAPA record.
     * @return the CAPA proposed actions.
     */
    public String getProposedActions() {
        return proposedActions;
    }

    /**
     * Sets the proposed actions of the CAPA record.
     * @param proposedActions the CAPA proposed actions to set.
     */
    public void setProposedActions(String proposedActions) {
        this.proposedActions = proposedActions;
    }

    /**
     * Gets the status of the CAPA record.
     * @return the CAPA status.
     */
    public CapaStatuses getCapaStatus() {
        return capaStatus;
    }

    /**
     * Sets the status of the CAPA record.
     * @param capaStatus the CAPA status to set.
     */
    public void setCapaStatus(CapaStatuses capaStatus) {
        this.capaStatus = capaStatus;
    }

    /**
     * Gets the assigned personnel responsible for the CAPA record.
     * @return the assigned personnel.
     */
    public Personnel getAssignedPersonnel() {
        return assignedPersonnel;
    }

    /**
     * Sets the assigned personnel responsible for the CAPA record.
     * @param assignedPersonnel the assigned personnel to set.
     */
    public void setAssignedPersonnel(Personnel assignedPersonnel) {
        this.assignedPersonnel = assignedPersonnel;
    }

    /**
     * Gets the due date of the CAPA record.
     * @return the CAPA due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the CAPA record.
     * @param dueDate the due date to set.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the completion date of the CAPA record.
     * @return the CAPA completion date.
     */
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets the completion date of the CAPA record.
     * @param completionDate the completion date to set.
     */
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}