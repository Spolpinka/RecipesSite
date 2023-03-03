package pro.sky.recipessite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    @NonNull
    private String name;
    @NonNull
    private int timeToSpend;
    @NonNull
    List<Ingredient> ingredients;
    @NonNull
    List<String> instructions;

}
