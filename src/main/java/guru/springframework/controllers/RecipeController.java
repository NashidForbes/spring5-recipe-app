package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    // autowire databinder for validation
    private WebDataBinder webDataBinder;

    // currently not in use, using different validation logic
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

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
            @ModelAttribute("recipe") Mono<RecipeCommand> command) {
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return Mono.just(RECIPE_RECIPEFORM_URL);
        }
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFound(Exception exception, Model model){
        log.error("Handling not found exception");
        log.error("exception: " + exception.getMessage());

        model.addAttribute("exception", exception);

        return "404error";
    }

}
