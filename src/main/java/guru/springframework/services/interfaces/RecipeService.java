package guru.springframework.services.interfaces;

import guru.springframework.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getRecipes();
}
