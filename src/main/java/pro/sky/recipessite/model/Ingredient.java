package pro.sky.recipessite.model;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import pro.sky.recipessite.controllers.exceptions.NameIncorrectException;
import pro.sky.recipessite.controllers.exceptions.QuantityIncorrectException;
import pro.sky.recipessite.controllers.exceptions.UnitIncorrectException;


@Data
public class Ingredient {
    @NonNull
    private String name;
    @NonNull
    private int quantity;
    @NonNull
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
