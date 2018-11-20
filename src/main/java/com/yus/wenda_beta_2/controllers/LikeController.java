package com.yus.wenda_beta_2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yus.wenda_beta_2.async.EventModel;
import com.yus.wenda_beta_2.async.EventProducer;
import com.yus.wenda_beta_2.async.EventType;
import com.yus.wenda_beta_2.mapper.QuestionMapper;
import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.EntityType;
import com.yus.wenda_beta_2.pojo.HostHolder;
import com.yus.wenda_beta_2.service.CommentService;
import com.yus.wenda_beta_2.service.QuestionService;
import com.yus.wenda_beta_2.service.serviceImpl.LikeService;
import com.yus.wenda_beta_2.utils.JedisAdapter;
import com.yus.wenda_beta_2.utils.WendaUtil;

@Controller
public class LikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;
    
    @Autowired
    QuestionService QuestionService;
    

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return WendaUtil.getJSONString(999);
        }
        logger.info("将like消息发送出去了！");
        Comment comment = commentService.getCommentById(commentId);

        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(hostHolder.getUser().getId()).setEntityId(commentId)
                .setEntityType(EntityType.ENTITY_COMMENT).setEntityOwnerId(comment.getUserId())
                .setExt("question", QuestionService.selectQuestionById(comment.getEntityId()).getTitle()));


        long likeCount = likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        
        logger.info(String.valueOf(likeCount));
        logger.info(WendaUtil.getJSONString(0),String.valueOf(likeCount));
        return WendaUtil.getJSONString(0, String.valueOf(likeCount));
       
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return WendaUtil.getJSONString(999);
        }
        logger.info("lsdfhs fsfsakfhsafkdsahfjks");
        long likeCount = likeService.disLike(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, commentId);
        return WendaUtil.getJSONString(0, String.valueOf(likeCount));
    }
}
