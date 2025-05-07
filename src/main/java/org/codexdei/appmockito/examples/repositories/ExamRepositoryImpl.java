package org.codexdei.appmockito.examples.repositories;

import org.codexdei.appmockito.examples.models.Exam;

import java.util.Collections;
import java.util.List;

public class ExamRepositoryImpl implements ExamRepository{

    @Override
    public Exam saveExam(Exam exam) {
        return null;
    }

    @Override
    public List<Exam> findAll() {

        return Collections.EMPTY_LIST;
//        return Arrays.asList(new Exam(5L,"Mathematics"), new Exam(6L,"Lenguages"),
//                            new Exam(7L,"Sciences"), new Exam(8L,"Programming"));
    }
}
