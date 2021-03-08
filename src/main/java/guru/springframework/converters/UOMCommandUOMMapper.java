package guru.springframework.converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.mapstruct.Mapper;

@Mapper
public interface UOMCommandUOMMapper {
    UnitOfMeasure UOMCommandToUOM(UnitOfMeasureCommand unitOfMeasureCommand);
    UnitOfMeasureCommand UOMToUOMCommand(UnitOfMeasure unitOfMeasure);
}
