package org.codexdei.appmockito.examples.services;

import org.codexdei.appmockito.examples.models.Exam;
import org.codexdei.appmockito.examples.repositories.ExamRepository;
import org.codexdei.appmockito.examples.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {
    //Aplicamos el patron de diseño inyeccion de dependencias, para no crear los mock manualmente, por
    //ello ahora estan comentados
    @Mock
    ExamRepository repository;
    @Mock
    QuestionRepository questionRepository;
    @InjectMocks
    ExamServiceImpl service;

    @BeforeEach
    void setUp() {
        //Se comenta este ahora porque se aplico en la clase la anotacion @ExtendWith...
        //Arrange
        //aplicamos la inyeccion de dependencias
//        MockitoAnnotations.openMocks(this);

        //Aplicando Patron de pruebas AAA, tiene 3 pasos:Arrange(preparar), Act(actuar), Assert(afirmar)
        //Arrange
//        repository = mock(ExamRepository.class);
//        questionRepository = mock(QuestionRepository.class);
//        service = new ExamServiceImpl(repository, questionRepository);
    }

    @Test
    void findAll() {

        List<Exam> data = Arrays.asList(new Exam(5L,"Mathematics"), new Exam(6L,"Lenguages"),
                new Exam(7L,"Sciences"), new Exam(8L,"Programming"));
        when(repository.findAll()).thenReturn(data);
        //Act
        Optional<Exam> exam = service.findExamByName("Mathematics");
        //Assert
        assertTrue(exam.isPresent());
        assertEquals(5L,exam.orElseThrow().getId());
        assertEquals("Mathematics",exam.get().getName());
    }


    @Test
    void findAllListEmpty() {

        List<Exam> data = Collections.emptyList();
        when(repository.findAll()).thenReturn(data);
        //Act
        Optional<Exam> exam = service.findExamByName("Mathematics");
        //Assert
        assertFalse(exam.isPresent());
        //para habilitar las siguientes lineas hay que usar en la linea anterior assertTue
//        assertEquals(5L,exam.orElseThrow().getId());
//        assertEquals("Mathematics",exam.get().getName());
    }

    @Test
    void testQuestionsExam() {
        //Arrange:
        when(repository.findAll()).thenReturn(Datas.EXAMENES);
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Datas.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        //Assert:
        List<String> questions = exam.getQuestions();
        int expectedSize = Datas.QUESTIONS.size();

        assertAll(
            () -> assertEquals(expectedSize,exam.getQuestions().size(),
                    "The number the questions must match Data.QUESTIONS"),
            () -> assertTrue(questions.contains("arithmetic"),
                    "The question is not in the list of questions")
        );
    }

    @Test
    void testQuestionsExamVerify() {
        //Arrange:
        when(repository.findAll()).thenReturn(Datas.EXAMENES);
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Datas.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        //Assert:
        List<String> questions = exam.getQuestions();
        int expectedSize = Datas.QUESTIONS.size();

        assertAll(
                () -> assertEquals(expectedSize,exam.getQuestions().size(),
                        "The number the questions must match Data.QUESTIONS"),
                () -> assertTrue(questions.contains("arithmetic"),
                        "The question is not in the list of questions")
        );
        //Verifica si se llamo a determinado metodo, la llamada al metodo debe ser fuera de los parentesis
        verify(repository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void testNotExamVerify() {
        //Arrange:
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Datas.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        assertNull(exam);
        verify(repository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }
}