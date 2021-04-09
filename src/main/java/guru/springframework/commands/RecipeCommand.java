package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Data
public class RecipeCommand {
    private String id;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Integer cookTime;
    private Difficulty difficulty;
    @NotBlank
    @Size(min = 3, max = 8024)
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    private Byte[] image;
    private NotesCommand notes;
    @Min(1)
    @Max(999)
    private Integer prepTime;
    @Min(1)
    @Max(100)
    private Integer servings;
    private String source;
    @URL
    private String url;

}
