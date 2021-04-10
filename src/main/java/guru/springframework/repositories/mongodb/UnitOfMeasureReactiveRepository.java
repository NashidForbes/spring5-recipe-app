package guru.springframework.repositories.mongodb;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UnitOfMeasureReactiveRepository extends
        ReactiveMongoRepository<UnitOfMeasure, String> {
    Mono<UnitOfMeasure> findByUnits(String unit);
}
