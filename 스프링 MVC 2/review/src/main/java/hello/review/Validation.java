package hello.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Validation {

//    @NotNull(message = "값 필수 입력!")
//    @NotNull(message = "{NotNull}")
    @NotNull
    @Min(1)
    private Integer minVal;

    @Max(value = 100, message = "{Max}")
    private Integer maxVal;

    @Range(min = 10, max = 100, message = "{Range}")
    private Integer rangeVal;

}

