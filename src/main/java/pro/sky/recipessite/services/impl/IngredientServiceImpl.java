package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static int id = 0;
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();

    @Override
    public int addIngredient(String name, int quantity, String volume){
            ingredients.put(++id, new Ingredient(name, quantity, volume));
            return id;

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
