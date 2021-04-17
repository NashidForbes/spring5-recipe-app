package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandIngredientMapper;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import guru.springframework.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientCommandIngredientMapper ingredientCommandIngredientMapper;
    private final guru.springframework.repositories.reactive.RecipeReactiveRepository
            recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final RecipeRepository recipeRepository;
    

    public IngredientServiceImpl(
            IngredientCommandIngredientMapper ingredientCommandIngredientMapper,
            guru.springframework.repositories.reactive.RecipeReactiveRepository recipeReactiveRepository,
            UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
            RecipeRepository recipeRepository) {
        this.ingredientCommandIngredientMapper = ingredientCommandIngredientMapper;
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId,
                                                           String ingredientId) {
        Mono<Recipe> recipeOptional = recipeReactiveRepository.findById(recipeId);

        return recipeReactiveRepository.findById(recipeId)
                .map(recipe -> recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(ingredient -> {
                    IngredientCommand ingredientCommand =
                            ingredientCommandIngredientMapper.IngredientToIngredientCommand(ingredient.get());
                    ingredientCommand.setRecipeId(recipeId);
                    return ingredientCommand;
                });


/*        if (recipeOptional.isPresent()) {
            log.debug("Error recipe id not found " + recipeId);
            throw new RuntimeException("Error recipe id not found " + recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional =
                recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .filter(ingredient -> ingredient != null)
                        .map(ingredientCommandIngredientMapper::IngredientToIngredientCommand)
                        .findFirst();
        if (!ingredientCommandOptional.isPresent()) {
            log.debug("Error ingredient id not found " + ingredientId);
            throw new RuntimeException("Error ingredient id not found " + ingredientId);
        }*/

/*        IngredientCommand ingredientCommandFound = ingredientCommandOptional.get();
        // converter doesn't have access to recipe id due to how Mongo DB works.
        // Which is different from Spring JPA
        ingredientCommandFound.setRecipeId(recipeId);

        return ingredientCommandFound;*/
    }

    @Override
    @Transactional
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null) {
            log.error("IngredientCommand object input is null");
            throw new IllegalArgumentException("Error ingredient command is null");
        }
        Optional<Recipe> recipeOptional = recipeRepository.findById(
                ingredientCommand.getRecipeId());
        if (!recipeOptional.isPresent()) {
            // TODO toss error if not found
            log.error("Recipe not fourd for id " + ingredientCommand.getRecipeId());
            return Mono.just(new IngredientCommand());
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId()
                            .equals(ingredientCommand.getId()))
                    .findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureReactiveRepository
                        .findById(ingredientCommand.getUnitOfMeasure().getId()).block());
/*                        .orElseThrow(() -> new RuntimeException(
                                "Unit of Measure Not Found")));  // TODO better error*/
                // throws
            } else {
                // add new ingredient
                recipe.addIngredient(ingredientCommandIngredientMapper
                        .IngredientCommandToIngredient(ingredientCommand));
            }
            Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();
            Optional<Ingredient> savedIngredientOptional =
                    savedRecipe.getIngredients().stream()
                            .filter(recipeIngredients -> recipeIngredients.getId()
                                    .equals(ingredientCommand.getId()))
                            .findFirst();
            // check the savedIngredient by description
            // saving ingredient for the first time it won't have an id available yet
            // for  query?
            if (!savedIngredientOptional.isPresent()) {
                // not totally safe, but the best for now
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription()
                                .equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount()
                                .equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure()
                                .getId()
                                .equals(ingredientCommand.getUnitOfMeasure().getId()))
                        .findFirst();
            }
            // to do check for fail

            // converter doesn't have access to recipe id due to how Mongo DB works.
            // Which is different from Spring JPA
            IngredientCommand ingredientCommandSaved = ingredientCommandIngredientMapper
                    .IngredientToIngredientCommand(savedIngredientOptional.get());
            ingredientCommandSaved.setRecipeId(savedRecipe.getId());

            return Mono.just(ingredientCommandSaved);

        }

    }

    @Override
    @Transactional
    public Mono<Void> deleteIngredientById(String recipeId, String ingredientId) {
        if (recipeId == null) {
            log.error("recipeId input is null");
            throw new IllegalArgumentException("Error recipeId is null");
        }

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()) {
            log.error("parent recipeOptional object is not present");
            throw new IllegalArgumentException("parent recipeOptional object is not " +
                    "valid or present");
        } else {
            Recipe recipe = recipeOptional.get();


            log.info("delete ingredient object with id " + ingredientId);
            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if(!ingredientOptional.isPresent()){
                log.error("ingredientOptional object is not present");
                throw new IllegalArgumentException("ingredientOptional object is not " +
                        "valid or present");
            }

            // Note: all manual tasks to delete references and objects between recipe and
            // ingredients, Not quite what I expected with JPA.
            Ingredient ingredientFound = ingredientOptional.get();
            //ingredientFound.setRecipe(null);
            recipe.getIngredients().remove(ingredientFound);
            recipeRepository.save(recipe);

            //ingredientRepository.deleteById(ingredientFound.getId());
        }
      return Mono.empty();
    }

}
