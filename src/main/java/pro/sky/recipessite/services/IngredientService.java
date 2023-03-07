package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.ArrayList;

@Service
public interface IngredientService {

    int addIngredient(Ingredient ingredient);

    Ingredient editIngredientById(int id, Ingredient ingredient);

    Ingredient deleteIngredientById(int id);

    Ingredient getIngredient(int id);

    ArrayList<Ingredient> getAllIngredients();

    boolean isIngresContainsId(int id);
}
