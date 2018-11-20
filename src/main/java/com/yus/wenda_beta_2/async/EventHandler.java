package com.yus.wenda_beta_2.async;

import java.util.List;

public interface EventHandler {
	
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();

}
