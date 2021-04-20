package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.interfaces.IngredientService;
import guru.springframework.services.interfaces.RecipeService;
import guru.springframework.services.interfaces.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(
            RecipeService recipeService,
            IngredientService ingredientService,
            UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for id " + id);
        // use commands object to avoid lazy load errors in Thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String ShowIngredient(@PathVariable String recipeId, @PathVariable String id,
                                 Model model) {
        log.debug(
                "Getting recipe id " + recipeId + " and getting ingredient with id " +
                        id);
        // use commands object to avoid lazy load errors in Thymeleaf
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId,
                        id));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        // make sure we have a good id value
        Mono<RecipeCommand> recipeCommand =
                recipeService.findCommandById(recipeId);
        if (recipeCommand == null) {
            log.error("Error recipe command is null");
            throw new IllegalStateException("recipe command is null");
        }
        // need to return back parent recipe id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);
        // init unit of measure
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        // nothing happens with the reactive service unitOfMeasureService until the
        // .block() is called
        model.addAttribute("uomList",
                unitOfMeasureService.listAllUoms().collectList().block());
        return "recipe/ingredient/ingredientform";

    }


    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId,
                        id));
        // nothing happens with the reactive service unitOfMeasureService until the
        // .block() is called
        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping(value = "/recipe/{recipeId}/ingredient")
    public Mono<String> saveOrUpdate(@Valid @ModelAttribute("ingredient") Mono<IngredientCommand> ingredientCommand,
                                     @PathVariable String recipeId, Model model) {
        return ingredientCommand.doOnNext(cmd -> cmd.setRecipeId(recipeId))
                .flatMap(ingredientService::saveIngredientCommand)
                .doOnNext(sc -> log.debug("saved recipe id:{} and ingredient id:{}", sc.getRecipeId(),
                        sc.getId()))
                .map(sc -> "redirect:/recipe/" + sc.getRecipeId() + "/ingredient/" + sc.getId() + "/show")
                .onErrorResume(WebExchangeBindException.class, thr -> {
                    ((IngredientCommand)model.getAttribute("ingredient")).setRecipeId(recipeId);
                    return Mono.just("recipe/ingredient/ingredientform");
                })
                .doOnError(thr -> log.error("Error saving ingredient for recipe {}", recipeId));
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {
        ingredientService.deleteIngredientById(recipeId, id);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
