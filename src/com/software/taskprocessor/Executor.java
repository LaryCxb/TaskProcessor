package com.software.taskprocessor;

public class Executor implements Runnable, Callback {

    private int repetitionsLeft;
    private final long sleepTime;

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
                System.out.println("Waiting for task interrupted");
            }
        }
    }

    private void execute(Task task) {
        try {
            onStart();
            task.execute();
            onSuccess();
        } catch (Exception e) {
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
            System.out.println("Waiting for repetition interrupted");
            Thread.currentThread().interrupt();
        }
        repetitionsLeft--;
        execute(task);
    }

    @Override
    public void onStart() {
        System.out.println("START");
    }

    @Override
    public void onSuccess() {
        System.out.println("SUCCESS");
    }

    @Override
    public void onFailure() {
        System.out.println("FAILURE");
    }
}
