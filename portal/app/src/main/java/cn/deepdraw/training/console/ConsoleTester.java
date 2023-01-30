package cn.deepdraw.training.console;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConsoleTester {
	
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
    	
    	System.out.println("------当线程数达到之后，优先执行------");
    });

	public static void main(String[] args) throws InterruptedException {

		tryReentrantLockWithCondition();
//		tryCyclicBarrier();
	}
	
	public static void tryCyclicBarrier() throws InterruptedException {

	    int threadCount = 550;
		ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
            	
                System.out.println("threadNum:" + threadNum + "is ready");
				try {
					
				    cyclicBarrier.await();
				} catch (Exception e) {
					
				    System.out.println("-----CyclicBarrierException------");
				}
				System.out.println("threadNum:" + threadNum + "is finish");
            });
        }
        threadPool.shutdown();
    }

	public static void tryReentrantLockWithCondition() throws InterruptedException {
		

		ReentrantLock lock = new ReentrantLock();
		Condition conditi1 = lock.newCondition();
//		Condition conditi2 = lock.newCondition();
		AtomicInteger atomicItg = new AtomicInteger(0);
		Runnable runnable1 = new Runnable() {

			@Override
			public void run() {

				lock.lock();
				try {

					while (true) {

						Thread.sleep(1000);
						if (atomicItg.incrementAndGet() % 2 == 1) {

							System.out.println();
							System.out.println("runnable1: " + atomicItg.get());
//							conditi2.signal();
							conditi1.signal();
							conditi1.await();
							System.out.println("weakup1...");
						}
						System.out.println("running1...");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					lock.unlock();
				}
			}
		};

		Runnable runnable2 = new Runnable() {

			@Override
			public void run() {

				lock.lock();
				try {

					while (true) {

						Thread.sleep(1000);
						if (atomicItg.incrementAndGet() % 2 == 0) {

							System.out.println();
							System.out.println("runnable2: " + atomicItg.get());
							conditi1.signal();
//							conditi2.await();
							conditi1.await();
							System.out.println("weakup2...");
						}
						System.out.println("running2...");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					lock.unlock();
				}
			}
		};

		new Thread(runnable1).start();
		new Thread(runnable2).start();

		Thread.sleep(3000);
	}
}