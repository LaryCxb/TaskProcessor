package com.software.taskprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor implements Runnable {

    private final int repetitions;
    private int repetitionsLeft;
    private final long sleepTime;
    Logger logger = LoggerFactory.getLogger(getClass());

    public Executor(int repetitions, long sleepTime) {
        this.repetitions = repetitions;
        this.repetitionsLeft = repetitions;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            repetitionsLeft = repetitions;
            try {
                Task task = (Task) TaskProcessor.getLinkedBlockingQueue().take();
                execute(task);
            } catch (InterruptedException e) {
                logger.warn("Waiting for task interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void execute(Task task) {
        try {
            task.onStart();
            task.execute();
            task.onSuccess();
        } catch (Exception e) {
            logger.debug(e.getMessage());
            task.updateErrorNumber(repetitions - repetitionsLeft + 1);
            task.onFailure();
            if (repetitionsLeft > 0) {
                waitAndRepeat(task);
            }
        }
    }

    private void waitAndRepeat(Task task) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.warn("Waiting for repetition interrupted");
            Thread.currentThread().interrupt();
        }
        repetitionsLeft--;
        execute(task);
    }

}
