package pro.sky.recipessite.services.impl;

import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.services.RecipesService;

import java.util.HashMap;
import java.util.Map;

public class RecipesServiceImpl implements RecipesService {
    private static int id = 0;
    private static Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public int addRecipe(Recipe recipe) {
        recipes.put(++id, recipe);
        return id;
    }
    @Override
    public Recipe getRecipe(int id) {
        return recipes.get(id);
    }
}
