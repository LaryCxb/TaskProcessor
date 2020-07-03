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
            Task task = TaskProcessor.linkedBlockingQueue.poll();
            Callback<String> callback = new Callback<>() {
                @Override
                public String onStart() {
                    return "onStart";
                }

                @Override
                public String onSuccess() {
                    return "onSuccess";
                }

                @Override
                public String onFailure() {
                    return "onFailure";
                }
            };
            callback.onStart();
            if (task.execute()) {
                callback.onSuccess();
            }
            else {
                callback.onFailure();
            }
        } catch (NullPointerException e) {
            System.out.println("No task in queue");
        }
    }
}
