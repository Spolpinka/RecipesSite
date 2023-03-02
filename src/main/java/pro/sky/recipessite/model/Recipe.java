package pro.sky.recipessite.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int timeToSpend;
    List<Ingredient> ingredients;
    List<String> instructions;

}
