package com.yus.wenda_beta_2.mapper;

import com.yus.wenda_beta_2.pojo.Ticket;
import com.yus.wenda_beta_2.pojo.TicketExample;
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

public interface TicketMapper {
    @SelectProvider(type=TicketSqlProvider.class, method="countByExample")
    int countByExample(TicketExample example);

    @DeleteProvider(type=TicketSqlProvider.class, method="deleteByExample")
    int deleteByExample(TicketExample example);

    @Delete({
        "delete from login_ticket",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into login_ticket (id, user_id, ",
        "ticket, expired, ",
        "status)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{ticket,jdbcType=VARCHAR}, #{expired,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER})"
    })
    int insert(Ticket record);

    @InsertProvider(type=TicketSqlProvider.class, method="insertSelective")
    int insertSelective(Ticket record);

    @SelectProvider(type=TicketSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="expired", property="expired", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Ticket> selectByExample(TicketExample example);

    @Select({
        "select",
        "id, user_id, ticket, expired, status",
        "from login_ticket",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="expired", property="expired", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    Ticket selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "id, user_id, ticket, expired, status",
        "from login_ticket",
        "where ticket = #{ticket,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="expired", property="expired", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    Ticket selectByTicket(String ticket);
    

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    @UpdateProvider(type=TicketSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Ticket record);

    @Update({
        "update login_ticket",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "ticket = #{ticket,jdbcType=VARCHAR},",
          "expired = #{expired,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Ticket record);
    
    @Update({
        "update login_ticket",
        "set status = #{1}",
        "where  ticket= #{0}"
    })
    int updateByTicket(String ticket,int status);
}