package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.interfaces.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(
            RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(String recipeId, MultipartFile file) {
        log.info("Recieved a file for recipe id " + recipeId);
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            // Store file in the database as a byte array
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();
                recipe.setImage(byteObjects);
                recipeRepository.save(recipe);
                log.info("Recipe image saved under recipe id " + recipeId);
            } else {
                log.error("recipeOptional is not present");
            }

        } catch (Exception e) {
            log.error("Recipe id error with upload: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
