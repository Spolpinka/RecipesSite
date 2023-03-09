package pro.sky.recipessite.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;

import java.util.*;

@Service
public class RecipesServiceImpl implements RecipesService {
    private static int id = 0;
    IngredientServiceImpl ingredientService = new IngredientServiceImpl();

    private static Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public String addRecipe(Recipe[] newRecipes) {
        String ids = "";
        for (Recipe recipe: newRecipes) {
            recipes.put(++id, recipe);
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredientService.addIngredient(ingredient);
            }
            ids += id + ", ";
        }
        return ids;
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        } else {
            return null;
        }
    }

    @Override
    public Recipe deleteRecipeById(int id) {
        if (recipes.containsKey(id)) {
            return recipes.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Recipe getRecipe(int id){
            return recipes.get(id);
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return (recipes.values());
    }

    @Override
    public boolean isRecipesContainsId(int id) {
        return recipes.containsKey(id);
    }

    @Override
    public Collection<Recipe> getRecipesByIngredientsId(int id) {
        Collection<Recipe> recipesById = new ArrayList<>();
        for (Recipe recipe : recipes.values()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getName().equals(ingredientService.getIngredient(id).getName())) {
                    recipesById.add(recipe);
                }
            }
        }
        return recipesById;
    }

    @Override
    public List<Recipe> getRecipesBySeveralIngredients(Ingredient[] ingredients) {
        List<Recipe> result = new ArrayList<>();
        List<Ingredient> ingrsForSeaching = Arrays.stream(ingredients).toList();
        for (Recipe recipe : recipes.values()) {
            if (recipe.getIngredients().containsAll(ingrsForSeaching)){
                result.add(recipe);
            }
        }
        return result;
    }

    @Override
    public LinkedHashMap<Integer, List<Recipe>> getAllRecipesBy10pcs() {
        int countOfPcs = 10;//количество рецептов на странице
        int countOfPages = 1;//счетчик для страниц
        if (recipes != null || recipes.isEmpty()) {
            LinkedHashMap<Integer, List<Recipe>> result = new LinkedHashMap<>();
            List<Recipe> recipesValues = recipes.values().stream().toList();
            List<Recipe> tempList = new ArrayList<>(countOfPcs);
            for (int i = 0; i < recipesValues.size(); i++) {
                tempList.add(recipesValues.get(i));
                if (tempList.size() == countOfPcs){
                    result.put(countOfPages, tempList);
                    tempList = new ArrayList<>(countOfPcs);
                    countOfPages++;
                }
            }
            return result;
        } else {
            return null;
        }
    }
}
