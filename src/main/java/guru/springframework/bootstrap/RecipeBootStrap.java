package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.repositories.reactive.CategoryReactiveRepository;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;
    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    public RecipeBootStrap(
            CategoryRepository categoryRepository,
            RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(categoryRepository.count() == 0L){
            log.debug("Loading Categories");
            loadCategories();
        }
        log.error("onApplicationEvent:############");
        log.error("Category count: " + categoryReactiveRepository.count().block().toString());

        if(unitOfMeasureRepository.count() == 0L){
            log.debug("Loading UOMs");
            loadUom();
        }

        log.error("onApplicationEvent:############");
        log.error("Unit of Measure count: " + unitOfMeasureReactiveRepository.count().block().toString());

        log.debug("Saving all loadData for bootstrap");
        recipeRepository.saveAll(loadData());
        //loadData();

        log.error("onApplicationEvent:############");
        log.error("Recipe count: " + recipeReactiveRepository.count().block().toString());
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setDepartmentName("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDepartmentName("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDepartmentName("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDepartmentName("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setUnits("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setUnits("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setUnits("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setUnits("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setUnits("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setUnits("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setUnits("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setUnits("Dash");
        unitOfMeasureRepository.save(uom8);

        UnitOfMeasure uom9 = new UnitOfMeasure();
        uom9.setUnits("Not Applicable");
        unitOfMeasureRepository.save(uom9);

    }

    private List<Recipe> loadData() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        Optional<UnitOfMeasure> teaSpoonOptional = unitOfMeasureRepository.findByUnits(
                "Teaspoon");

/*      example of using defensive programming to catch any query exceptions from
        the repository should do it for all unit of measure optionals */
        if (!teaSpoonOptional.isPresent()) {
            throw new Exception("Error Teaspoon not found");
        }
        UnitOfMeasure teaSpoon = teaSpoonOptional.get();
        Optional<UnitOfMeasure> tableSpoonOptional = unitOfMeasureRepository.findByUnits(
                "Tablespoon");
        if (!tableSpoonOptional.isPresent()) {
            throw new Exception("Error tableSpoon not found");
        }
        UnitOfMeasure tableSpoon = tableSpoonOptional.get();
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByUnits(
                "Cup");
        if (!cupOptional.isPresent()) {
            throw new Exception("Error cup not found");
        }
        UnitOfMeasure cup = cupOptional.get();
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByUnits(
                "Ounce");
        if (!ounceOptional.isPresent()) {
            throw new Exception("Error ounce not found");
        }
        UnitOfMeasure ounce = ounceOptional.get();
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByUnits(
                "Pinch");
        if (!pinchOptional.isPresent()) {
            throw new Exception("Error pinch not found");
        }
        UnitOfMeasure pinch = pinchOptional.get();
        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByUnits(
                "Dash");
        if (!dashOptional.isPresent()) {
            throw new Exception("Error dash not found");
        }
        UnitOfMeasure dash = dashOptional.get();
        Optional<UnitOfMeasure> notApplicableOptional =
                unitOfMeasureRepository.findByUnits(
                        "Not Applicable");
        if (!notApplicableOptional.isPresent()) {
            throw new Exception("Error Not Applicable not found");
        }
        UnitOfMeasure notApplicable = dashOptional.get();
        Optional<Category> americanaOptional = categoryRepository.findByDepartmentName(
                "American");
        if (!americanaOptional.isPresent()) {
            throw new Exception("Error americanaOptional Not not found");
        }
        Category americana = americanaOptional.get();
        Optional<Category> italianOptional = categoryRepository.findByDepartmentName(
                "Italian");
        if (!italianOptional.isPresent()) {
            throw new Exception("Error italian Not not found");
        }
        Category italian = italianOptional.get();
        Optional<Category> mexicanOptional = categoryRepository.findByDepartmentName(
                "Mexican");
        if (!mexicanOptional.isPresent()) {
            throw new Exception("Error mexican Not not found");
        }
        Category mexican = mexicanOptional.get();
        Optional<Category> fastfoodOptional = categoryRepository.findByDepartmentName(
                "Fast Food");
        if (!fastfoodOptional.isPresent()) {
            throw new Exception("Error Fast Food Not not found");
        }
        Category fastfood = fastfoodOptional.get();

        Recipe recipe1 = new Recipe();
        recipe1.setName("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setServings(4);
        recipe1.setSource("www.simplyrecipes.com");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe1.setCookTime(10);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setDirections(" The Best Way to Cut an Avocado\n" +
                "\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp " +
                "chef's knife and twist apart the sides. One side will have the pit. To" +
                " remove it, you can do one of two things:\n" +
                "\n" +
                "    Method #1: Gently tap the pit with your chef's knife so the knife " +
                "gets wedged into the pit. Twist your knife slightly to dislodge the " +
                "pit and lift to remove. If you use this method, first protect your " +
                "hand with a thick kitchen towel before proceeding.\n" +
                "    Method #2: Cut the side with the pit in half again, exposing more " +
                "of the pit. Use your fingers or a spoon to remove the pit\n" +
                "\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside" +
                " the peel and use a spoon to scoop them out.");
        //recipe1.setImage();
        Notes notes1 = new Notes();
        //notes1.setRecipe(recipe1);
        notes1.setNotes(
                "All you really need to make guacamole is ripe avocados and salt." +
                        " After that, a little lime or lemon juice—a splash of " +
                        "acidity—will help to " +
                        "balance the richness of the avocado. Then if you want, " +
                        "add chopped cilantro, chiles, onion, and/or tomato . ");
        recipe1.setNotes(notes1);


/*       Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("ripe avocados");
        ingredient1.setAmount(new BigDecimal("2.00"));
        ingredient1.setRecipe(recipe1);
        ingredient1.setUnitOfMeasure(teaSpoon);*/
        recipe1.getIngredients().add(new Ingredient("ripe avocados",
                new BigDecimal("2.00"), recipe1, teaSpoon));
        recipe1.getIngredients().add(new Ingredient("salt",
                new BigDecimal("0.50"), recipe1, teaSpoon));
        recipe1.getIngredients().add(new Ingredient("fresh lime juice",
                new BigDecimal(".25"), recipe1, tableSpoon));
        recipe1.getIngredients().add(new Ingredient("minced red onion",
                new BigDecimal("2.00"), recipe1, tableSpoon));
        recipe1.getIngredients()
                .add(new Ingredient("serrano chiles, stems and seeds removed, minced",
                        new BigDecimal("2"), recipe1, notApplicable));
        recipe1.getIngredients().add(new Ingredient("cilantro",
                new BigDecimal("2.00"), recipe1, tableSpoon));
        recipe1.getIngredients()
                .add(new Ingredient("A dash of freshly grated black pepper",
                        new BigDecimal("0.00"), recipe1, notApplicable));
        recipe1.getIngredients()
                .add(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                        new BigDecimal("0.50"), recipe1, teaSpoon));
        recipe1.getIngredients().add(new Ingredient("Red radishes or jicama, to garnish ",
                new BigDecimal("0.00"), recipe1, notApplicable));
        recipe1.getIngredients().add(new Ingredient("Tortilla chips, to serve ",
                new BigDecimal("0.00"), recipe1, notApplicable));
        //americana.getRecipes().add(recipe1);
        recipe1.getCategories().add(americana);
        recipe1.getCategories().add(italian);
        Recipe recipe2 = new Recipe();
        recipe2.setName("Spicy Grilled Chicken Tacos");
        recipe2.setPrepTime(20);
        recipe2.setServings(6);
        recipe2.setSource("www.simplyrecipes.com");
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe2.setCookTime(15);
        recipe2.setDifficulty(Difficulty.MODERATE);
        recipe2.setDirections(" First, I marinate the chicken briefly in a spicy paste " +
                "of ancho chile powder, oregano, cumin, and sweet orange juice while " +
                "the grill is heating. You can also use this time to prepare the taco " +
                "toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now " +
                "you are ready to assemble the tacos and dig in. The whole meal comes " +
                "together in about 30 minutes! ");
        //recipe2.setImage();
        Notes notes2 = new Notes();
        //notes2.setRecipe(recipe2);
        notes2.setNotes(" The ancho chiles I use in the marinade are named for their " +
                "wide shape. They are large, have a deep reddish brown color when " +
                "dried, and are mild in flavor with just a hint of heat. You can find " +
                "ancho chile powder at any markets that sell Mexican ingredients, or " +
                "online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the" +
                " center of the table: avocados, radishes, tomatoes, red onions, wedges" +
                " of lime, and a sour cream sauce. I add arugula, as well – this green " +
                "isn't traditional for tacos, but we always seem to have some in the " +
                "fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own " +
                "tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger " +
                "party. A taco and a cold beer on a warm day? Now that's living! ");
        recipe2.setNotes(notes2);
        recipe2.getIngredients().add(new Ingredient("ancho chili powder",
                new BigDecimal("2.00"), recipe2, tableSpoon));
        recipe2.getIngredients().add(new Ingredient("dried oregano",
                new BigDecimal("1.00"), recipe2, teaSpoon));
        recipe2.getIngredients().add(new Ingredient("dried cumin",
                new BigDecimal("1.00"), recipe2, tableSpoon));
        recipe2.getIngredients().add(new Ingredient("sugar",
                new BigDecimal("1.00"), recipe2, tableSpoon));
        recipe2.getIngredients()
                .add(new Ingredient("salt",
                        new BigDecimal("0.50"), recipe2, teaSpoon));
        recipe2.getIngredients().add(new Ingredient("clove garlic, finely chopped",
                new BigDecimal("1.00"), recipe2, tableSpoon));
        recipe2.getIngredients()
                .add(new Ingredient("finely grated orange zest ",
                        new BigDecimal("1.00"), recipe2, notApplicable));
        recipe2.getIngredients()
                .add(new Ingredient("fresh-squeezed orange juice",
                        new BigDecimal("3.00"), recipe2, teaSpoon));
        recipe2.getIngredients().add(new Ingredient("olive oil",
                new BigDecimal("2.00"), recipe2, notApplicable));
        recipe2.getIngredients().add(new Ingredient("skinless, boneless chicken thighs (1 1/4 " +
                "pounds) ",
                new BigDecimal("6.00"), recipe2, notApplicable));
        Optional<Category> americanaOptional2 = categoryRepository.findByDepartmentName(
                "Italian");
        if (!americanaOptional2.isPresent()) {
            throw new Exception("Error italian Not not found");
        }
        recipe2.getCategories().add(italian);
        recipe2.getCategories().add(fastfood);
        recipe2.getCategories().add(mexican);
        // recipeRepository.save(recipe1); doesn't work creates duplicate objects type in same
        // context?
        // recipeRepository.save(recipe2);
        recipes.add(recipe1);
        recipes.add(recipe2);
        return recipes;
    }
}
