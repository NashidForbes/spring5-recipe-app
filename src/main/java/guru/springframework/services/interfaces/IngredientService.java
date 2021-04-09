package guru.springframework.services.interfaces;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredientById(String recipeId, String ingredientId);
}
