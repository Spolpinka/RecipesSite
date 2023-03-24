package pro.sky.recipessite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipessite.services.FilesService;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/baseExport")
    @Operation(
            summary = "Экспорт базы рецептов",
            description = "Выгрузка файла Json с базой всех рецептов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно выгружен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = File.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Выгрузка не удалась"
                    )
            }

    )
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = filesService.getRecipesFile();
        if (file.exists()) {
            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeBase.json\"")
                    .contentLength(file.length())
                    .body(isr);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/recipesImport", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Импорт базы рецептов",
            description = "Замещение базы рецептов файлом из запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "file",
                            description = "файл в формате JSON, замещающий собой старый файл",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = File.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = File.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Добавление не удалось"
                    )
            }

    )
    public ResponseEntity<Void> uploadRecipeBase(@RequestParam MultipartFile file) {
        if (filesService.uploadRecipeFile(file)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Импорт базы ингридиентов",
            description = "Файл базы ингридиентов замещается новым файлом из запроса"
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "file",
                            description = "файл в формате JSON, замещающий собой старый файл",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = File.class))
                                    )
                            }

                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно добавлен"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "добавление не удалось"
                    )
            }

    )
    @PutMapping(value = "/ingredientsImport", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientBase(@RequestParam MultipartFile file) {
        if (filesService.uploadIngredientFile(file)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






}
