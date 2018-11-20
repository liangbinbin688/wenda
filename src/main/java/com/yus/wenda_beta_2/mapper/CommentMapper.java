package com.yus.wenda_beta_2.mapper;

import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.CommentExample;
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

public interface CommentMapper {
    @SelectProvider(type=CommentSqlProvider.class, method="countByExample")
    int countByExample(CommentExample example);

    @DeleteProvider(type=CommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(CommentExample example);

    @Delete({
        "delete from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into comment (id, user_id, ",
        "entity_id, entity_type, ",
        "created_date, status, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{entityId,jdbcType=INTEGER}, #{entityType,jdbcType=INTEGER}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Comment record);

    @InsertProvider(type=CommentSqlProvider.class, method="insertSelective")
    int insertSelective(Comment record);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_id", property="entityId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_type", property="entityType", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_id", property="entityId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_type", property="entityType", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Comment> selectByExample(CommentExample example);

    @Select({
        "select",
        "id, user_id, entity_id, entity_type, created_date, status, content",
        "from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_id", property="entityId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_type", property="entityType", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Comment selectByPrimaryKey(Integer id);
    
    @Select({
    	"select",
    	"count(id) from comment",
    	"where entity_id=#{entityId} and entity_type=#{entityType}"
    })
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
    
    
    @Select({
        "select",
        "id, user_id, entity_id, entity_type, created_date, status, content",
        "from comment",
        "where entity_id=#{entityId} and entity_type=#{entityType} order by id desc"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_id", property="entityId", jdbcType=JdbcType.INTEGER),
        @Result(column="entity_type", property="entityType", jdbcType=JdbcType.INTEGER),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update comment",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "entity_id = #{entityId,jdbcType=INTEGER},",
          "entity_type = #{entityType,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Comment record);
   
    @Update({
        "update comment",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "entity_id = #{entityId,jdbcType=INTEGER},",
          "entity_type = #{entityType,jdbcType=INTEGER},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
    
    
    @Update({
        "update comment",
        "set status = #{status,jdbcType=INTEGER},",
          "where entity_id=#{entityId} and entity_type=#{entityType}"
    })
    void updateStatus(@Param("entityId") int entityId, @Param("entityType") int entityType, @Param("status") int status);
}