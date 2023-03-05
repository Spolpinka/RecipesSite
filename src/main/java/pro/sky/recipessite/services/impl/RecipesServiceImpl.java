package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RecipesServiceImpl implements RecipesService {
    private static int id = 0;
    private static Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public int addRecipe(String name, int timeToSpend, List<Ingredient> ingredients, List<String> instructions) {
        recipes.put(++id, new Recipe(name, timeToSpend, ingredients, instructions));
        return id;
    }
    @Override
    public Recipe getRecipe(int id){
            return recipes.get(id);
    }
@Override
    public boolean isRecipesContainsId(int id) {
        return recipes.containsKey(id);
    }
}
