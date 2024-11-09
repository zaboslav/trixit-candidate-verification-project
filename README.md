# Candidate verification

Project to test skills of potential candidate for Trixit projects.

### Project description
Platform for currency conversions allows customer to convert between supported currencies. Conversion is done via base currency (USD). Platform is downloading rates periodically, so we are able to provide conversion to the past.

Customer can only convert between assigned currencies.

### For developers
To build project run: `mvn clean package`

### Application configuration

```
# DB Configuration
spring.datasource.url=jdbc:postgresql://{pg_hostname}:{pg_port}/{pg_schema}
spring.datasource.username={pg_user}
spring.datasource.password={pg_password}

# limit for historical conversions
candidate.testing.dataFrom=2020-01-01
# Base currency for conversion
candidate.testing.baseCurrency=USD
# Conversion rate API
candidate.testing.apiUrl=https://api.vatcomply.com/rates?base={base}&date={date}
```
### Main library used
* MapStruct
* Spring Boot
* Spring Data JPA
* Lombok
* Liquibase


# Candidate review

Relation database is needed, but requirement is that application will work with PostgreSQL. You can use test container, docker etc.

## Conversion implementation - 30 minutes - priority 1
Design and implement REST service which provides API to convert amount from one currency to other.

Requirements:
* Customer can only use assigned currencies
* The conversion is done via the base currency
* Two options for conversion:
  * Using latest rate
  * Conversion to date


## DB structure polishing - 20 minutes - priority 2
Check DB and polish it to provider performance and protect consistency of data. All changes has to be implemented via Liquibase script.


## Auditlog service implementation - 20 minutes - priority 3
There is mock service for audit, but real implementation has to be done.

## Error handling implementation - 20 minutes - priority 4
Application should return specific error structure:

```
@AllArgsConstructor
public class ErrorResponse {
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String path;
}

```
There are some exception allows developer to propagate error from code by using specific Exceptions:
* cz.trixit.demo.project.candidate.exception.ApiResponseException
* cz.trixit.demo.project.candidate.exception.BadRequestException
* cz.trixit.demo.project.candidate.exception.ConflictException
* cz.trixit.demo.project.candidate.exception.NotFoundException

So, implement exception / error mapping to proper to specific error.

## Overall code review - 20 minutes - priority 5
Check existing code and fix you do not like. Comments would be accepted as well.
