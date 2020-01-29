package cz.thomas.springtest.repository.entity;

import cz.thomas.springtest.dto.MarkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Mark")
public class Mark {

    @Id
    @SequenceGenerator(name = "mark_seq", sequenceName = "mark_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "subject")
    private MarkDTO.Subject subject;
    @ManyToOne
    private Student student;
    @Column(name = "mark")
    private int mark;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(MarkDTO.Subject subject) {
        this.subject = subject;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
