
# SPRING BATCH JOB DEMO

# Language

- Language: <a href="README.md">Portugues Brazil (pt-br)</a>  |  Inglês (en)

# Pre Requisites

- Java 17
- H2 Database
- Mysql Database
- Oraclelinux
- Sftp Server
- Mail Server

> To help and optimize the use of this demo project you can use the Docker Series which is also available
> present in this github account whose link is https://github.com/huntercodexs/docker-series, where there is a
> pre-configured environment on the h2_mysql_oracle_sftp_mailhog branch that can be accessed through the link
> https://github.com/huntercodexs/docker-series/tree/h2_mysql_oracle_sftp_mailhog, follow usage instructions
> and have a good experience

# Project proposal (what is it about?)

This is a demo project to demonstrate how a job works using the java spring batch framework, although there are many
configuration formats and uses of spring batch to create a job, the standard definition of
operation, as shown below:

![spring-batch-default.png](data/media/spring-batch-default.png)

The proposal goes beyond the demonstration of a flow for jobs using spring batch, where integrations will also be shown
with external features like an SFTP server, sending email and reading files for bulk processing. Below
we have the complete flow of the project, showing all the steps and processing carried out during the life cycle of a
job execution

- Overview Diagram

![job-flow-structure-overview.png](data/media/job-flow-structure-overview-V2.png)

# Project presentation

The project can be described as follows:

- The name of the JOB was set to EnrollmentValidation;
- The Job was developed for processing product registrations that must be validated, sent to the server
  FTP and reported to those responsible for the products, such as sales, inventory and purchasing.
- The JOB has 3 stages (steps) and each stage has 3 tasks, reading (reader), processing (processor) and recording (writer);
    - STEP 1:
        - In step 1 (FirstStep) the JOB reads the database, through the ItemReader task, to extract the data that
          must be processed, i.e. validated and sent to the FTP server
        - Soon after, still in the ItemProcessor task, the data is processed and validated for the subsequent step
        - In the last task of step 1, that is in the ItemWriter task, the data is definitely generated and sent to the
          FTP server, as well as being reported by email to the appropriate persons responsible
        - After sending the files successfully, the files are marked as .processed, for example:
            - spring-batch-job-demo-data-20230608234700.txt > spring-batch-job-demo-data-20230608234700.txt.processed
        - At this stage, the products that have been processed are also sent by email
    - STEP 2:
        - In step 2 (SecondStep) the JOB reads the files that were processed by the service provider and that
          are available on the FTP server in the download folder, in fact they are response or return files that the provider
          generated during the processing of the files sent in STEP 1 and which must be received by the JOB, processed and
          informing those responsible of the status of this processing
        - After downloading the files they will be in the predefined folder in the properties: for example:
            - spring-batch-job-demo-data-20230608193001-20230608234702.download
    - STEP 3:
        - In step 3 (ThirdStep) the files that were received in the previous step will be processed and finalized as
          the rules and conditions foreseen in the system and will be marked as finished, for example:
            - spring-batch-job-demo-data-20230608193001-20230608234702.download.finished

# How it works

When the JOB runs, it schedules the steps and tasks as scheduled to run during the hour and
minute defined in the properties file.

Basically, it reads the database in search of product records to be validated and consolidated,
sending the products to the FTP server through a file to then be processed.

Soon after processing by the service provider (on the FTP server) response files are made available regarding
processing the files, so the JOB reads and captures these files to download to the local machine
where the JOB is being executed.

To finalize the processing flow, the JOB executes the last step, which in this case is the processing of the files that
were downloaded in the previous step and that must be processed and finalized.

All steps generate a report by email informing the status and what is happening during a JOB processing.

# Settings

To use this project, you must pay attention to the following points:

- Create the database, in this case called huntercodexs:

<pre>
CREATE DATABASE huntercodexs CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
</pre>

- Populate the created database:

<pre>
CREATE TABLE `PRODUCTS` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `SALES` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quantity` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO PRODUCTS (id, name, description, price) VALUES (1, 'Plastic Bags', 'Plastic bags to shopping', '40,00');
INSERT INTO PRODUCTS (id, name, description, price) VALUES (2, 'Wallet Plan', 'Money Wallet', '20,00');

INSERT INTO SALES (id, product, quantity, total) VALUES (1, 'Plastic Bags', '3', '120,00');
INSERT INTO SALES (id, product, quantity, total) VALUES (2, 'Wallet Plan', '2', '80,00');

</pre>

- Configure access to the SFTP server:

<pre>
## SFTPD
# -------------------------------------------------------------------------------------------------------------------
sftp.server.address=192.168.0.204
sftp.server.port=35022
sftp.username=sftp-user
sftp.password=sftp-pass
sftp.allow.unknown-hosts=true
sftp.folder-upload-path=upload/
sftp.folder-download-path=download/
sftp.extension-files-download=.txt.response
sftp.localfolder-receive-path=/home/$USER/txt/download/
sftp.localfolder-finished-path=/home/$USER/txt/download/finished/
</pre>

- Configure JOB scheduling:

<pre>
## JOB SETTINGS
# -------------------------------------------------------------------------------------------------------------------
job.enrollment-validation.enabled=true
job.enrollment-validation.hour=23
job.enrollment-validation.minute=47
</pre>

> For more details check application.properties file

# Extras

Before running the project, make sure you have the environment correctly configured.
