package pro.sky.recipessite.services.impl;

import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.services.RecipesService;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.services.impl.exceptions.RecipeIsNullException;

import java.util.HashMap;
import java.util.Map;

public class RecipesServiceImpl implements RecipesService {
    private static int id = 0;
    private static Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public int addRecipe(Recipe recipe) throws RecipeIsNullException {
        if (recipe != null) {
            recipes.put(++id, recipe);
            return id;
        } else {
            throw new RecipeIsNullException("Переданный рецепт - null!");
        }
    }
    @Override
    public Recipe getRecipe(int id) throws IdIsIncorrectException {
        if (id >= 0 && recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new IdIsIncorrectException("Переданный id меньше ноля или не существует!");
        }
    }
}
