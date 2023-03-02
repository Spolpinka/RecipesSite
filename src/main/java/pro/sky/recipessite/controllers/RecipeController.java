package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/addRecipe")
    public String addRecipe(@RequestParam Recipe recipe) {
        return "Идентификатор добавленного рецепта - " + recipesService.addRecipe(recipe);
    }

    @GetMapping("/getRecipe")
    public Recipe getRecipe(@RequestParam int id) {
        return recipesService.getRecipe(id);
    }
}
