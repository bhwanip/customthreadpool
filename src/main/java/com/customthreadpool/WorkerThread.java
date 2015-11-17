package com.customthreadpool;

import java.util.List;

public class WorkerThread extends Thread {

	private List<Runnable> tasksQue;

	public WorkerThread(List<Runnable> tasksQue) {
		this.tasksQue = tasksQue;
	}

	@Override
	public void run() {
		Runnable task;
		while(true){
			synchronized (tasksQue) {
				if(tasksQue.isEmpty())
					try {
						tasksQue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				task = tasksQue.remove(0);
			}
			task.run();
		}
	}

}
