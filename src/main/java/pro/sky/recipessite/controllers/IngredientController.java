package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.services.impl.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.services.impl.exceptions.IngredientIsNullException;

@RestController
@RequestMapping("/ingres")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/addIngedient")
    public String addIngredient(@RequestParam String name, @RequestParam int quantity, @RequestParam String volume){
        return "Идентификатор добавленного ингридиента - " + ingredientService.addIngredient(name, quantity, volume);
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredient(@RequestParam int id) {
        try {
            return ingredientService.getIngredient(id);
        } catch (IdIsIncorrectException e) {
            System.out.println("Переданный id меньше 0 или не существует!");
            return null;
        }
    }
}
