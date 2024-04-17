package model;

import java.util.Date;

/**
 * Represents filter options for CAPA (Corrective and Preventive Actions) records, allowing users to set preferences on how records are displayed based on various parameters such as date range, status, and trigger source. 
 * This defining structure aids in personalizing the CAPA Records Overview page by aligning the displayed data with user preferences.
 */
public class CapaRecordsFilterOptions {

    /**
     * Primary key; uniquely identifies each record in capa_records_filter_options.
     */
    private Integer id;

    /**
     * Identifies the user to whom the filter option settings apply. 
     * Instrumental in personalizing the CAPA Records Overview page by aligning the displayed data with user preferences.
     */
    private Integer userId;

    /**
     * Stores preferred date ranges for viewing CAPA records. 
     * Enhances user experience by allowing quick access to CAPA records within selectable time frames, supporting both retrospective analysis and ongoing oversight.
     */
    private String dateRange;

    /**
     * Stores preferred CAPA record statuses for filtering. 
     * Assists in tailoring the records displayed on the overview page, facilitating focused review of CAPAs based on their lifecycle stage.
     */
    private CapaStatuses status;

    /**
     * Specifies preferred origins of CAPA triggers for filtering purposes. 
     * Helps in categorizing and streamlining CAPA records according to their source, enabling targeted analysis and follow-up actions.
     */
    private TriggerSources triggerSource;

    /**
     * Stores the last used filter settings by the user. 
     * Aids in user experience by pre-loading preferred settings upon the user's return, reducing setup time and enhancing the usability of the CAPA Records Overview page.
     */
    private String lastUsedSettings;

    // Enum for CAPA Statuses
    public enum CapaStatuses {
        OPEN, UNDER_REVIEW, APPROVED, CLOSED
    }

    // Enum for Trigger Sources
    public enum TriggerSources {
        AUDIT, DEVIATION, MARKET_COMPLAINT, OTHER_QUALITY_PROCESSES
    }

    // Getters and Setters
    /**
     * Gets the unique identifier for the filter options record.
     * @return The unique identifier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the filter options record.
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user ID to whom the filter options apply.
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID to whom the filter options apply.
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the preferred date range for viewing CAPA records.
     * @return The preferred date range.
     */
    public String getDateRange() {
        return dateRange;
    }

    /**
     * Sets the preferred date range for viewing CAPA records.
     * @param dateRange The preferred date range to set.
     */
    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    /**
     * Gets the stored preferred CAPA record status.
     * @return The CAPA record status.
     */
    public CapaStatuses getStatus() {
        return status;
    }

    /**
     * Sets the stored preferred CAPA record status.
     * @param status The CAPA record status to set.
     */
    public void setStatus(CapaStatuses status) {
        this.status = status;
    }

    /**
     * Gets the specified preferred origin of CAPA triggers for filtering.
     * @return The CAPA trigger source.
     */
    public TriggerSources getTriggerSource() {
        return triggerSource;
    }

    /**
     * Sets the specified preferred origin of CAPA triggers for filtering.
     * @param triggerSource The CAPA trigger source to set.
     */
    public void setTriggerSource(TriggerSources triggerSource) {
        this.triggerSource = triggerSource;
    }

    /**
     * Gets the last used filter settings by the user.
     * @return The last used filter settings.
     */
    public String getLastUsedSettings() {
        return lastUsedSettings;
    }

    /**
     * Sets the last used filter settings by the user.
     * @param lastUsedSettings The last used filter settings to set.
     */
    public void setLastUsedSettings(String lastUsedSettings) {
        this.lastUsedSettings = lastUsedSettings;
    }
}