package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int id = 0;
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredients.put(++id, ingredient);
        return id;
    }

    @Override
    public String addIngredientArray(Ingredient[] newIngredients) {
        String ids = "";
        for (Ingredient ingredient : newIngredients) {
            ingredients.put(++id, ingredient);
            ids += id + ", ";
        }
        return ids;
    }

    @Override
    public Ingredient editIngredientById(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            return ingredients.put(id, ingredient);
        } else {
            return null;
        }
    }

    @Override
    public Ingredient deleteIngredientById(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredients.get(id);
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    @Override
    public boolean isIngresContainsId(int id) {
        return ingredients.containsKey(id);
    }
}
