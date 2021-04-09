package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureCommandUnitOfMeasureMapper;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureCommandUnitOfMeasureMapper unitOfMeasureCommandUnitOfMeasureMapper;

    @Mock
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImplTest() {
        this.unitOfMeasureCommandUnitOfMeasureMapper =
                unitOfMeasureCommandUnitOfMeasureMapper.INSTANCE;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                unitOfMeasureCommandUnitOfMeasureMapper);
    }

    @Test
    void listAllUoms() {
        //given
        List<UnitOfMeasure> unitOfMeasures = new ArrayList<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        List<UnitOfMeasureCommand> commands = service.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}