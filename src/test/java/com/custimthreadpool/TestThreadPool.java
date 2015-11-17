package com.custimthreadpool;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.customthreadpool.CustomLatch;
import com.customthreadpool.ThreadPool;

public class TestThreadPool {

	private static final int THREAD_POOL_SIZE = 10;
	private static final int TASK_COUNT = 50;

	@Test
	public void test() throws InterruptedException {
		CustomLatch taskCountLatch = new CustomLatch(TASK_COUNT);
		ThreadPool pool = new ThreadPool(THREAD_POOL_SIZE);
		for (int i = 0; i < TASK_COUNT; i++) {
			Runnable task = new TestRunnableTask(taskCountLatch);
			pool.execute(task);
		}
		if(!taskCountLatch.await(30*1000)){
			fail("Not all tasks yet completed, tasks pending: " + taskCountLatch.getCount());
		}
		assertThat(pool.getTasksQue().size(), is(0));

	}

}
