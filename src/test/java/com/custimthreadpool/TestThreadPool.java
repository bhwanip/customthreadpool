package com.custimthreadpool;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

import com.customthreadpool.ThreadPool;

public class TestThreadPool {

	private static final int THREAD_POOL_SIZE = 10;
	private static final int TASK_COUNT = 50;

	@Test
	public void test() throws InterruptedException {
		CountDownLatch taskCountLatch = new CountDownLatch(TASK_COUNT);
		ThreadPool pool = new ThreadPool(THREAD_POOL_SIZE);
		for (int i = 0; i < TASK_COUNT; i++) {
			Runnable task = new TestRunnableTask(taskCountLatch);
			pool.execute(task);
		}
		if(!taskCountLatch.await(30, TimeUnit.SECONDS)){
			fail("Not all tasks yet completed, tasks pending: " + taskCountLatch.getCount());
		}
		assertThat(pool.getTasksQue().size(), is(0));

	}

}
