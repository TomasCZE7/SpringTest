package cz.thomas.springtest.repository.entity;

import cz.thomas.springtest.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "School")
public class SchoolClass {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "specialization")
    private String Specialization;

    @Column(name = "year")
    private String year;

    @OneToMany(mappedBy = "partOfClass", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

}
