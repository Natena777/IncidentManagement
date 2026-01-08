package org.example.incidentmanagement.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class DatabaseOperations {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseOperations() {
        // Manual DataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://aws-1-eu-north-1.pooler.supabase.com:5432/postgres?sslmode=require");
        dataSource.setUsername("postgres.bdxzpljjgdscptfcybld");
        dataSource.setPassword("Nikanatena27#");

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Case Access Rights
    public void insertCasesAccessRight(Integer caseId, Integer userId, Integer roleId, Integer assigneeGroupId,
                                       Integer createdBy, LocalDateTime createdOn,
                                       boolean canRead, boolean canEdit, boolean canDelete) {
        String sql = """
                INSERT INTO cases_access_rights
                (case_id, user_id, role_id, assignee_group_id, created_on, created_by, can_read, can_edit, can_delete) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, caseId, userId, roleId, assigneeGroupId, createdOn, createdBy, canRead, canEdit, canDelete);

    }

    public List<Map<String, Object>> getCasesAccessRights(Integer caseId) {
        String sql = """
                select * from cases_access_rights where case_id=?
                """;

        return jdbcTemplate.queryForList(sql, caseId);

    }






}

