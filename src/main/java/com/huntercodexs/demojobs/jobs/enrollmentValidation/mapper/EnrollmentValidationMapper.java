package com.huntercodexs.demojobs.jobs.enrollmentValidation.mapper;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class EnrollmentValidationMapper implements RowMapper<EnrollmentValidationDto> {

    @Override
    public EnrollmentValidationDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();

        try {
            enrollmentValidationDto.setId(rs.getInt("id"));
            enrollmentValidationDto.setName(rs.getString("name"));
            enrollmentValidationDto.setDescription(rs.getString("description"));
            enrollmentValidationDto.setPrice(rs.getString("price"));
        } catch (RuntimeException re) {
            log.error("Mapper say: (mapRow) Exception: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }

        log.info("Mapper say: (mapRow) enrollmentValidationDto: " + enrollmentValidationDto);

        return enrollmentValidationDto;
    }
}
