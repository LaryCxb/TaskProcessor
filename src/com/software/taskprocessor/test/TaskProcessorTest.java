package com.software.taskprocessor.test;

import com.software.taskprocessor.Task;
import com.software.taskprocessor.TaskProcessor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskProcessorTest {

    Task task1 = new Task() {
        @Override
        public void execute() throws Exception {

        }

        @Override
        public int updateErrorNumber(int errorNumber) {
            return 0;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure() {

        }
    };
    Task task2 = new Task() {
        @Override
        public void execute() throws Exception {

        }

        @Override
        public int updateErrorNumber(int errorNumber) {
            return 0;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure() {

        }
    };

    @Test
    void addTaskTest() throws InterruptedException {
        LinkedBlockingQueue<? super Task> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add(task1);
        linkedBlockingQueue.add(task2);
        TaskProcessor.addTask(task1);
        TaskProcessor.addTask(task1);

        assertEquals(linkedBlockingQueue.take(), TaskProcessor.getLinkedBlockingQueue().take());
        assertNotEquals(linkedBlockingQueue.take(), TaskProcessor.getLinkedBlockingQueue().take());
    }
}