# TaskProcessor

Library for multi-thread executing client's tasks for defined (by a client)
number of times until success. Each repetition starts after defined time.

## Using

* Create class implementing Task from com.software.taskprocessor
* Start TaskProcessor:
````
TaskProcessor.start(int repetitions, long time)
````
TaskProcessor will start executing tasks from its queue.

* Add task:
````
TaskProcessor.addTask(Task task);
````
Your task will be added to TaskProcessor's queue. 

TaskProcessor will take added task from queue and will start task's execution in new thread. If execution fails, TaskProcessor will start another thread which will begin the task execution after defined time. TaskProcessor will make defined by user amount of attempts of execution.

## Built with
Java
