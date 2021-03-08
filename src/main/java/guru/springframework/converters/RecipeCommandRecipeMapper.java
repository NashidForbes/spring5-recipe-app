package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeCommandRecipeMapper {
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);
    RecipeCommand recipeToRecipeCommand(Recipe recipe);
}
