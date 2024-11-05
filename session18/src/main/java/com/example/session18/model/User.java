package com.example.session18.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String name;
    private Integer age;
    private String email;
}
    // @NotNull(message = "Name cannot be null")
    // private String name;
    // @NotBlank(message = "Last name cannot be blank")
    // private String lastName;
    // @NotEmpty(message = "Course cannot be empty")
    // private List<String> course;

    // @Min(14)
    // private Integer attendance;
    // @Max(8)
    // private Integer classroom;
    // @Size(min = 2, max = 4)
    // private List<String> supervisor;

    // @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+")
    // private String fullname;
    // @Email
    // private String email;
    // @Digits(integer = 1, fraction = 2)
    // private Float gpa;

    // @Past
    // private LocalDate birthdate;
    // @Future
    // private LocalDateTime nextSession;

    // @AssertTrue
    // private String isActive;
    // @AssertFalse
    // private String isNewStudent;
//}
