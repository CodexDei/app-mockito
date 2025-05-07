package org.codexdei.appmockito.examples.services;

import org.codexdei.appmockito.examples.models.Exam;

import java.util.Arrays;
import java.util.List;

public class Data {

    public final static List<Exam> EXAMENES = Arrays.asList(new Exam(5L,"Mathematics"), new Exam(6L,"Lenguages"),
            new Exam(7L,"Sciences"), new Exam(8L,"Programming"));

    public final static List<String> QUESTIONS = Arrays.asList("arithmetic", "integrals", "derivates", "trigonometry","geometry");

    public final static Exam EXAM = new Exam(8L,"Physics");

}
