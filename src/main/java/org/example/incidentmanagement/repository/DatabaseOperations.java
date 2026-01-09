package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOperations implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperations.class);

    private static final String DB_URL = "jdbc:postgresql://aws-1-eu-north-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String DB_USER = "postgres.bdxzpljjgdscptfcybld";
    private static final String DB_PASSWORD = "Nikanatena27#";

    private Connection connection;

    public DatabaseOperations() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            this.connection.setAutoCommit(true);
            logger.debug("Database connection established");
        } catch (SQLException e) {
            logger.error("Failed to establish database connection", e);
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    logger.debug("Database connection closed successfully");
                }
            } catch (SQLException e) {
                logger.error("Failed to close database connection", e);
            }
        }
    }

    // Case Access Rights
    public void insertCasesAccessRight(Integer caseId, Integer userId, Integer roleId, Integer assigneeGroupId,
                                       Integer createdBy, LocalDateTime createdOn,
                                       boolean canRead, boolean canEdit, boolean canDelete) {

        String sql = """
                INSERT INTO cases_access_rights
                (case_id, user_id, role_id, assignee_group_id, created_on, created_by, can_read, can_edit, can_delete) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, caseId);
            stmt.setObject(2, userId);
            stmt.setObject(3, roleId);
            stmt.setObject(4, assigneeGroupId);
            stmt.setTimestamp(5, Timestamp.valueOf(createdOn));
            stmt.setInt(6, createdBy);
            stmt.setBoolean(7, canRead);
            stmt.setBoolean(8, canEdit);
            stmt.setBoolean(9, canDelete);

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to insert case access right for caseId: {}", caseId, e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }
    }

    public List<Map<String, Object>> getCasesAccessRights(Integer caseId) {

        String sql = "SELECT * FROM cases_access_rights WHERE case_id = ?";
        List<Map<String, Object>> results = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, caseId);

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }

            return results;

        } catch (SQLException e) {
            logger.error("Failed to get case access rights for caseId: {}", caseId, e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }
    }

    public void deleteCasesAccessRights(Integer id) {

        String sql = "DELETE FROM cases_access_rights WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Failed to delete case access right with id: {}", id, e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }
    }
}