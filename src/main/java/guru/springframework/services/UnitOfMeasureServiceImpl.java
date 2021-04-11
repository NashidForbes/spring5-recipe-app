package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureCommandUnitOfMeasureMapper;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import guru.springframework.services.interfaces.UnitOfMeasureService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureCommandUnitOfMeasureMapper
            unitOfMeasureCommandUnitOfMeasureMapper;

    public UnitOfMeasureServiceImpl(
            UnitOfMeasureReactiveRepository unitOfMeasureRepository,
            UnitOfMeasureCommandUnitOfMeasureMapper unitOfMeasureCommandUnitOfMeasureMapper) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommandUnitOfMeasureMapper =
                unitOfMeasureCommandUnitOfMeasureMapper;
    }


    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {
        return unitOfMeasureReactiveRepository
                .findAll()
                .map(unitOfMeasureCommandUnitOfMeasureMapper::UOMToUOMCommand);


/*        return StreamSupport.stream(unitOfMeasureReactiveRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureCommandUnitOfMeasureMapper::UOMToUOMCommand)
                .collect(Collectors.toList());*/
    }
}
