package guru.springframework.services;

import guru.springframework.converters.RecipeCommandRecipeMapper;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    RecipeCommandRecipeMapper mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, mapper);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        List<Recipe> recipesData = new ArrayList<>();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipesData);
        Set<Recipe> recipes = new HashSet<Recipe>(recipeService.getRecipes());
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        String idToDelete = "2";
        recipeService.deleteById(idToDelete);

        // no 'when', since method has void return type

        verify(recipeRepository, times(1)).deleteById(anyString());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipe = recipeService.findById("9");

        // should go boom (throw exception)
    }

    @Test(expected = NumberFormatException.class)
    public void getRecipeById_throws_NumberFormatException_when_id_is_not_a_number() throws Exception{
        recipeService.findById("asd");
    }
}