package guru.springframework.converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UOMCommandUOMMapper {

    UOMCommandUOMMapper INSTANCE = Mappers.getMapper(UOMCommandUOMMapper.class);

    UnitOfMeasure UOMCommandToUOM(UnitOfMeasureCommand unitOfMeasureCommand);

    UnitOfMeasureCommand UOMToUOMCommand(UnitOfMeasure unitOfMeasure);
}
