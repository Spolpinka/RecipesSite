package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
public interface FilesService {
    boolean saveIngredients(String json);

    boolean saveRecipes(String json);

    String readIngredients();

    String readRecipes();

    File getRecipesFile();

    File getIngredientsFile();

    boolean cleanFile(Path path);
}
