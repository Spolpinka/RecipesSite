package pro.sky.recipessite.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * 1. добавление ингредиента, соответствующий ингредиент приходит в формате Json в теле запроса
     *
     * @param ingredient из тела запроса
     * @return ResponseEntity</ строка>
     */
    @PostMapping("/addIngredient")
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok("Идентификатор добавленного ингридиента - " +
                ingredientService.addIngredient(ingredient));
    }

    /**
     * 1.1. ввод ингредиентов массивом
     * @param ingredients массив из тела запроса
     * @return строку с идентификаторами
     */
    @PostMapping("/addIngredientArray")
    public ResponseEntity<String> addIngredientArray(@RequestBody Ingredient... ingredients) {
        return ResponseEntity.ok("Идентификатор добавленного ингридиента - " +
                ingredientService.addIngredientArray(ingredients));
    }

    /**
     * 2. редактирование ингредиента
     *
     * @param id из URL
     * @param ingredient из тела запроса
     * @return ингридиент (объект)
     */
    @PutMapping("/editIngredient/{id}")
    public ResponseEntity<Ingredient> editIngredientById(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredientById(id, ingredient);
        if (newIngredient != null) {
            return ResponseEntity.ok(newIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 3. удаление ингридента по id
     *
     * @param id из URL
     * @return удаленный ингредиент
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredientById(@PathVariable int id) {
        Ingredient ingredient = ingredientService.deleteIngredientById(id);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 4. получение информации об ингрединте по id
     *
     * @param id из URL
     * @return ингридиент
     * @throws IdIsIncorrectException проверка id на входе
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) throws IdIsIncorrectException {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 5. получение полного списка ингредиентов
     * @return ArrayList всех ингредиентов
     */
    @GetMapping()
    public ResponseEntity<Collection<Ingredient>> getAllIngredients() {
        Collection<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
}
