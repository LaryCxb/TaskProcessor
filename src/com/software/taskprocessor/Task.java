package com.software.taskprocessor;

public interface Task extends Callback<String> {

    void execute();
}
