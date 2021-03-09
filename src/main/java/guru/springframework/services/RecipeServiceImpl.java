package guru.springframework.services;

import guru.springframework.command.RecipeCommand;
import guru.springframework.converters.RecipeCommandRecipeMapper;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandRecipeMapper mapper;

    public RecipeServiceImpl(
            RecipeRepository recipeRepository,
            RecipeCommandRecipeMapper mapper) {
        this.recipeRepository = recipeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Recipe> getRecipes() {
        log.debug("Retrieving all recipes");
        List<Recipe> recipeSet = new ArrayList<>();
        recipeRepository.findAll().listIterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(long anyLong) {
        return mapper.recipeToRecipeCommand(findById(anyLong));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        // Not tied to Hibernate context / DB context so using the prefix detached
        Recipe detachedRecipe = mapper.recipeCommandToRecipe(command);

        // if it's new will create a new entity
        // if it's existing will do a merge operation
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe id" + savedRecipe.getId());

        // return the saved recipe object to the web layer
        return mapper.recipeToRecipeCommand(savedRecipe);
    }


}
