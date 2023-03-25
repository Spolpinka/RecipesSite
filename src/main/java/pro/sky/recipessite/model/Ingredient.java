package pro.sky.recipessite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import pro.sky.recipessite.controllers.exceptions.NameIncorrectException;
import pro.sky.recipessite.controllers.exceptions.QuantityIncorrectException;
import pro.sky.recipessite.controllers.exceptions.UnitIncorrectException;


@Data
@Schema(description = "Сущность Ингредиент")
@NoArgsConstructor
public class Ingredient {
    @NonNull
    @Schema(description = "Наименование ингредиента")
    private String name;
    @NonNull
    @Schema(description = "Количество ингредиента")
    private int quantity;
    @NonNull
    @Schema(description = "Единицы измерения для количества (шт., ст.л., ч.л. и т.п.")
    private String unit;

    public Ingredient(@NonNull String name, @NonNull int quantity, @NonNull String unit)
            throws NameIncorrectException, QuantityIncorrectException, UnitIncorrectException {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            throw new NameIncorrectException("введено некорректное значение name");
        }
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new QuantityIncorrectException("введено некорректное значение количества ингредиента");
        }
        if (StringUtils.isBlank(unit)) {
            this.unit = unit;
        } else {
            throw new UnitIncorrectException("введено некорректное значение единиц измерения ингридиента");
        }
    }
}
