package org.yixi.mallcore.task;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

@Component
public class TaskService {
    private TaskService taskService;

    private DelayQueue<Task> delayQueue = new DelayQueue<>();

    @PostConstruct
    private void init(){
        taskService = this;
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true){
                try {
                    Task task = delayQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addTask(Task task){
        if(delayQueue.contains(task)){
            return;
        }
        // 添加任务
        delayQueue.add(task);
    }

    public void removeTask(Task task){
        delayQueue.remove(task);
    }
}
