package pro.sky.recipessite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipessite.controllers.exceptions.IdIsIncorrectException;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.IngredientService;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингридиенты", description = "работа с ингридиентами: добавление, редактирование, получение, удаление")
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
    @Operation(
            summary = "добавление ингридиента",
            description = "добавление ингридиента из тела запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "ingredient",
                            description = "объект ingredient в формате JSON",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингридиент успешно добавлен"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "добавление не удалось"
                    )
            }

    )
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok("Идентификатор добавленного ингридиента - " +
                ingredientService.addIngredient(ingredient));
    }

    /**
     * 1.1. ввод ингредиентов массивом
     *
     * @param ingredients массив из тела запроса
     * @return строку с идентификаторами
     */
    @PostMapping("/addIngredientArray")
    @Operation(
            summary = "добавление нескольких ингридиентов",
            description = "добавление ингридиентов из массива в теле запроса "
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "ingredient",
                            description = "массив объектов ingredient в формате JSON",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингридиент успешно добавлен"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "добавление не удалось"
                    )
            }

    )
    public ResponseEntity<String> addIngredientArray(@RequestBody Ingredient... ingredients) {
        return ResponseEntity.ok("Идентификатор добавленного ингридиента - " +
                ingredientService.addIngredientArray(ingredients));
    }

    /**
     * 2. редактирование ингредиента
     *
     * @param id         из URL
     * @param ingredient из тела запроса
     * @return ингридиент (объект)
     */
    @PutMapping("/editIngredient/{id}")
    @Operation(
            summary = "редактирование ингридиента",
            description = "редактирование ингридиента по id в пути запроса путем замещения новым из тела запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "ingredient",
                            description = "объект ingredient в формате JSON",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }

                    ),
                    @Parameter(
                            name = "id",
                            description = "id ингридиента для редактирования",
                            example = "1"
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингридиент успешно заменен"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Замена не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Удаление ингридиента",
            description = "Удаление ингридиента по id в пути запроса "
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "id ингридиента для удаления",
                            example = "1"

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Выведенный Ингридиент успешно удален",
                            content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Удаление не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Получение информации об ингридиенте",
            description = "Получение информации об ингридиенте по id в пути запроса "
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "id ингридиента для удаления",
                            example = "1"

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация об ингридиенте получена",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Получение информации об ингридиенте не удалось"
                    )
            }

    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) throws IdIsIncorrectException {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 5. получение полного списка ингредиентов
     *
     * @return ArrayList всех ингредиентов
     */
    @GetMapping()
    @Operation(
            summary = "Удаление ингридиента",
            description = "Удаление ингридиента по id в пути запроса "
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация об всех ингридиентах успешно получена",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Получение информации не удалось"
                    )
            }

    )
    public ResponseEntity<Collection<Ingredient>> getAllIngredients() {
        Collection<Ingredient> ingredients = ingredientService.getAllIngredients();
        if (!ingredients.isEmpty()) {
            return ResponseEntity.ok(ingredients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
