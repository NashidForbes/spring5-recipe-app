package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.domain.Category;
import org.mapstruct.Mapper;

/*Using MapStruct to avoid manual type conversion from example:
https://github.com/springframeworkguru/spring5-recipe-app/tree/creating-command-obj/src/main/java
/guru/springframework/converters*/

@Mapper
public interface CategoryCommandCategoryMapper {
    Category CategoryCommandToCategory(CategoryCommand categoryCommand);

    CategoryCommand CategoryToCategoryCommand(Category category);
}
