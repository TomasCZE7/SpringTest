package cz.thomas.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassDTO {

    private Long id;
    private String Specialization;
    private String year;
    private List<StudentDTO> students = new ArrayList<StudentDTO>();

}
