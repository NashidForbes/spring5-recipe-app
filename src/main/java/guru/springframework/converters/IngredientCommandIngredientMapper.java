package guru.springframework.converters;

import guru.springframework.command.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*Using MapStruct to avoid manual type conversion from example:
https://github.com/springframeworkguru/spring5-recipe-app/tree/creating-command-obj/src/main/java
/guru/springframework/converters*/

@Mapper
public interface IngredientCommandIngredientMapper {

    IngredientCommandIngredientMapper INSTANCE = Mappers.getMapper(IngredientCommandIngredientMapper.class);

    Ingredient IngredientCommandToIngredient(IngredientCommand ingredientCommand);

    IngredientCommand IngredientToIngredientCommand(Ingredient ingredient);

}
