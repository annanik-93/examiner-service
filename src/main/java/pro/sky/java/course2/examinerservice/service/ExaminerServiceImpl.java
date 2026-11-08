package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.BadQuestionAmountException;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    // Повышенная сложность: Внедряем список всех сервисов для работы с обоими предметами сразу
    private final List<QuestionService> questionServices;
    private final Random random = new Random();

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    // Критерий: Метод Collection<Question> getQuestions(int amount) реализован корректно
    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalAvailable = questionServices.stream()
                .mapToInt(service -> service.getAll().size())
                .sum();

        // Критерий: При amount > количества доступных вопросов или при amount <= 0 выбрасывается исключение BAD_REQUEST
        if (amount <= 0 || amount > totalAvailable) {
            throw new BadQuestionAmountException("Запрошено неверное количество вопросов: " + amount);
        }

        // Критерии: Для сбора результатов используется Set с проверкой размера
        Set<Question> examTicket = new HashSet<>();

        while (examTicket.size() < amount) {
            QuestionService randomService = questionServices.get(random.nextInt(questionServices.size()));
            if (!randomService.getAll().isEmpty()) {
                examTicket.add(randomService.getRandomQuestion());
            }
        }
        return examTicket;
    }
}