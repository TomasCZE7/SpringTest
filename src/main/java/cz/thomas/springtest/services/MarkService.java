package cz.thomas.springtest.services;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.mapping.MarkMapper;
import cz.thomas.springtest.repository.MarkRepository;
import cz.thomas.springtest.repository.StudentRepository;
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
        markRepository.save(markMapper.fromDTO(dto, studentId));
    }

    public void deleteMark(Long markId) {
        markRepository.deleteById(markId);
    }
}
