package cz.thomas.springtest.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @ManyToOne
    private SchoolClass partOfClass;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Mark> graduationMarks = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addGraduationMark(List<Mark> graduationMarks) {
        this.graduationMarks = graduationMarks;
    }


}
