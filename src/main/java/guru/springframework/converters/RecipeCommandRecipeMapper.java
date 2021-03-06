package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeCommandRecipeMapper {

    RecipeCommandRecipeMapper INSTANCE =
            Mappers.getMapper(RecipeCommandRecipeMapper.class);

    //@Mapping(target = "version", ignore = true)
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);

    RecipeCommand recipeToRecipeCommand(Recipe recipe);
}
