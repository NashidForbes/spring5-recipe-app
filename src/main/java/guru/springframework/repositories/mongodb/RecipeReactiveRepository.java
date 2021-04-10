package guru.springframework.repositories.mongodb;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecipeReactiveRepository extends
        ReactiveMongoRepository<Recipe, String> {
    Flux<Recipe> findAll();
}
