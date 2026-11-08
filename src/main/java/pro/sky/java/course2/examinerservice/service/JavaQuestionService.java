package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;
import java.util.*;

@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    // Повышенная сложность: Инжектирование репозитория в сервис
    public JavaQuestionService(QuestionRepository javaQuestionRepository) {
        this.repository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    // Критерий: Возвращается случайный вопрос при непустой коллекции через Random
    @Override
    public Question getRandomQuestion() {
        Collection<Question> all = repository.getAll();
        if (all.isEmpty()) {
            throw new IllegalStateException("База вопросов по Java пуста");
        }
        List<Question> list = new ArrayList<>(all);
        return list.get(random.nextInt(list.size()));
    }
}