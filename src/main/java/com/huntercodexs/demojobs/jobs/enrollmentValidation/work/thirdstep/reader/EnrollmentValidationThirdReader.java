package com.huntercodexs.demojobs.jobs.enrollmentValidation.work.thirdstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.mapper.EnrollmentValidationMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EnrollmentValidationThirdReader {

    @Bean
    public JdbcCursorItemReader<EnrollmentValidationDto> readerThirdStep(
            @Qualifier("mysqlDataSource") DataSource dataSource
    ) {

        String sql =
        """
        SELECT * FROM PRODUCTS WHERE ID IS NOT NULL
        """;

        System.out.println("[READER-FIRST-STEP] >>> readerThirdStep");

        return new JdbcCursorItemReaderBuilder<EnrollmentValidationDto>()
            .name("thirdStepReader")
            .sql(sql)
            .dataSource(dataSource)
            .rowMapper(new EnrollmentValidationMapper())
            .verifyCursorPosition(false)
            .build();

    }
}






