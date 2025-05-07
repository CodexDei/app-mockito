package org.codexdei.appmockito.examples.services;

import org.codexdei.appmockito.examples.models.Exam;
import org.codexdei.appmockito.examples.repositories.ExamRepository;
import org.codexdei.appmockito.examples.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class ExamServiceImpl implements ExamService{

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;

    public ExamServiceImpl(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {

        return examRepository.findAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .findFirst();
    }

    @Override
    public Exam findExamByNameWithQuestions(String name) {
        Exam exam = null;

        Optional<Exam> examOptional = findExamByName(name);
        if(examOptional.isPresent()){
            exam = examOptional.orElseThrow();
            List<String> questionsList = questionRepository.findQuestionsByExamId(exam.getId());
            exam.setQuestions(questionsList);
        }
        return exam;
    }

    @Override
    public Exam saveExam(Exam exam) {

        if (!exam.getQuestions().isEmpty()){
            questionRepository.saveMultiple(exam.getQuestions());
        }

        return examRepository.saveExam(exam);
    }

}
