package com.dayuan.atm.text;

import com.dayuan.atm.service.CardService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

//并发测试和controller没有关系，所以只要测试service接口即可
public class SpringTest {

    //并发测试线程数
    public static final int MAX_THREAD = 300;

    //并发测试工具-栅栏
    //栅栏类似于闭锁，它能阻塞一组线程直到某个事件的发生。栅栏与闭锁的关键区别在于，所有的线程必须同时到达栅栏位置，才能继续执行。闭锁用于等待事件，而栅栏用于等待其他线程。
    public static final CyclicBarrier cb = new CyclicBarrier(MAX_THREAD);
    //CountDownLatch能够使一个或多个线程等待其他线程完成各自的工作后再执行；MAX_THREAD：计数器；CountDownLatch是JDK 5+里面闭锁的一个实现
    public static final CountDownLatch cd = new CountDownLatch(MAX_THREAD);

    //原子整形类，并赋予初始值0
    public static final AtomicInteger success2 = new AtomicInteger(0);
    public static final AtomicInteger error2 = new AtomicInteger(0);


    public static int success = 0;
    public static int error = 0;

    public static void main(String[] args) throws Exception{

        //解析spring.XML，否则空指针，classpath指的是WEB-INF下面的classes目录，spring.xml文件放在resources里，resources里的文件编译后会存放在WEB-INF下面的classes目录
        //IO容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        //获取service
        CardService cardService = context.getBean(CardService.class);
        MyRunnable myRunnable = new MyRunnable(cardService);

        for (int i = 0; i < MAX_THREAD; i++) {
            Thread thread = new Thread(myRunnable);
            thread.start();
        }

        //一直阻塞当前线程，直到计数器的值为0，除非线程被中断
        cd.await();

        //得到成功、失败数量
        System.out.println("success:" + success2.get());
        System.out.println("error" + error2.get());
    }

    //通过实现Runnable接口实现线程
    static class MyRunnable implements Runnable {
        CardService cardService;

        public MyRunnable(CardService cardService) {
            this.cardService = cardService;
        }

        @Override
        public void run() {
            try {
                //表示等待所有线程全部到达run（）时，再一起执行run（）
                cb.await();
                cardService.deposit(1033,"1","0.01",1000);
                //++、--线程不安全
                //success++;
                success2.getAndAdd(1);
            } catch (Exception e) {
                e.printStackTrace();
                //error++;
                error2.getAndAdd(1);
            }
            //线程执行完一个，计数减一，相当于CountDownLatch减一
            cd.countDown();
        }
    }

}
