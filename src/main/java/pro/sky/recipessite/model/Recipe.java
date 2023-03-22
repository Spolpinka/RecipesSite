package pro.sky.recipessite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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

    public String getIngredientsToString() {
        String result = "";
        for (Ingredient ingredient :
                ingredients) {
            result.concat("- ")
                    .concat(ingredient.getName())
                    .concat(" - ")
                    .concat(ingredient.getQuantity()+" ")
                    .concat(ingredient.getUnit());
        }
        return result;
    }

    public String getInstructionsToString() {
        String result = "";
        for (Map.Entry<String, String> entry : instructions.entrySet()) {
            result.concat(entry.getKey()+". ").concat(entry.getValue()+"\n");
        }
        return result;
    }
}
