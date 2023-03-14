package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;

@Service
public interface FilesService {
    boolean saveIngredients(String json);

    boolean saveRecipes(String json);

    String readIngredients();

    String readRecipes();
}
