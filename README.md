# TaskProcessor

Library for multi-thread executing client's tasks for defined (by a client)
number of times until success. Each repetition starts after defined time.

## Using

* Create class implementing Task from com.software.taskprocessor
* Start TaskProcessor:
````
TaskProcessor.start(int repetitions, long time)
````
* Add task:
````
TaskProcessor.addTask(Task task);
````

## Built with
Java