package pro.sky.recipessite.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipessite.services.FilesService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.files}")
    private String filesPath;
    @Value("${name.of.ingredients.file}")
    private String ingredientFileName;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Override
    public boolean saveIngredients(String json) {
        Path path = Path.of(filesPath, ingredientFileName);
        try {
            cleanFile(path);
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveRecipes(String json) {
        Path path = Path.of(filesPath, recipesFileName);
        try {
            cleanFile(path);
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readIngredients() {
        try {
            return Files.readString(Path.of(filesPath, ingredientFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String readRecipes() {
        try {
            return Files.readString(Path.of(filesPath, recipesFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean cleanFile(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
