package com.github.redeyesdevelopment.messages;

public interface BroadcastMessage extends Message {
    void markAsRead(String consumerName);
    boolean excludeIfReadByAll(String[] consumers);
}
