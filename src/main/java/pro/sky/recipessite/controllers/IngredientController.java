package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;

@RestController
@RequestMapping("/ingres")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/addIngedient")
    public String addIngredient(@RequestParam Ingredient ingredient) {
        return "Идентификатор добавленного ингридиента - " + ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredient(@RequestParam int id) {
        return ingredientService.getIngredient(id);
    }
}
