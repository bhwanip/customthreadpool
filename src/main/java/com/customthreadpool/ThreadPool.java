package com.customthreadpool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

	final int threadCount;
	final LinkedList<Runnable> tasksQue;
	final Thread[] threads;

	public ThreadPool(int threadCount) {
		super();
		this.threadCount = threadCount;
		tasksQue = new LinkedList<Runnable>();
		threads = new Thread[threadCount];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread(tasksQue);
			threads[i].setName(WorkerThread.class.getName() + i);
			threads[i].start();

		}
		logger.info("Launched Thread pool with threadCount = " + threadCount);
	}

	public void execute(Runnable task){
		synchronized (tasksQue) {
			tasksQue.addLast(task);
			tasksQue.notifyAll();
		}
	}

	public List<Runnable> getTasksQue() {
		return Collections.unmodifiableList(tasksQue);
	}

}
