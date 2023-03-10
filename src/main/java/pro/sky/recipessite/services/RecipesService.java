package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public interface RecipesService {
    String addRecipe(Recipe[] recipes);

    Recipe editRecipeById(int id, Recipe recipe);

    Recipe deleteRecipeById(int id);

    Recipe getRecipe(int id);

    Collection<Recipe> getAllRecipes();

    boolean isRecipesContainsId(int id);

    Collection<Recipe> getRecipesByIngredientsId(int id);

    List<Recipe> getRecipesBySeveralIngredients(Ingredient[] ingredients);

    LinkedHashMap<Integer, List<Recipe>> getAllRecipesBy10pcs();
}
