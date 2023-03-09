package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public interface RecipesService {
    int addRecipe(Recipe recipe);

    Recipe editRecipeById(int id, Recipe recipe);

    Recipe deleteRecipeById(int id);

    Recipe getRecipe(int id);

    ArrayList<Recipe> getAllRecipes();

    boolean isRecipesContainsId(int id);

    ArrayList<Recipe> getRecipesByIngredientsId(int id);

    List<Recipe> getRecipesBySeveralIngredients(Ingredient[] ingredients);

    LinkedHashMap<Integer, List<Recipe>> getAllRecipesBy10pcs();
}
