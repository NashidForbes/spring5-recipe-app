package guru.springframework.services;

import guru.springframework.command.IngredientCommand;
import guru.springframework.converters.IngredientCommandIngredientMapper;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class IngredientServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientCommandIngredientMapper ingredientCommandIngredientMapper;


    public IngredientServiceImplTest() {
        this.ingredientCommandIngredientMapper =
                ingredientCommandIngredientMapper.INSTANCE;
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientCommandIngredientMapper,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() throws Exception {
    }

    @Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //then
        // TODO Figure the java.lang.NullPointerException
        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdAndIngredientId(1L, 3L);



        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}