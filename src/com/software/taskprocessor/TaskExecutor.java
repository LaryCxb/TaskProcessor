package com.software.taskprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskExecutor implements Runnable {

    private final Task task;
    private final int repetitions;
    private final long sleepTime;
    private final int repetition;
    Logger logger = LoggerFactory.getLogger(getClass());

    public TaskExecutor(Task task, int repetitions, long sleepTime, int repetition) {
        this.task = task;
        this.repetitions = repetitions;
        this.sleepTime = sleepTime;
        this.repetition = repetition;
    }

    @Override
    public void run() {
        waitIfNotFirst();
        execute(task);
    }

    private void execute(Task task) {
        try {
            task.onStart();
            task.execute();
            task.onSuccess();
        } catch (Exception e) {
            logger.debug(e.getMessage());
            task.updateErrorNumber(repetition + 1);
            task.onFailure();
            if (repetitions - repetition > 0) {
                Thread executor = new Thread(new TaskExecutor(task, repetitions, sleepTime, repetition + 1));
                executor.start();
            }
        }
    }

    private void waitIfNotFirst() {
        if (repetition != 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                logger.warn("Waiting for repetition interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}
