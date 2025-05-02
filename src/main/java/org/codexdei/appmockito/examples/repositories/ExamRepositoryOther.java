package org.codexdei.appmockito.examples.repositories;

import org.codexdei.appmockito.examples.models.Exam;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryOther implements ExamRepository{
    @Override
    public List<Exam> findAll() {
        try {
            System.out.println("ExamRepositoryOther");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }
}
