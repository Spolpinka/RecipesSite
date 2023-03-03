package pro.sky.recipessite.services;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;

import java.util.List;

@Service
public interface RecipesService {
    int addRecipe(String name, int timeToSpend, List<Ingredient> ingredients, List<String> instructions);

    Recipe getRecipe(int id) throws IdIsIncorrectException;
}
