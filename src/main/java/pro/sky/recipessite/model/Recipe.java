package pro.sky.recipessite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import pro.sky.recipessite.controllers.exceptions.IngredientListIncorrectException;
import pro.sky.recipessite.controllers.exceptions.InstructionsIncorrectException;
import pro.sky.recipessite.controllers.exceptions.NameIncorrectException;
import pro.sky.recipessite.controllers.exceptions.TimeIncorrectException;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "Сущность Рецепт")
public class Recipe {
    @NonNull
    @Schema(description = "Наименование рецепта")
    private String name;
    @NonNull
    @Schema(description = "Время, нужное для приготовления")
    private int timeToSpend;
    @NonNull
    @Schema(description = "Список ингредиентов")
    private List<Ingredient> ingredients;
    @NonNull
    @Schema(description = "Инструкции по приготовлению")
    private Map<String, String> instructions;

    public Recipe(@NonNull String name, @NonNull int timeToSpend, @NonNull List<Ingredient> ingredients,
                  @NonNull Map<String, String> instructions)
            throws NameIncorrectException, TimeIncorrectException,
            IngredientListIncorrectException, InstructionsIncorrectException {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            throw new NameIncorrectException("введено некорректное значение name");
        }
        if (timeToSpend > 0) {
            this.timeToSpend = timeToSpend;
        } else {
            throw new TimeIncorrectException("введено некорректное значение \"время приготовления\"");
        }
        if (!ingredients.isEmpty()) {
            this.ingredients = ingredients;
        } else {
            throw new IngredientListIncorrectException("введен пустой список ингридиентов");
        }
        if (!instructions.isEmpty()) {
            this.instructions = instructions;
        } else {
            throw new InstructionsIncorrectException("введ пустой список инструкций по приготовлению");
        }
    }
}
