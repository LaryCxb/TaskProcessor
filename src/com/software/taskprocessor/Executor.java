package com.software.taskprocessor;

public class Executor implements Runnable {

    @Override
    public void run() {
        while (true) {
            execute();
        }
    }

    private void execute() {
        try {
            Task task = (Task) TaskProcessor.linkedBlockingQueue.take();
            task.execute();
        } catch (InterruptedException e) {
            System.out.println("Waiting for task interrupted");
        }
    }
}
