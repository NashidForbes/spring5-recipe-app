package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.interfaces.RecipeService;
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
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // restful like url mapping
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        Mono<RecipeCommand> recipe = recipeService.findCommandById(id);
        model.addAttribute("recipe", recipe);
        return RECIPE_RECIPEFORM_URL;
    }


    // bind the form parameters via POST to the properties to the RecipeCommand object
    @PostMapping("/recipe")
    public Mono<String> saveOrUpdate(
            @Valid @ModelAttribute("recipe") Mono<RecipeCommand> command) {
/*        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return Mono.just(RECIPE_RECIPEFORM_URL);
        }*/
        return command
                .flatMap(recipeService::saveRecipeCommand)
                .map(recipe -> "redirect:/recipe/" + recipe.getId() + "/show")
                .doOnError(thr -> log.error("Error saving recipe"))
                .onErrorResume(WebExchangeBindException.class,
                        thr -> Mono.just(RECIPE_RECIPEFORM_URL));
    }


    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        recipeService.deleteById(id);
        return "redirect:/";
    }
    // need to declare @ResponseStatus as MVC conventional exception handling takes
    // precedence
/*    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception");
        log.error("exception: " + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }*/

}
