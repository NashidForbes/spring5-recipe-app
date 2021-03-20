package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandIngredientMapper;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientCommandIngredientMapper ingredientCommandIngredientMapper;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(
            IngredientCommandIngredientMapper ingredientCommandIngredientMapper,
            RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientCommandIngredientMapper = ingredientCommandIngredientMapper;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,
                                                           Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
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
        }
        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null) {
            log.error("IngredientCommand object input is null");
            throw new IllegalArgumentException("Error ingredient command is null");
        }
        Optional<Recipe> recipeOptional = recipeRepository.findById(
                ingredientCommand.getRecipeId());
        if (!recipeOptional.isPresent()) {
            // TODO toss error if not found
            log.error("Recipe not fourd for id " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
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
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(ingredientCommand.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Unit of Measure Not Found")));  // TODO better error
                // throws
            } else {
                // add new ingredient
                recipe.addIngredient(ingredientCommandIngredientMapper
                        .IngredientCommandToIngredient(ingredientCommand));
            }
            Recipe savedRecipe = recipeRepository.save(recipe);
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
            return ingredientCommandIngredientMapper
                    .IngredientToIngredientCommand(savedIngredientOptional.get());

        }

    }

}
