package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureCommandUnitOfMeasureMapper;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.UnitOfMeasureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureCommandUnitOfMeasureMapper
            unitOfMeasureCommandUnitOfMeasureMapper;

    public UnitOfMeasureServiceImpl(
            UnitOfMeasureRepository unitOfMeasureRepository,
            UnitOfMeasureCommandUnitOfMeasureMapper unitOfMeasureCommandUnitOfMeasureMapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommandUnitOfMeasureMapper =
                unitOfMeasureCommandUnitOfMeasureMapper;
    }


    @Override
    public List<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureCommandUnitOfMeasureMapper::UOMToUOMCommand)
                .collect(Collectors.toList());
    }
}
