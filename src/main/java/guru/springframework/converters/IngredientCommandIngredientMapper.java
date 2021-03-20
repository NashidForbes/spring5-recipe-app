package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*Using MapStruct to avoid manual type conversion from example:
https://github.com/springframeworkguru/spring5-recipe-app/tree/creating-command-obj/src/main/java
/guru/springframework/converters*/

@Mapper
public interface IngredientCommandIngredientMapper {

    IngredientCommandIngredientMapper INSTANCE = Mappers.getMapper(IngredientCommandIngredientMapper.class);

    @Mapping(source = "recipeId", target="recipe.id")
    Ingredient IngredientCommandToIngredient(IngredientCommand ingredientCommand);

    @Mapping(source = "ingredient.recipe.id", target="recipeId")
    IngredientCommand IngredientToIngredientCommand(Ingredient ingredient);

}
