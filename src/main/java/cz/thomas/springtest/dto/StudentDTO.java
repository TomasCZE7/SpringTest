package cz.thomas.springtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int classNumber;
    private String specialization;
    private String address;
    private List<MarkDTO> graduationMarks;

}
