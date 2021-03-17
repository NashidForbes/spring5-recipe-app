package guru.springframework.services;

import guru.springframework.command.IngredientCommand;
import guru.springframework.converters.IngredientCommandIngredientMapper;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientCommandIngredientMapper ingredientCommandIngredientMapper;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(
            IngredientCommandIngredientMapper ingredientCommandIngredientMapper,
            RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientCommandIngredientMapper = ingredientCommandIngredientMapper;
        this.recipeRepository = recipeRepository;
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
                        .filter(ingredient -> ingredient!= null)
                        .map(ingredientCommandIngredientMapper::IngredientToIngredientCommand).findFirst();
        if (!ingredientCommandOptional.isPresent()) {
            log.debug("Error ingredient id not found " + ingredientId);
            throw new RuntimeException("Error ingredient id not found " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
