package org.codexdei.appmockito.examples.repositories;

import org.codexdei.appmockito.examples.models.Exam;
import org.codexdei.appmockito.examples.services.ExamService;
import org.codexdei.appmockito.examples.services.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamRepositoryImplTest {

    ExamRepository repository;
    ExamService service;
    QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        //Aplicando Patron de pruebas AAA, tiene 3 pasos:Arrange(preparar), Act(actuar), Assert(afirmar)
        //Arrange
        repository = mock(ExamRepository.class);
        questionRepository = mock(QuestionRepository.class);
        service = new ExamServiceImpl(repository, questionRepository);
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
}