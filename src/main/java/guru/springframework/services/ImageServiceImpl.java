package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.services.interfaces.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(
            RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
        log.info("Recieved a file for recipe id " + recipeId);
        try {
            Optional<Mono<Recipe>> recipeOptional =
                    Optional.of(
                            recipeReactiveRepository.findById(recipeId)
                                    .map(recipe -> {
                                        Byte[] byteObjects = new Byte[0];
                                        try {
                                            byteObjects =
                                                    new Byte[file.getBytes().length];
                                            int i = 0;
                                            // Store file in the database as a byte array
                                            for (byte b : file.getBytes()) {
                                                byteObjects[i++] = b;
                                            }
                                            recipe.setImage(byteObjects);
                                            return recipe;

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        }
                                    }));
            if (recipeOptional.isPresent()) {
                Mono<Recipe> recipeMono = recipeOptional.get();
                recipeReactiveRepository.save(recipeMono.block()).block();
                log.info("Recipe image saved under recipe id " + recipeId);
            } else {
                log.error("recipeOptional is not present");
            }

        } catch (Exception e) {
            log.error("Recipe id error with upload: " + e.getMessage());
            e.printStackTrace();
        }
        return Mono.empty();
    }
}
