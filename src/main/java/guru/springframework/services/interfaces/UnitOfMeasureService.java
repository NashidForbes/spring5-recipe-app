package guru.springframework.services.interfaces;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.List;

public interface UnitOfMeasureService {
    List<UnitOfMeasureCommand> listAllUoms();
}
