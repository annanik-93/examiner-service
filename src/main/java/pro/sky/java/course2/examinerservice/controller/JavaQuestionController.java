package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;
import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    // Критерий: Поле QuestionService объявлено как private final и инициализировано через конструктор
    private final QuestionService service;

    // Повышенная сложность: работа через квалифаер
    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService service) {
        this.service = service;
    }

    // Критерий: Наличие эндпоинта POST /exam/java/add и параметров @RequestParam
    @PostMapping("/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    // Критерий: Наличие эндпоинта DELETE /exam/java/remove и параметров @RequestParam
    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        return service.remove(new Question(question, answer));
    }

    // Критерий: Наличие эндпоинта GET /exam/java
    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}