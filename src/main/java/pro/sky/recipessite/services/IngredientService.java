package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;

import java.util.ArrayList;
import java.util.Collection;

@Service
public interface IngredientService {

    int addIngredient(Ingredient ingredient);

    String addIngredientArray(Ingredient[] newIngredients);

    Ingredient editIngredientById(int id, Ingredient ingredient);

    Ingredient deleteIngredientById(int id);

    Ingredient getIngredient(int id);

    Collection<Ingredient> getAllIngredients();

    boolean isIngresContainsId(int id);

}
