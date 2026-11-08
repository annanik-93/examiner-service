package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository repository;
    private JavaQuestionService service;
    private Question q1;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService(repository);
        q1 = new Question("Q", "A");
    }

    // Критерий: Тест сценария работы с пустой коллекцией (получение случайного вопроса)
    @Test
    void getRandomQuestion_ShouldThrowException_WhenStorageIsEmpty() {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestion_WhenStorageIsNotEmpty() {
        when(repository.getAll()).thenReturn(List.of(q1));
        assertThat(service.getRandomQuestion()).isEqualTo(q1);
    }
}