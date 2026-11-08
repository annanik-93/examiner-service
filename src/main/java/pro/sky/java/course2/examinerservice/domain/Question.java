package pro.sky.java.course2.examinerservice.domain;

import java.util.Objects;

public class Question {
    // Критерий: поля приватные
    private final String question;
    private final String answer;

    // Критерий: наличие публичного конструктора
    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Критерий: реализованы геттеры
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }

    // Критерий: метод equals сравнивает по полям через Objects.equals с соблюдением всех базовых проверок
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Проверка на ссылки
        if (o == null || getClass() != o.getClass()) return false; // Проверка на null и класс
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question) &&
                Objects.equals(answer, question1.answer); // Сравнение по полям
    }

    // Критерий: hashCode рассчитан по тем же полям
    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return "Question{" + "question='" + question + '\'' + ", answer='" + answer + '\'' + '}';
    }
}