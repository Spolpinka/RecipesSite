package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String mainMethod() {
        return "Приложение \"Сайт рецептов\" запущено";
    }

    @GetMapping("info")
    public String info() {
        return """
                Имя: Яна Сироткина
                Название проекта: "Рецептурная"
                Дата создания проекта: 24 Feb 2023
                Описание: онлайн каталог рецептов""";
    }
}
