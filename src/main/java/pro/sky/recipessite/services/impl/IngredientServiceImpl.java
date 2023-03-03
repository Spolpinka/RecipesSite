package pro.sky.recipessite.services.impl;

import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.services.impl.exceptions.IngredientIsNullException;

import java.util.HashMap;
import java.util.Map;

public class IngredientServiceImpl implements IngredientService {
    private static int id = 0;
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public int addIngredient(Ingredient ingredient) throws IngredientIsNullException {
        if (ingredient != null) {
            ingredients.put(++id, ingredient);
            return id;
        } else {
            throw new IngredientIsNullException("Переданный ингридиент - null");
        }
    }

    @Override
    public Ingredient getIngredient(int id) throws IdIsIncorrectException {
        if (id >= 0 && ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new IdIsIncorrectException("Переданный id меньше ноля или не существует!");
        }
    }
}
