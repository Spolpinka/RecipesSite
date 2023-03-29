package pro.sky.recipessite.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Cайт рецептов \"Рецептурная\"",
                description = "API серверной части сайта рецептов",
                version = "1.0.0.",
                contact = @Contact(
                        name = "Сироткина Яна Евгеньевна",
                        email = "a421243266@gmail.com",
                        url = "https://github.com/Spolpinka/CourseWorkSocks"
                )
        )
)
public class OpenApiConfig {
}
