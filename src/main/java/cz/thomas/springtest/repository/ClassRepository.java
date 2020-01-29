package cz.thomas.springtest.repository;

import cz.thomas.springtest.repository.entity.Mark;
import cz.thomas.springtest.repository.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository  extends JpaRepository<SchoolClass, Long> {

    @Override
    Optional<SchoolClass> findById(Long classId);

    @Override
    void deleteById(Long classId);
}
