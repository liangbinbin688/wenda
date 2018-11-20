package com.yus.wenda_beta_2.mapper;

import com.yus.wenda_beta_2.pojo.Question;
import com.yus.wenda_beta_2.pojo.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface QuestionMapper {
    @SelectProvider(type=QuestionSqlProvider.class, method="countByExample")
    int countByExample(QuestionExample example);

    @DeleteProvider(type=QuestionSqlProvider.class, method="deleteByExample")
    int deleteByExample(QuestionExample example);

    @Delete({
        "delete from question",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into question (id, title, ",
        "user_id, created_date, ",
        "comment_count, content)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{commentCount,jdbcType=INTEGER}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Question record);

    @InsertProvider(type=QuestionSqlProvider.class, method="insertSelective")
    int insertSelective(Question record);

    @SelectProvider(type=QuestionSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    @SelectProvider(type=QuestionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER)
    })
    List<Question> selectByExample(QuestionExample example);

    @Select({
        "select",
        "id, title, user_id, created_date, comment_count, content",
        "from question",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Question selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "id, title, user_id, created_date, comment_count, content",
        "from question"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Question> selectAllQuestion();
    
    @Select({
        "select",
        "id, title, user_id, created_date, comment_count, content",
        "from question order by id desc limit #{num,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Question> selectAllQuestionLimit(Integer num);
    
    

    @UpdateProvider(type=QuestionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    @UpdateProvider(type=QuestionSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    @UpdateProvider(type=QuestionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    @UpdateProvider(type=QuestionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Question record);

    @Update({
        "update question",
        "set title = #{title,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "comment_count = #{commentCount,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Question record);

    @Update({
        "update question",
        "set title = #{title,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "comment_count = #{commentCount,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Question record);
    
    @Update({
        "update question",
         "set comment_count = #{commentCount}",
        "where id = #{id}"
    })
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);
}