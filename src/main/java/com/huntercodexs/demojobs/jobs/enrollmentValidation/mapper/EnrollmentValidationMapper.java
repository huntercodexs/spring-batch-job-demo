package com.huntercodexs.demojobs.jobs.enrollmentValidation.mapper;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EnrollmentValidationMapper implements RowMapper<EnrollmentValidationDto> {

    @Override
    public EnrollmentValidationDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();

        enrollmentValidationDto.setId(rs.getInt("id"));
        enrollmentValidationDto.setName(rs.getString("name"));
        enrollmentValidationDto.setDescription(rs.getString("description"));
        enrollmentValidationDto.setPrice(rs.getString("price"));

        System.out.println("[READER] => [MAPPER] >>> mapRow");
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("name"));

        return enrollmentValidationDto;
    }
}
