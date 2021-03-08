package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper //TODO need to remove this and put on implementation
@Component
public interface RecipeCommandRecipeMapper {

    RecipeCommandRecipeMapper INSTANCE = Mappers.getMapper(RecipeCommandRecipeMapper.class);

    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);
    RecipeCommand recipeToRecipeCommand(Recipe recipe);
}
