package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/addRecipe")
    public String addRecipe(@RequestParam String name, @RequestParam int timeToSpend, @RequestParam List<Ingredient> ingredients, @RequestParam List<String> instructions) {
        return "Идентификатор добавленного рецепта - " + recipesService.addRecipe(name, timeToSpend, ingredients, instructions);
    }

    @GetMapping("/getRecipe")
    public Recipe getRecipe(@RequestParam int id) throws IdIsIncorrectException {
        if (id >= 0 && recipesService.isRecipesContainsId(id) ){
            return recipesService.getRecipe(id);
        } else {
            throw new IdIsIncorrectException("Нет такого id!");
        }
    }
}
