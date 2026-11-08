package pro.sky.java.course2.examinerservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

@SpringBootApplication
public class ExaminerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExaminerServiceApplication.class, args);
    }

    // Автоматическое наполнение базы при старте приложения

    @Bean
    public CommandLineRunner initData(
            @Qualifier("javaQuestionRepository") QuestionRepository javaRepo,
            @Qualifier("mathQuestionRepository") QuestionRepository mathRepo) {
        return args -> {
            // Наполняем Java
            javaRepo.add(new Question("Что такое ООП?", "Объектно-ориентированное программирование"));
            javaRepo.add(new Question("Что такое Сет?", "Коллекция уникальных элементов"));
            javaRepo.add(new Question("Чем интерфейс отличается от абстрактного класса?", "Интерфейс описывает только поведение."));

            // Наполняем Math
            mathRepo.add(new Question("Сколько будет 2 + 2?", "4"));
            mathRepo.add(new Question("Чему равен квадратный корень из 16?", "4"));
            mathRepo.add(new Question("Чему равен синус 90 градусов?", "1"));

            System.out.println(">>> БАЗА ДАННЫХ ЭКЗАМЕНА УСПЕШНО НАПОЛНЕНА ВОПРОСАМИ! <<<");
        };
    }
}

/*
ЗАПУСК ПРОГРАММЫ:

- вопросы по Java:
http://localhost:8080/exam/java
[{"question":"Что такое ООП?","answer":"Объектно-ориентированное программирование"},{"question":"Что такое Сет?","answer":"Коллекция уникальных элементов"},{"question":"Чем интерфейс отличается от абстрактного класса?","answer":"Интерфейс описывает только поведение."}]

- вопросы по математике:
http://localhost:8080/exam/math
[{"question":"Чему равен квадратный корень из 16?","answer":"4"},{"question":"Чему равен синус 90 градусов?","answer":"1"},{"question":"Сколько будет 2 + 2?","answer":"4"}]

- сгенерировать случайный билет (например, из 3 вопросов):
http://localhost:8080/exam/get/3
[{"question":"Что такое ООП?","answer":"Объектно-ориентированное программирование"},{"question":"Чем интерфейс отличается от абстрактного класса?","answer":"Интерфейс описывает только поведение."},{"question":"Сколько будет 2 + 2?","answer":"4"}]
НО при каждом обновлении страницы с билетом (/exam/get/3) вопросы будут случайным образом перемешиваться и выбираться из общей базы!
http://localhost:8080/exam/get/3  - новая страница - рандомный вопрос
 */
