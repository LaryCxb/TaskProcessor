package com.software.taskprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executor implements Runnable, Callback {

    private int repetitionsLeft;
    private final long sleepTime;
    private int errorNumber = 0;
    Logger logger = LoggerFactory.getLogger(getClass());

    public Executor(int repetitions, long sleepTime) {
        this.repetitionsLeft = repetitions;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
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
            onStart();
            task.execute();
            onSuccess();
        } catch (Exception e) {
            logger.debug(e.getMessage());
            onFailure();
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

    @Override
    public void onStart() {
        logger.info("START");
    }

    @Override
    public void onSuccess() {
        logger.info("SUCCESS after {} errors", errorNumber);
        errorNumber = 0;
    }

    @Override
    public void onFailure() {
        errorNumber++;
        logger.info("FAILURE #{}", errorNumber);
    }
}
