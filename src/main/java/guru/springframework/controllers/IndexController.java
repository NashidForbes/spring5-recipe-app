package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.interfaces.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(
            CategoryRepository categoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository,
            RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

/*        Optional<Category> categoryOptional = categoryRepository.findByDepartmentName(
                "American");
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByUnits("Teaspoon");*/

        model.addAttribute("recipes", recipeService.getRecipes());

/*        System.out.println("Cat id is: " + categoryOptional.get().getId());
        System.out.println("Uom id is " + unitOfMeasureOptional.get().getId());*/

        return "index";
    }
}
