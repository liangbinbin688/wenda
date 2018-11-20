package com.yus.wenda_beta_2.mapper;

import com.yus.wenda_beta_2.pojo.Message;
import com.yus.wenda_beta_2.pojo.MessageExample;
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

public interface MessageMapper {
    @SelectProvider(type=MessageSqlProvider.class, method="countByExample")
    int countByExample(MessageExample example);

    @DeleteProvider(type=MessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageExample example);

    @Delete({
        "delete from message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into message (id, from_id, ",
        "to_id, created_date, ",
        "has_read, conversation_id, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{fromId,jdbcType=INTEGER}, ",
        "#{toId,jdbcType=INTEGER}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{hasRead,jdbcType=INTEGER}, #{conversationId,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Message record);

    @InsertProvider(type=MessageSqlProvider.class, method="insertSelective")
    int insertSelective(Message record);

    @SelectProvider(type=MessageSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="from_id", property="fromId", jdbcType=JdbcType.INTEGER),
        @Result(column="to_id", property="toId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.INTEGER),
        @Result(column="conversation_id", property="conversationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Message> selectByExampleWithBLOBs(MessageExample example);

    @SelectProvider(type=MessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="from_id", property="fromId", jdbcType=JdbcType.INTEGER),
        @Result(column="to_id", property="toId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.INTEGER),
        @Result(column="conversation_id", property="conversationId", jdbcType=JdbcType.VARCHAR)
    })
    List<Message> selectByExample(MessageExample example);

    @Select({
        "select",
        "id, from_id, to_id, created_date, has_read, conversation_id, content",
        "from message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="from_id", property="fromId", jdbcType=JdbcType.INTEGER),
        @Result(column="to_id", property="toId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.INTEGER),
        @Result(column="conversation_id", property="conversationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Message selectByPrimaryKey(Integer id);
    
    /*@Select({"select ", INSERT_FIELDS, " ,count(id) as id from ( select * from ", TABLE_NAME, " where from_id=#{userId} or to_id=#{userId} order by id desc) tt 
     * group by conversation_id  order by created_date desc limit #{offset}, #{limit}"})
    List<Message> getConversationList(@Param("userId") int userId,
                                      @Param("offset") int offset, @Param("limit") int limit);*/
    
    @Select({
        "select ",
        "from_id, to_id, created_date, has_read, conversation_id, content,count(id) as id",
        "from (select * from message where from_id=#{userId} or to_id=#{userId} order by id desc) ",
        "tt group by conversation_id  order by created_date desc limit #{offset}, #{limit}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="from_id", property="fromId", jdbcType=JdbcType.INTEGER),
        @Result(column="to_id", property="toId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.INTEGER),
        @Result(column="conversation_id", property="conversationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Message> getConversationList(@Param("userId") int userId,
            @Param("offset") int offset, @Param("limit") int limit);
   /* @Select({"select count(id) from ", TABLE_NAME, " where has_read=0 and to_id=#{userId} and conversation_id=#{conversationId}"})
    int getConvesationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);*/
    
    @Select({
        "select",
        "count(id) ",
        "from message",
        "where has_read=0 and to_id=#{userId} and conversation_id=#{conversationId}"
    })
    
    int getConvesationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);

    
    /*@Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where conversation_id=#{conversationId} order by id desc limit #{offset}, #{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset, @Param("limit") int limit);*/
    @Select({
        "select",
        "id, from_id, to_id, created_date, has_read, conversation_id, content",
        "from message",
        "where conversation_id=#{conversationId} order by id desc limit #{offset}, #{limit}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="from_id", property="fromId", jdbcType=JdbcType.INTEGER),
        @Result(column="to_id", property="toId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.INTEGER),
        @Result(column="conversation_id", property="conversationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
            @Param("offset") int offset, @Param("limit") int limit);
    
    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    @Update({
        "update message",
        "set from_id = #{fromId,jdbcType=INTEGER},",
          "to_id = #{toId,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "has_read = #{hasRead,jdbcType=INTEGER},",
          "conversation_id = #{conversationId,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Message record);

    @Update({
        "update message",
        "set from_id = #{fromId,jdbcType=INTEGER},",
          "to_id = #{toId,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "has_read = #{hasRead,jdbcType=INTEGER},",
          "conversation_id = #{conversationId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Message record);
    @Update({
        "update message",
        "set has_read = #{hasRead,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByHasRead(@Param("id") Integer id, @Param("hasRead") Integer hasRead);
}