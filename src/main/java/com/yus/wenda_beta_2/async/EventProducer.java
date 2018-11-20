package com.yus.wenda_beta_2.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yus.wenda_beta_2.utils.JedisAdapter;
import com.yus.wenda_beta_2.utils.RedisKeyUtil;

@Service
public class EventProducer {
	
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel) {
        try {
            String json = JSONObject.toJSONString(eventModel);
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
