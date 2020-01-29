package cz.thomas.springtest.dto.mapping;

import cz.thomas.springtest.dto.SchoolClassDTO;
import cz.thomas.springtest.repository.entity.SchoolClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClassMapper {

    public abstract SchoolClassDTO toDTO(SchoolClass source);

    public abstract SchoolClass fromDTO(SchoolClassDTO source);

}
