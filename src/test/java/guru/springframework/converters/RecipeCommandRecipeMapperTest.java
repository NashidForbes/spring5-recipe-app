package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.command.NotesCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RecipeCommandRecipeMapperTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CATID_1 = 1L;
    public static final Long CATID_2 = 2L;
    public static final Long INGRED_ID_1 = 1L;
    public static final Long INGRED_ID_2 = 2L;
    public static final Long NOTES_ID = 9L;


    RecipeCommandRecipeMapper converter = RecipeCommandRecipeMapper.INSTANCE;

    RecipeCommand recipeCommand;
    Recipe recipe;

    void initRecipeCommand() {
        //given
        recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setName(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        recipeCommand.setNotes(notes);
        CategoryCommand category = new CategoryCommand();
        category.setId(CATID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CATID_2);
        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);
    }

    void initRecipe() {
        //given
        recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setName(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);
        Category category = new Category();
        category.setId(CATID_1);
        Category category2 = new Category();
        category2.setId(CATID_2);
        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

    }

    @BeforeEach
    void setUp() {
        initRecipeCommand();
        initRecipe();
    }

    @Test
    void recipeCommandToRecipe() {
        Recipe recipeNew = converter.recipeCommandToRecipe(recipeCommand);
        assertNotNull(recipeNew);
        assertEquals(RECIPE_ID, recipeNew.getId());
        assertEquals(COOK_TIME, recipeNew.getCookTime());
        assertEquals(PREP_TIME, recipeNew.getPrepTime());
        assertEquals(DESCRIPTION, recipeNew.getName());
        assertEquals(DIFFICULTY, recipeNew.getDifficulty());
        assertEquals(DIRECTIONS, recipeNew.getDirections());
        assertEquals(SERVINGS, recipeNew.getServings());
        assertEquals(SOURCE, recipeNew.getSource());
        assertEquals(URL, recipeNew.getUrl());
        assertEquals(NOTES_ID, recipeNew.getNotes().getId());
        assertEquals(2, recipeNew.getCategories().size());
        assertEquals(2, recipeNew.getIngredients().size());
    }

    @Test
    void recipeToRecipeCommand() {
        RecipeCommand recipeNew = converter.recipeToRecipeCommand(recipe);
        assertNotNull(recipeNew);
        assertEquals(RECIPE_ID, recipeNew.getId());
        assertEquals(COOK_TIME, recipeNew.getCookTime());
        assertEquals(PREP_TIME, recipeNew.getPrepTime());
        assertEquals(DESCRIPTION, recipeNew.getName());
        assertEquals(DIFFICULTY, recipeNew.getDifficulty());
        assertEquals(DIRECTIONS, recipeNew.getDirections());
        assertEquals(SERVINGS, recipeNew.getServings());
        assertEquals(SOURCE, recipeNew.getSource());
        assertEquals(URL, recipeNew.getUrl());
        assertEquals(NOTES_ID, recipeNew.getNotes().getId());
        assertEquals(2, recipeNew.getCategories().size());
        assertEquals(2, recipeNew.getIngredients().size());
    }
}