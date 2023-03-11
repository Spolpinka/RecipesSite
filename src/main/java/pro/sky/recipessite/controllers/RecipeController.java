package pro.sky.recipessite.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "работа с рецептами: добавление, редактирование, получение, удаление")
public class RecipeController {
    private final RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    /**
     * 6. добавление рецептов
     * @param recipes из тела запроса
     * @return строку с идентификаторами добавленных рецептов
     */
    @PostMapping("/addRecipe")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe... recipes) {
        return ResponseEntity.ok("Идентификаторы добавленных рецептов - " +
                recipesService.addRecipe(recipes));
    }

    /**
     * 7. редактирование рецепта по id
     *
     * @param id     из URL
     * @param recipe из тела запроса
     * @return новый рецепт
     */
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipeById(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe newRecipe = recipesService.editRecipeById(id, recipe);
        if (newRecipe != null) {
            return ResponseEntity.ok(newRecipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 8. удаление рецепта по id
     *
     * @param id из URL
     * @return удаленный рецепт
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable int id) {
        Recipe recipe = recipesService.deleteRecipeById(id);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 9. получение рецепта по id
     *
     * @param id из URL
     * @return полученный рецепт
     * @throws IdIsIncorrectException выкидываем исключение не доходя до сервиса
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) throws IdIsIncorrectException {
        Recipe recipe = recipesService.getRecipe(id);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * 10. получение списка всех рецептов
     *
     * @return ArrayList рецептов
     */
    @GetMapping
    public ResponseEntity<Collection<Recipe>> getAllRecipes() {
        Collection<Recipe> recipes = recipesService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 11. поиск рецептов по id ингридиента
     *
     * @param id из URL
     * @return Collection рецептов
     */
    @GetMapping("/getRecipesByIngredientsId/{id}")
    public ResponseEntity<Collection<Recipe>> getRecipesByIngredientsId(@PathVariable int id) {
        Collection<Recipe> recipes = recipesService.getRecipesByIngredientsId(id);
        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 12. поиск рецептов по нескольким ингридиентам
     *
     * @param ingredients принимаем из тела запроса
     * @return список рецептов
     */
    @GetMapping("/getRecipesBySeveralIngredients/")
    public ResponseEntity<List<Recipe>> getRecipesBySeveralIngredients(@RequestBody Ingredient... ingredients) {
        if (recipesService.getRecipesBySeveralIngredients(ingredients) != null) {
            return ResponseEntity.ok(recipesService.getRecipesBySeveralIngredients(ingredients));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 13. вывод рецептов по 10 штук на странице
     * @return LinkedHashMap, где ключ - номер страницы, значение - список из 10 рецептов
     */
    @GetMapping("/getAllRecipesBy10pcs")
    public ResponseEntity<LinkedHashMap<Integer, List<Recipe>>> getAllRecipesBy10pcs() {
        if (recipesService.getAllRecipesBy10pcs() != null) {
            return ResponseEntity.ok(recipesService.getAllRecipesBy10pcs());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
