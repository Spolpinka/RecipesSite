package pro.sky.recipessite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Recipe {
    @NonNull
    private String name;
    @NonNull
    private int timeToSpend;
    @NonNull
    private List<Ingredient> ingredients;
    @NonNull
    private Map<String, String> instructions;

}
