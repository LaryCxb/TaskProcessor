package com.software.taskprocessor.test;

import com.software.taskprocessor.Task;
import com.software.taskprocessor.TaskProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskProcessorTest {

    static FakedTask task1;
    static FakedTask task2;
    LinkedBlockingQueue<? super Task> linkedBlockingQueue;

    @BeforeAll
    public static void setupClass() {
        task1 = new FakedTask();
        task2 = new FakedTask();
    }

    @BeforeEach
    public void setup() {
        linkedBlockingQueue = new LinkedBlockingQueue<>();
    }

    @Test
    void shouldEqualsWhenSameAdded() throws InterruptedException {
        //given

        //when
        linkedBlockingQueue.add(task1);
        TaskProcessor.addTask(task1);

        //then
        assertEquals(linkedBlockingQueue.take(), TaskProcessor.getLinkedBlockingQueue().take());
    }

    @Test
    void shouldNotEqualsWhenDifferentAdded() throws InterruptedException {
        //given

        //when
        linkedBlockingQueue.add(task1);
        TaskProcessor.addTask(task2);

        //then
        assertNotEquals(linkedBlockingQueue.take(), TaskProcessor.getLinkedBlockingQueue().take());
    }

}