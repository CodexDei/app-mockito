package org.codexdei.appmockito.examples.repositories;

import java.util.List;

public interface QuestionRepository {

    List<String> findQuestionsByExamId(Long id);
}
