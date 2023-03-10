package pro.sky.recipessite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Ingredient {
    @NonNull
    private String name;
    @NonNull
    private int quantity;
    @NonNull
    private String unit;
}
