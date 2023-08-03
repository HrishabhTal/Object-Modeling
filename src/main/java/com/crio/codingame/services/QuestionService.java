package com.crio.codingame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.repositories.IQuestionRepository;
import com.crio.codingame.repositories.QuestionRepository;

public class QuestionService implements IQuestionService{
    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question create(String title, Level level, Integer difficultyScore) {
     final Question question = new Question(title,level, difficultyScore);
        return questionRepository.save(question);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Questions if level is not specified.
    // Or
    // Get List of Question which matches the level provided.

    @Override
    public List<Question> getAllQuestionLevelWise(Level level) {
        List<Question> list=new ArrayList<Question>();
        if(level==null)
        {
         list=questionRepository.findAll();
        }
       else{
        list=questionRepository.findAllQuestionLevelWise(level);
       }
       return list;
}
}
