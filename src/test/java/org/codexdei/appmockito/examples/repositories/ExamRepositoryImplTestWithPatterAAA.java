package org.codexdei.appmockito.examples.repositories;

import org.codexdei.appmockito.examples.models.Exam;
import org.codexdei.appmockito.examples.services.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamServiceImplTest2 {

    private ExamRepository mockRepository;
    private ExamService examService;
    private List<Exam> examenesEjemplo;

    @BeforeEach
    void setup() {
        // Arrange común
        mockRepository = mock(ExamRepositoryOther.class);
        //examService = new ExamServiceImpl(mockRepository,examService);
        examenesEjemplo = Arrays.asList(
                new Exam(5L, "Mathematics"),
                new Exam(6L, "Languages"),
                new Exam(7L, "Sciences"),
                new Exam(8L, "Programming")
        );
    }

    @Test
    void findExamByName_ExamenExistente_DebeRetornarExamen() {
        // Arrange
        when(mockRepository.findAll()).thenReturn(examenesEjemplo);

        // Act
        Optional<Exam> resultado = examService.findExamByName("Mathematics");

        // Assert
        assertNotNull(resultado);
        assertEquals(5L, resultado.orElseThrow().getId());
        assertEquals("Mathematics", resultado.get().getName());
        verify(mockRepository).findAll();
    }

    @Test
    void findExamByName_BusquedaParcial_DebeRetornarExamen() {
        // Arrange
        when(mockRepository.findAll()).thenReturn(examenesEjemplo);

        // Act
        Optional<Exam> resultado = examService.findExamByName("Prog");

        // Assert
        assertNotNull(resultado);
        assertEquals(8L, resultado.orElseThrow().getId());
        assertEquals("Programming", resultado.orElseThrow().getName());
    }

    @Test
    void findExamByName_ExamenNoExistente_DebeRetornarNull() {
        // Arrange
        when(mockRepository.findAll()).thenReturn(examenesEjemplo);

        // Act
        Optional<Exam> resultado = examService.findExamByName("Historia");

        // Assert
        assertNull(resultado);
    }

    @Test
    void findExamByName_RepositorioVacio_DebeRetornarNull() {
        // Arrange
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        Optional<Exam> resultado = examService.findExamByName("Mathematics");

        // Assert
        assertNull(resultado);
    }
}
