package com.java8;

import com.spring5.controller.simpleTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.annotation.Resource;
import java.beans.ConstructorProperties;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 老的dataformat存在线程安全问题，多个线程同时调用dataformat会抛出异常
 */
public class NewDate {
    /**
     * 通过org.springframework.core.env.AbstractPropertyResolver#resolveRequiredPlaceholders去解析
     */
    @Value("${newDate.year}")
    private int year;
    private int month;
@Autowired
        @Resource
    simpleTest simpleTes;
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * java.beans.ConstructorProperties自己定义构造内成员变量的属性名，不受限于入参名，
     * 对应的setter，getter也是根据注解内设置的属性来匹配
     * @param y
     * @param m
     */
    @ConstructorProperties({"year","month"})
    public NewDate(int y, int m) {

        this.year = year;
        this.month = month;
    }

    public NewDate() {
        System.out.println("newDate");
    }

    /**
     * java的注解，原本是在servlet的init方法执行前调用，springboot管理下，在applyBeanPostProcessorsBeforeInitialization中执行，
     * 调用之后,在ServletWebServerApplicationContext#startWebServer中调用servlet的init方法
     *
     */
    @PostConstruct
    public void PostConstruct(){
        System.out.println();
    }
    public void initNewDate(){
        System.out.println();
    }
    public void destroyNewDate(){
        System.out.println();
    }
    @anno("1")
    @anno("2")
    public void say() {
        System.out.println("say");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        /**这是老的实现方式，存在线程不安全的原因是，{@link DecimalFormat#digitList}作为一个成员变量被多个线程持有，
         * 导致造成不正常的修改，发生解析错误，抛出异常，例如：
         * Caused by: java.lang.NumberFormatException: For input string: "121.E1212E2"
         at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2043)
         at sun.misc.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
         at java.lang.Double.parseDouble(Double.java:538)
         at java.text.DigitList.getDouble(DigitList.java:169)   //这里拼接的解析数据
         at java.text.DecimalFormat.parse(DecimalFormat.java:2089)
         at java.text.SimpleDateFormat.subParse(SimpleDateFormat.java:2162)
         at java.text.SimpleDateFormat.parse(SimpleDateFormat.java:1514)
         at java.text.DateFormat.parse(DateFormat.java:364)
         at com.java8.NewDate$1.call(NewDate.java:27)
         *
         */
        //  oldDataFormat();
        /**
         * java8,新的localDate（day,month,year），localTime(hour,minutes,seconds,nano)，LocalDateTime(localDate,localTime)
         * 包括各自内部的成员变量都是 final的
         */
        //   newDataFormat();
        LocalDateTime ld2 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
        System.out.println(ld2.plusDays(1));
        System.out.println(ld2.withDayOfMonth(30));
        System.out.println(ld2.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(ld2.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
        Instant now = Instant.now();    //时间戳
        System.out.println(now.toEpochMilli());
        System.out.println(now.getEpochSecond());
        System.out.println(now.getNano());

        Duration duration = Duration.between(now, Instant.now());
        System.out.println(duration.toMillis());
        Period period = Period.between(LocalDate.now(), LocalDate.now().plusDays(3));
        System.out.println(period.getDays());

        System.out.println(LocalDateTime.now(ZoneId.of("Africa/Harare")));  //设置时区
        System.out.println(LocalDateTime.now().atZone(ZoneId.of("Africa/Harare")));//输出日期带有时区显示，和时差

        /**
         * {@link Repeatable}
         */
        new NewDate().testRepeatable();


    }

    private  void testRepeatable() throws NoSuchMethodException {
        Class<? extends NewDate> aClass = this.getClass();
        Method say = aClass.getMethod("say", null);
        say.getDeclaredAnnotations();//返回annos的容器对象，里面存储了多个anno
        System.out.println(say.getAnnotationsByType(anno.class)[0].value());//一个anno的value
    }

    private static void oldDataFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                return sdf.parse("20161121");
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }

    private static void newDataFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {

            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20161121", dateTimeFormatter);
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
