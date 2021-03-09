package guru.springframework.services.interfaces;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();

    Recipe findById(Long aLong);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(long anyLong);
}
