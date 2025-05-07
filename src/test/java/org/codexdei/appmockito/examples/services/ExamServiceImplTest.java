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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {
    //Aplicamos el patron de diseño inyeccion de dependencias, para no crear los mock manualmente, por
    //ello ahora estan comentados
    @Mock
    ExamRepository examRepository;
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
        when(examRepository.findAll()).thenReturn(data);
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
        when(examRepository.findAll()).thenReturn(data);
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
        when(examRepository.findAll()).thenReturn(Data.EXAMENES);
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        //Assert:
        List<String> questions = exam.getQuestions();
        int expectedSize = Data.QUESTIONS.size();

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
        when(examRepository.findAll()).thenReturn(Data.EXAMENES);
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        //Assert:
        List<String> questions = exam.getQuestions();
        int expectedSize = Data.QUESTIONS.size();

        assertAll(
                () -> assertEquals(expectedSize,exam.getQuestions().size(),
                        "The number the questions must match Data.QUESTIONS"),
                () -> assertTrue(questions.contains("arithmetic"),
                        "The question is not in the list of questions")
        );
        //Verifica si se llamo a determinado metodo, la llamada al metodo debe ser fuera de los parentesis
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void testNotExamVerify() {
        //Arrange:
        when(examRepository.findAll()).thenReturn(Collections.emptyList());
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS);
        //Act:
        Exam exam = service.findExamByNameWithQuestions("Sciences");
        assertNull(exam);
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void examTestSave() {

        Exam newExam = Data.EXAM;
        newExam.setQuestions(Data.QUESTIONS);

        when(examRepository.saveExam(any(Exam.class))).thenReturn(Data.EXAM);
        Exam exam = service.saveExam(newExam);
        assertNotNull(exam.getId());
        assertEquals(8L,exam.getId());
        assertEquals("Physics",exam.getName());

        verify(examRepository).saveExam(newExam);
        verify(questionRepository).saveMultiple(newExam.getQuestions());
    }
}