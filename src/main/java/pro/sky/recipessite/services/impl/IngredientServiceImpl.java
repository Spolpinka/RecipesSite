package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int id = 0;
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public int addIngredient(String name, int quantity, String volume) {
        ingredients.put(++id, new Ingredient(name, quantity, volume));
        return id;

    }

    @Override
    public Ingredient getIngredient(int id) {
            return ingredients.get(id);
    }
    @Override
    public boolean isIngresContainsId(int id) {
        return ingredients.containsKey(id);
    }
}
