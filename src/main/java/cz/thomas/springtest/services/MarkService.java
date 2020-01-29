package cz.thomas.springtest.services;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.mapping.MarkMapper;
import cz.thomas.springtest.repository.MarkRepository;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.repository.entity.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MarkService {

    @Autowired
    MarkRepository markRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MarkMapper markMapper;

    @Transactional
    public void addMark(MarkDTO dto, Long studentId){
        addMark(markMapper.fromDTO(dto, studentId));
    }

    @Transactional
    public void addMark(Mark mark){
        markRepository.save(mark);
    }


    public void deleteMark(Long markId) {
        markRepository.deleteById(markId);
    }
}
