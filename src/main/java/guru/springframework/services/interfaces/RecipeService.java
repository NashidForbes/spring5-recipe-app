package guru.springframework.services.interfaces;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();

    Recipe findById(String aString);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(String anyString);

    void deleteById(String idToDelete);
}
