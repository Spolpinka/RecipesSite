package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.services.impl.exceptions.IngredientIsNullException;

@Service
public interface IngredientService {

    int addIngredient(String name, int quantity, String volume);

    Ingredient getIngredient(int id) throws IdIsIncorrectException;
}
