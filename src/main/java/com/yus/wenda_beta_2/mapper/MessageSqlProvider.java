package com.yus.wenda_beta_2.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.yus.wenda_beta_2.pojo.Message;
import com.yus.wenda_beta_2.pojo.MessageExample.Criteria;
import com.yus.wenda_beta_2.pojo.MessageExample.Criterion;
import com.yus.wenda_beta_2.pojo.MessageExample;
import java.util.List;
import java.util.Map;

public class MessageSqlProvider {

    public String countByExample(MessageExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("message");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MessageExample example) {
        BEGIN();
        DELETE_FROM("message");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Message record) {
        BEGIN();
        INSERT_INTO("message");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getFromId() != null) {
            VALUES("from_id", "#{fromId,jdbcType=INTEGER}");
        }
        
        if (record.getToId() != null) {
            VALUES("to_id", "#{toId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            VALUES("created_date", "#{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHasRead() != null) {
            VALUES("has_read", "#{hasRead,jdbcType=INTEGER}");
        }
        
        if (record.getConversationId() != null) {
            VALUES("conversation_id", "#{conversationId,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExampleWithBLOBs(MessageExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("from_id");
        SELECT("to_id");
        SELECT("created_date");
        SELECT("has_read");
        SELECT("conversation_id");
        SELECT("content");
        FROM("message");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String selectByExample(MessageExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("from_id");
        SELECT("to_id");
        SELECT("created_date");
        SELECT("has_read");
        SELECT("conversation_id");
        FROM("message");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Message record = (Message) parameter.get("record");
        MessageExample example = (MessageExample) parameter.get("example");
        
        BEGIN();
        UPDATE("message");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getFromId() != null) {
            SET("from_id = #{record.fromId,jdbcType=INTEGER}");
        }
        
        if (record.getToId() != null) {
            SET("to_id = #{record.toId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHasRead() != null) {
            SET("has_read = #{record.hasRead,jdbcType=INTEGER}");
        }
        
        if (record.getConversationId() != null) {
            SET("conversation_id = #{record.conversationId,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("message");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("from_id = #{record.fromId,jdbcType=INTEGER}");
        SET("to_id = #{record.toId,jdbcType=INTEGER}");
        SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        SET("has_read = #{record.hasRead,jdbcType=INTEGER}");
        SET("conversation_id = #{record.conversationId,jdbcType=VARCHAR}");
        SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        
        MessageExample example = (MessageExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("message");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("from_id = #{record.fromId,jdbcType=INTEGER}");
        SET("to_id = #{record.toId,jdbcType=INTEGER}");
        SET("created_date = #{record.createdDate,jdbcType=TIMESTAMP}");
        SET("has_read = #{record.hasRead,jdbcType=INTEGER}");
        SET("conversation_id = #{record.conversationId,jdbcType=VARCHAR}");
        
        MessageExample example = (MessageExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Message record) {
        BEGIN();
        UPDATE("message");
        
        if (record.getFromId() != null) {
            SET("from_id = #{fromId,jdbcType=INTEGER}");
        }
        
        if (record.getToId() != null) {
            SET("to_id = #{toId,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedDate() != null) {
            SET("created_date = #{createdDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getHasRead() != null) {
            SET("has_read = #{hasRead,jdbcType=INTEGER}");
        }
        
        if (record.getConversationId() != null) {
            SET("conversation_id = #{conversationId,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(MessageExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}