package guru.springframework.commands;

import guru.springframework.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class RecipeCommand {
    private Long id;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Integer cookTime;
    private Difficulty difficulty;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private String name;
    private NotesCommand notes;
    private Integer prepTime;
    private Integer servings;
    private String source;
    private String url;

}
