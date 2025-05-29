package org.example.task_2;

public class MainTask2 {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        Worker worker = new Worker(listener);
        worker.start();
    }
}
