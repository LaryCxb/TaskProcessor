package com.software.taskprocessor;

public interface Callback<T> {

    T onStart();
    T onSuccess();
    T onFailure();
}
