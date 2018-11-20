package com.yus.wenda_beta_2.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.yus.wenda_beta_2.mapper.QuestionMapper;
import com.yus.wenda_beta_2.pojo.Question;
import com.yus.wenda_beta_2.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	 @Autowired
	 QuestionMapper questionmapper;
	 @Autowired
	 SensitiveService sensitiveService;
	@Override
	public int addQuestion(Question question) {
		// TODO Auto-generated method stub
//		过滤HTML
		question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
     // 敏感词过滤
        question.setTitle(sensitiveService.filter(question.getTitle()));
        question.setContent(sensitiveService.filter(question.getContent()));
		return questionmapper.insert(question);
		
	}
	@Override
	public List<Question> getAllQuestion() {
		// TODO Auto-generated method stub
		
		List<Question> selectAllQuestion = questionmapper.selectAllQuestion();
		
		return selectAllQuestion;
	}
	@Override
	public List<Question> selectAllQuestionLimit(Integer num) {
		// TODO Auto-generated method stub
		List<Question> selectAllQuestionLimit = questionmapper.selectAllQuestionLimit(num);
		return selectAllQuestionLimit;
	}
	@Override
	public Question selectQuestionById(Integer id) {
		// TODO Auto-generated method stub
		Question selectByPrimaryKey = questionmapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
	@Override
	public int updateCommentCount(int id, int commentCount) {
		// TODO Auto-generated method stub
		questionmapper.updateCommentCount(id, commentCount);
		return 0;
	}

}
