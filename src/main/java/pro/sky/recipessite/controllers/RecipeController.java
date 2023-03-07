package pro.sky.recipessite.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    /**
     * 6. добавление рецепта
     * @param recipe из тебя запроса
     * @return строка с номером присвоенного id
     */
    @PostMapping("/addRecipe")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok("Идентификатор добавленного рецепта - " +
                recipesService.addRecipe(recipe));
    }

    /**
     * 7. редактирование рецепта по id
     * @param id из URL
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
     * @param id из URL
     * @return полученный рецепт
     * @throws IdIsIncorrectException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) throws IdIsIncorrectException {
        if (id >= 0 && recipesService.isRecipesContainsId(id) ){
            return ResponseEntity.ok(recipesService.getRecipe(id));
        } else {
            throw new IdIsIncorrectException("нет такого id или введенный id меньше 0!");
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Recipe>> getAllRecipes() {
        ArrayList<Recipe> recipes = recipesService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
