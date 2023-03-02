package pro.sky.recipessite.services;

import pro.sky.recipessite.model.Recipe;

public interface RecipesService {
    int addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
