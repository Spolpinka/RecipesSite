package pro.sky.recipessite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String run() {
        return "Приложение запущено";
    }

    @GetMapping("info")
    public String info() {
        return "Имя: Яна Сироткина" + "\n" +
                "Название проекта: Рецептурная" + "\n" +
                "Дата создания проекта: 24 Feb 2023" + "\n" +
                "Описание: онлайн каталог рецептов";
    }
}
