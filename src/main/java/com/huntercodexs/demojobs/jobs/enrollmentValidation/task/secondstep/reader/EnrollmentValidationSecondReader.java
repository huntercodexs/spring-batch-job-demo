package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.mapper.EnrollmentValidationMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EnrollmentValidationSecondReader {

    @Bean
    public JdbcCursorItemReader<EnrollmentValidationDto> readerSecondStep(
            @Qualifier("mysqlDataSource") DataSource dataSource
    ) {

        String sql =
        """
        SELECT * FROM PRODUCTS WHERE ID IS NOT NULL
        """;

        System.out.println("[READER-SECOND-STEP] >>> readerSecondStep");

        return new JdbcCursorItemReaderBuilder<EnrollmentValidationDto>()
            .name("secondStepReader")
            .sql(sql)
            .dataSource(dataSource)
            .rowMapper(new EnrollmentValidationMapper())
            .verifyCursorPosition(false)
            .build();

    }
}






