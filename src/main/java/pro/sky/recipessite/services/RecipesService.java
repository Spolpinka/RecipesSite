package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.services.impl.exceptions.RecipeIsNullException;

@Service
public interface RecipesService {
    int addRecipe(Recipe recipe) throws RecipeIsNullException;

    Recipe getRecipe(int id) throws IdIsIncorrectException;
}
