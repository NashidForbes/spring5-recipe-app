package guru.springframework.services.interfaces;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux getRecipes();

    Mono<Recipe> findById(String aString);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

    Mono<RecipeCommand> findCommandById(String anyString);

    Mono<Void> deleteById(String idToDelete);
}
