package org.codexdei.appmockito.examples.services;

import org.codexdei.appmockito.examples.models.Exam;

import java.util.Optional;

public interface ExamService {

    Optional<Exam> findExamByName(String name);
    Exam    findExamByNameWithQuestions(String name);
    Exam saveExam(Exam exam);
}
