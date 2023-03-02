package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
@Service
public interface IngredientService {
    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
