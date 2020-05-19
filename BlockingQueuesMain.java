package com.manager.parking;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueuesMain {
	public static void main(String... args) {
		BlockingQueue blockQ = new BlockingQueue(2);

		try {
			Thread t1 = new Thread(new EneuqueThread(blockQ, 10));
			Thread t1a = new Thread(new EneuqueThread(blockQ, 30));
			Thread t1b = new Thread(new EneuqueThread(blockQ, 50));
			Thread t1c = new Thread(new EneuqueThread(blockQ, 40));

			Thread t2 = new Thread(new EneuqueThread(blockQ, 20));
			Thread t3 = new Thread(new DequeueThread(blockQ));
			Thread t4 = new Thread(new DequeueThread(blockQ));
			t1.start();
			t3.start();
			t4.start();

			Thread.sleep(3000);
			t1a.start();
			t1b.start();
			t1c.start();

			t2.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class BlockingQueue {
	private List<Object> queue = new LinkedList<>();
	private int limit = 10;

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			System.out.println("enq wait");
			wait();
		}
		notifyAll();
		/*
		 * if (this.queue.size() == 1) { notifyAll(); }
		 */
		System.out.println("item enq " + item);
		this.queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			System.out.println("deq wait");
			wait();
		}
		notifyAll();

		/*
		 * if (this.queue.size() == this.limit) { notifyAll(); }
		 */
		System.out.println("item deq " + queue.get(0));

		return this.queue.remove(0);
	}

}

class EneuqueThread implements Runnable {
	BlockingQueue obj;
	int num;

	EneuqueThread(BlockingQueue q, int num) {
		this.obj = q;
		this.num = num;
	}

	public void run() {
		try {
			obj.enqueue(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class DequeueThread implements Runnable {
	BlockingQueue obj;

	DequeueThread(BlockingQueue q) {
		this.obj = q;
	}

	public void run() {
		try {
			obj.dequeue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
