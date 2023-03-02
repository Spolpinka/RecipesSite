package pro.sky.recipessite.services;

import pro.sky.recipessite.model.Ingredient;

public interface IngredientService {
    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
