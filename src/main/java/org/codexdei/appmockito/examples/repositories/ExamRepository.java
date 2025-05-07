package org.codexdei.appmockito.examples.repositories;

import org.codexdei.appmockito.examples.models.Exam;

import java.util.List;

public interface ExamRepository {

    Exam saveExam(Exam exam);
    List<Exam> findAll();
}
