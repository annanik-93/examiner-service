package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.BadQuestionAmountException;

import java.util.Collection;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock private QuestionService javaService;
    @Mock private QuestionService mathService;
    private ExaminerServiceImpl examinerService;
    private Question qJava;
    private Question qMath;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(List.of(javaService, mathService));
        qJava = new Question("JavaQ", "JavaA");
        qMath = new Question("MathQ", "MathA");
    }

    // Критерий: Тест сценария amount = 0
    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsZero() {
        when(javaService.getAll()).thenReturn(List.of(qJava));
        when(mathService.getAll()).thenReturn(List.of(qMath));
        assertThrows(BadQuestionAmountException.class, () -> examinerService.getQuestions(0));
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountExceedsLimit() {
        when(javaService.getAll()).thenReturn(List.of(qJava));
        when(mathService.getAll()).thenReturn(List.of(qMath));
        assertThrows(BadQuestionAmountException.class, () -> examinerService.getQuestions(3));
    }

    @Test
    void getQuestions_ShouldReturnCorrectAmountOfQuestions() {
        when(javaService.getAll()).thenReturn(List.of(qJava));
        when(mathService.getAll()).thenReturn(List.of(qMath));
        when(javaService.getRandomQuestion()).thenReturn(qJava);
        when(mathService.getRandomQuestion()).thenReturn(qMath);

        Collection<Question> result = examinerService.getQuestions(2);
        assertThat(result).hasSize(2).contains(qJava, qMath);
    }
}