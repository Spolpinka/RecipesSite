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
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.model.Recipe;
import pro.sky.recipessite.services.RecipesService;

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
     *
     * @param recipes из тела запроса
     * @return строку с идентификаторами добавленных рецептов
     */
    @PostMapping("/addRecipe")
    @Operation(
            summary = "Добавление рецепта",
            description = "Добавление рецепта из тела запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "recipe",
                            description = "объект recipe в формате JSON",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Добавление не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Редактирование рецепта",
            description = "Редактирование рецепта по id путем замещения новым рецептом из тела запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "id рецепта для редактирования (замещения)",
                            example = "1"
                    ),
                    @Parameter(
                            name = "recipe",
                            description = "новый объект recipe в формате JSON для замены",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Добавление не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Удаление рецепта",
            description = "Удаление рецепта по id из пути запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "id рецепта для редактирования (замещения)",
                            example = "1"
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Удаление не удалось"
                    )
            }

    )
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
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта",
            description = "Получение рецепта по id из пути запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "id рецепта для редактирования (замещения)",
                            example = "1"
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Добавление не удалось"
                    )
            }

    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
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
    @Operation(
            summary = "Получение списка всех рецепта",
            description = "Редактирование рецепта по id путем замещения новым рецептом из тела запроса"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепты успешно получены",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Получение рецептов не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Поиск рецепта по id ингридиента",
            description = "Поиск рецептов по id ингридиента из пути запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id Ингредиента",
                            description = "id ингредиента для редактирования (замещения)",
                            example = "1",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Integer.class))
                                    )
                            }
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепты найдены",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Найти рецепты не удалось"
                    )
            }

    )
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
    @Operation(
            summary = "Поиск рецептов по нескольким ингредиентам",
            description = "Поиск рецептов по нескольким ингридиентам из тела запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "ingredient",
                            description = "объекты ingredient в формате JSON для поиска рецептов, которые содержат указанные объекты",
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
                            description = "Рецепт успешно добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Добавление не удалось"
                    )
            }

    )
    public ResponseEntity<List<Recipe>> getRecipesBySeveralIngredients(@RequestBody Ingredient... ingredients) {
        if (recipesService.getRecipesBySeveralIngredients(ingredients) != null) {
            return ResponseEntity.ok(recipesService.getRecipesBySeveralIngredients(ingredients));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 13. вывод рецептов по 10 штук на странице
     *
     * @return LinkedHashMap, где ключ - номер страницы, значение - список из 10 рецептов
     */
    @GetMapping("/getAllRecipesBy10pcs")
    @Operation(
            summary = "вывод рецептов по 10 штук на странице",
            description = "поиск и возвращение всех рецептов по 10 штук на 1 эелемент Map"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "рецепты найдены и возвращены",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<LinkedHashMap<Integer, List<Recipe>>> getAllRecipesBy10pcs() {
        if (recipesService.getAllRecipesBy10pcs() != null) {
            return ResponseEntity.ok(recipesService.getAllRecipesBy10pcs());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
