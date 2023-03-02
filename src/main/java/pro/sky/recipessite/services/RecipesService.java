package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Recipe;
@Service
public interface RecipesService {
    int addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
