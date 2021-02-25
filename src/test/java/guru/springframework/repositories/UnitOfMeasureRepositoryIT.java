package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByUnits() {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnits("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getUnits());

    }

    @Test
    public void findByUnitsCup() {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnits("Cup");

        assertEquals("Cup", uomOptional.get().getUnits());

    }
}