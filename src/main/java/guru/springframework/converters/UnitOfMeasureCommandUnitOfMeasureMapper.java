package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitOfMeasureCommandUnitOfMeasureMapper {

    UnitOfMeasureCommandUnitOfMeasureMapper
            INSTANCE = Mappers.getMapper(UnitOfMeasureCommandUnitOfMeasureMapper.class);

    UnitOfMeasure UOMCommandToUOM(UnitOfMeasureCommand unitOfMeasureCommand);

    UnitOfMeasureCommand UOMToUOMCommand(UnitOfMeasure unitOfMeasure);
}
