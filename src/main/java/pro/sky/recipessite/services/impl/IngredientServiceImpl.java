package pro.sky.recipessite.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.recipessite.model.Ingredient;
import pro.sky.recipessite.services.FilesService;
import pro.sky.recipessite.services.IngredientService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int id = 0;
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();
    private final FilesService filesService;

   public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredients.put(++id, ingredient);
        saveToFile();
        return id;
    }

    @Override
    public String addIngredientArray(Ingredient[] newIngredients) {
        String ids = "";
        for (Ingredient ingredient : newIngredients) {
            ingredients.put(++id, ingredient);
            ids += id + ", ";
        }
        saveToFile();
        return ids;
    }

    @Override
    public Ingredient editIngredientById(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            saveToFile();
            return ingredient;
        } else {
            return null;
        }
    }

    @Override
    public Ingredient deleteIngredientById(int id) {
        if (ingredients.containsKey(id)) {
            Ingredient i = ingredients.remove(id);
            saveToFile();
            return i;
        } else {
            return null;
        }
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredients.get(id);
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    @Override
    public boolean isIngresContainsId(int id) {
        return ingredients.containsKey(id);
    }

    private void saveToFile() {
        try {
            String s = new ObjectMapper().writeValueAsString(ingredients);
            filesService.saveIngredients(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            ingredients = new ObjectMapper().readValue(filesService.readIngredients(),
                    new TypeReference<HashMap<Integer, Ingredient>>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
