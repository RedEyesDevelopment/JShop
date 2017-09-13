package com.github.redeyesdevelopment.message_processor;

import com.hazelcast.core.IQueue;
import  com.github.redeyesdevelopment.messages.*;

public class MessageProcessor {

    private class MessageListener implements Runnable {
        private IQueue queue;

        @Override
        public void run() {
            while (true) {
                if (!queue.isEmpty()) {
                    Message message = (Message) queue.peek();
                }
            }
        }
    }
}
