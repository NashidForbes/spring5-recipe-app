package guru.springframework.repositories.mongodb;

import guru.springframework.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category,
        String> {
    Mono<Category> findByDepartmentName(String departmentName);
}
