package pro.sky.recipessite.controllers;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

@RestController
@RequestMapping("/ingres")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/addIngredient")
    public String addIngredient(@RequestParam String name, @RequestParam int quantity, @RequestParam String volume){
        return "Идентификатор добавленного ингридиента - " + ingredientService.addIngredient(name, quantity, volume);
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredient(@RequestParam int id) throws IdIsIncorrectException {
        if (id < 0 && ingredientService.isIngresContainsId(id)){
            return ingredientService.getIngredient(id);
        } else {
            throw new IdIsIncorrectException("нет такого id или введенный id меньше 0!");
        }
    }
}
