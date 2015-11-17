package com.customthreadpool;

public class CustomLatch {

	private int count;

	public CustomLatch(int count) {
		super();
		this.count = count;
	}


	public synchronized void await() throws InterruptedException{
		while(count > 0){
			this.wait();
		}
	}

	public synchronized boolean await(long timeout) throws InterruptedException{
		if(count > 0){
			this.wait(timeout);
			if(count == 0) {
				return true;
			}
		}
		return false;
	}


	public synchronized void countDown(){
		this.count--;
		if(this.count == 0){
			this.notifyAll();
		}
	}


	public int getCount() {
		return count;
	}


}
