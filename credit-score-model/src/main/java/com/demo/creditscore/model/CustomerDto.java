package com.demo.creditscore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CustomerDto {
    private Long id;
    @NotBlank(message = "First Name is a mandatory field")
    @Size(min = 2, max = 255, message = "First Name '${validatedValue}' must be between {min} and {max} characters long")
    private String firstName;
    @NotBlank(message = "Last Name is a mandatory field")
    @Size(min = 2, max = 255, message = "Last Name '${validatedValue}' must be between {min} and {max} characters long")
    private String lastName;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 70, message = "Age should not be greater than 70")
    private Integer age;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "Date of birth is a mandatory field and must not be empty")
    private LocalDate dateOfBirth;
    @NotNull(message = "Annual Income is a mandatory field and must not be empty")
    @Positive(message = "Annual Income could not be less than zero")
    private Integer annualIncome;
}

