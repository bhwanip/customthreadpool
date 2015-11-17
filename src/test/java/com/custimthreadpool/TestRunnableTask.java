package com.custimthreadpool;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRunnableTask implements Runnable {
	
	private final static Logger logger = LoggerFactory.getLogger(TestRunnableTask.class);

	public TestRunnableTask(CountDownLatch taskCountLatch) {
		super();
		this.taskCountLatch = taskCountLatch;
	}

	private final CountDownLatch taskCountLatch;
	
	public void run() {
		logger.info("Before Countdown: " + taskCountLatch.getCount());
		taskCountLatch.countDown();
		logger.info("After Countdown: " + taskCountLatch.getCount());
	}

}
