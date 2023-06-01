package com.huntercodexs.demojobs.jobs.enrollmentValidation.mapper;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentValidationMapper implements RowMapper<EnrollmentValidationDto> {

    public EnrollmentValidationDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();

        enrollmentValidationDto.setId(rs.getInt("ID"));
        enrollmentValidationDto.setName(rs.getString("NAME"));
        enrollmentValidationDto.setDescription(rs.getString("DESCRIPTION"));
        enrollmentValidationDto.setPrice(rs.getString("PRICE"));

        System.out.println("[READER] => [MAPPER] >>> mapRow");
        System.out.println(rs.getInt("ID"));
        System.out.println(rs.getString("NAME"));

        return enrollmentValidationDto;
    }
}
