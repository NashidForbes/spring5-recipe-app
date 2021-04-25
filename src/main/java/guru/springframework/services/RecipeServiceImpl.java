package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandRecipeMapper;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandRecipeMapper mapper;

    public RecipeServiceImpl(
            RecipeReactiveRepository recipeReactiveRepository,
            RecipeCommandRecipeMapper mapper) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux getRecipes() {
        log.debug("Retrieving all recipes");
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        Mono<Recipe> recipeOptional = recipeReactiveRepository.findById(id);
        if (recipeOptional == null) {
            // throw new RuntimeException("Recipe not found");
            throw new NotFoundException("Recipe not found: " + id.toString());
        }
        return recipeOptional;
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String anyString) {
        return findById(anyString)
                .map(recipe -> {
                    RecipeCommand recipeCommand = mapper.recipeToRecipeCommand(recipe);
                    recipeCommand.getIngredients().forEach(ingredient -> {
                        ingredient.setRecipeId(recipeCommand.getId());
                    });
                    return recipeCommand;

                });
    }

    @Override
    public Mono<Void> deleteById(String idToDelete) {
        log.debug("deleting id " + idToDelete);
        recipeReactiveRepository.deleteById(idToDelete);
        return Mono.empty();
    }

    @Override
    @Transactional
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        Recipe recipe = mapper.recipeCommandToRecipe(command);
        return recipeReactiveRepository.save(recipe)
                .map(mapper::recipeToRecipeCommand)
                .doOnNext(savedRecipe -> log.debug("Saved recipe {}", savedRecipe.getId()));
    }


}
