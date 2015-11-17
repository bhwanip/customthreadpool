package com.custimthreadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.customthreadpool.CustomLatch;

public class TestRunnableTask implements Runnable {
	
	private final static Logger logger = LoggerFactory.getLogger(TestRunnableTask.class);

	public TestRunnableTask(CustomLatch taskCountLatch) {
		super();
		this.taskCountLatch = taskCountLatch;
	}

	private final CustomLatch taskCountLatch;
	
	public void run() {
		logger.info("Before Countdown: " + taskCountLatch.getCount());
		taskCountLatch.countDown();
		logger.info("After Countdown: " + taskCountLatch.getCount());
	}

}
