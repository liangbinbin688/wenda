package com.yus.wenda_beta_2.service;

import java.util.List;

import com.yus.wenda_beta_2.pojo.Question;

public interface QuestionService {
	
	public int addQuestion(Question question );
		
	public List<Question> getAllQuestion();
	
	public List<Question> selectAllQuestionLimit(Integer num);
	
	public Question selectQuestionById(Integer id);
	
	public int updateCommentCount(int id,int commentCount);

}
