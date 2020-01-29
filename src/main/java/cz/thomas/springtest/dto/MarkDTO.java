package cz.thomas.springtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkDTO {

    public enum Subject {
        MATH, ENGLISH, IT, PHYSICS
    }

    private Long id;
    private Subject subject;
    private int mark;

}
