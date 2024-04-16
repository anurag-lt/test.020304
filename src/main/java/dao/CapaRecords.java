package dao;


import model.CapaRecords;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.CapaRecords.CapaSources;
import model.CapaRecords.CapaStatuses;
import java.util.Map.Entry;


public class CapaRecordsDAO {

	
	/**
	 * Inserts a new CAPA record into the database with the details provided from the CAPA Record Initiation Form.
	 * @param issueDescription Details the specific issue or non-conformity identified that requires corrective and preventive action.
	 * @param capaSource Origin trigger of the CAPA, such as deviations, audit findings, or other quality processes.
	 * @param proposedActions Outlines the corrective and preventive measures proposed to address the identified issue.
	 * @param capaStatus The lifecycle stage of the CAPA (e.g., open, under review, approved, closed).
	 * @param assignedPersonnelId The ID of the staff member responsible for implementing the actions.
	 * @param dueDate The target completion date for the corrective and preventive actions to be implemented.
	 * @param completionDate The actual date when the CAPA was resolved or closed.
	 * @return The auto-generated key of the inserted CAPA record, or -1 if the insertion failed.
	 */
	public int insertCapaRecord(String issueDescription, CapaRecords.CapaSources capaSource, String proposedActions, CapaRecords.CapaStatuses capaStatus, int assignedPersonnelId, LocalDate dueDate, LocalDate completionDate) {
	    Connection conn = DatabaseUtility.connect();
	    PreparedStatement pstmt = null;
	    String insertSQL = "INSERT INTO capa_records (issue_description, capa_source, proposed_actions, capa_status, assigned_personnel_id, due_date, completion_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    int generatedId = -1;
	    try {
	        pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, issueDescription);
	        pstmt.setString(2, capaSource.toString());
	        pstmt.setString(3, proposedActions);
	        pstmt.setString(4, capaStatus.toString());
	        pstmt.setInt(5, assignedPersonnelId);
	        pstmt.setDate(6, Date.valueOf(dueDate));
	        pstmt.setDate(7, Date.valueOf(completionDate));
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            ResultSet rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	    } finally {
	        DatabaseUtility.disconnect(conn);
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	    return generatedId;
	}
	
	/**
	 * Fetches a list of CAPA records with optional filtering and pagination.
	 * @param filters Key-value pairs for filtering the CAPA records, such as by status or trigger source.
	 * @param pageNumber For pagination, the number of the page to retrieve.
	 * @param pageSize The number of records per page for pagination purposes.
	 * @return A list of CAPA records matching the criteria.
	 */
	public List<CapaRecords> fetchAllCapaRecords(Map<String, String> filters, int pageNumber, int pageSize) {
	    List<CapaRecords> records = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DatabaseUtility.connect();
	        StringBuilder query = new StringBuilder("SELECT * FROM capa_records WHERE 1=1 ");
	        // Apply filters
	        for (Entry<String, String> filter : filters.entrySet()) {
	            query.append(" AND ").append(filter.getKey()).append("=?");
	        }
	        query.append(" LIMIT ? OFFSET ?");
	        stmt = conn.prepareStatement(query.toString());
	        int index = 1;
	        for (Entry<String, String> filter : filters.entrySet()) {
	            stmt.setString(index++, filter.getValue());
	        }
	        stmt.setInt(index++, pageSize);
	        stmt.setInt(index, (pageNumber - 1) * pageSize);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            CapaRecords record = new CapaRecords();
	            record.setId(rs.getInt("id"));
	            record.setIssueDescription(rs.getString("issue_description"));
	            record.setCapaSource(CapaSources.valueOf(rs.getString("capa_source")));
	            record.setProposedActions(rs.getString("proposed_actions"));
	            record.setCapaStatus(CapaStatuses.valueOf(rs.getString("capa_status")));
	            // Assuming PersonnelId is managed separately
	            record.setDueDate(rs.getDate("due_date").toLocalDate());
	            record.setCompletionDate(rs.getDate("completion_date") != null ? rs.getDate("completion_date").toLocalDate() : null);
	            records.add(record);
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	    } finally {
	        DatabaseUtility.disconnect(conn);
	        try { if (stmt != null) stmt.close(); if (rs != null) rs.close(); }
	        catch (SQLException ex) { Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);}
	    }
	    return records;
	}
	
	/**
	 * Updates an existing CAPA record with new details in the database.
	 * @param id Unique identifier for the CAPA record to be updated.
	 * @param issueDescription Updated details of the specific issue or non-conformity identified.
	 * @param capaSource Updated origin trigger of the CAPA.
	 * @param proposedActions Updated corrective and preventive measures proposed.
	 * @param capaStatus Updated lifecycle stage of the CAPA.
	 * @param assignedPersonnelId Updated staff member responsible for implementing the actions.
	 * @param dueDate Updated target completion date for the actions to be implemented.
	 * @param completionDate Updated actual date when the CAPA was resolved or closed.
	 */
	public void updateCapaRecord(int id, String issueDescription, CapaSources capaSource, String proposedActions, CapaStatuses capaStatus, int assignedPersonnelId, LocalDate dueDate, LocalDate completionDate) {
	    Connection conn = DatabaseUtility.connect();
	    try {
	        String query = "UPDATE capa_records SET issue_description = ?, capa_source = ?, proposed_actions = ?, capa_status = ?, assigned_personnel_id = ?, due_date = ?, completion_date = ? WHERE id = ?";
	
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, issueDescription);
	        pstmt.setString(2, capaSource.name());
	        pstmt.setString(3, proposedActions);
	        pstmt.setString(4, capaStatus.name());
	        pstmt.setInt(5, assignedPersonnelId);
	        pstmt.setDate(6, Date.valueOf(dueDate));
	        pstmt.setDate(7, Date.valueOf(completionDate));
	        pstmt.setInt(8, id);
	
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error updating CAPA record", e);
	    } finally {
	        DatabaseUtility.disconnect(conn);
	    }
	}
}
