package pro.sky.recipessite.services.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipessite.services.FilesService;

import java.io.File;
import java.io.FileOutputStream;
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

    private final Path recipePath = Path.of(filesPath, recipesFileName);
    private final Path ingredientPath = Path.of(filesPath, ingredientFileName);

    @Override
    public boolean saveIngredients(String json) {
        try {
            cleanIngredientFile();
            Files.writeString(ingredientPath, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveRecipes(String json) {
        try {
            cleanRecipeFile();
            Files.writeString(recipePath, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readIngredients() {
        try {
            return Files.readString(ingredientPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String readRecipes() {
        try {
            return Files.readString(recipePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File getRecipesFile() {
        return new File(filesPath + "/" + recipesFileName);
    }

    @Override
    public File getIngredientsFile() {
        return new File(filesPath + "/" + ingredientFileName);
    }


    @Override
    public boolean uploadIngredientFile(MultipartFile file) {
        if (cleanIngredientFile()) {
            System.out.println("Очистка прошла успешно");
        } else {
            System.out.println("Что-то с очисткой не задалось...");
        }
        File dataFile = getIngredientsFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean uploadRecipeFile(MultipartFile file) {
        if (cleanRecipeFile()) {
            System.out.println("Очистка прошла успешно");
        } else {
            System.out.println("Что-то с очисткой не задалось...");
        }
        File dataFile = getRecipesFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * очищаем файл базы данных рецептов
     * @return boolean очищено или нет
     */
    @Override
    public boolean cleanRecipeFile() {
        try {
            Files.deleteIfExists(recipePath);
            Files.createFile(recipePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * очищаем файл базы данных ингридиентов
     * @return boolean прошла очистка или нет
     */
    @Override
    public boolean cleanIngredientFile() {
        try {
            Files.deleteIfExists(ingredientPath);
            Files.createFile(ingredientPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * создаем временный файл для выгрузки нужных данных
     * @param suffix файла
     * @return путь к временному файлу, чтобы открывать входящий в файл поток
     */
    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(filesPath), "tempFile", suffix);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


}
