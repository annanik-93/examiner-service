package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JavaQuestionRepositoryTest {
    private JavaQuestionRepository repository;
    private Question q1;

    @BeforeEach
    void setUp() {
        repository = new JavaQuestionRepository();
        q1 = new Question("Java?", "Yes");
    }

    // Критерий: Тест сценария работы с дубликатами при добавлении
    @Test
    void add_ShouldNotAddDuplicateQuestions() {
        repository.add(q1);
        repository.add(q1);
        assertThat(repository.getAll()).hasSize(1);
    }

    // Критерий: Тест сценария работы с пустой коллекцией (удаление)
    @Test
    void remove_ShouldThrowException_WhenCollectionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> repository.remove(q1));
    }
}