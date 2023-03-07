package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class RecipesServiceImpl implements RecipesService {
    private static int id = 0;
    private static Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public int addRecipe(Recipe recipe) {
        recipes.put(++id, recipe);
        return id;
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        } else {
            return null;
        }
    }

    @Override
    public Recipe deleteRecipeById(int id) {
        if (recipes.containsKey(id)) {
            return recipes.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Recipe getRecipe(int id){
            return recipes.get(id);
    }

    @Override
    public ArrayList<Recipe> getAllRecipes() {
        return (ArrayList<Recipe>) (recipes.values());
    }

    @Override
    public boolean isRecipesContainsId(int id) {
        return recipes.containsKey(id);
    }
}
