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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class RecipeCommand {
    private String id;
    // change from Set to List, since Spring MVC properly binds to List
    private List<CategoryCommand> categories = new ArrayList<>();
    private Integer cookTime;
    private Difficulty difficulty;
    @NotBlank
    @Size(min = 3, max = 8024)
    private String directions;
    // change from Set to List, since Spring MVC properly binds to List
    private List<IngredientCommand> ingredients = new ArrayList<>();
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
