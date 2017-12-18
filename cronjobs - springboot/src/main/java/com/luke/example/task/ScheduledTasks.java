//package ch.finform.task;
//
//import Properties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
///**
// * Created by nvnhung on 05/26/2017.
// * This is another way for running auto task instead of using TaskScheduler
// */
//@Component
//public class ScheduledTasks  {
//
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Autowired
//    Properties properties;
//
//    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
//    public void sampleFixedRateTask(){
////        reportCurrentTime();
//    }
//    @Scheduled(cron = "${cron.expression}")
//    public void sampleCronTask(){
//        log.info(properties.getCheckTimeCron()+" {}", dateFormat.format(new Date()));
//    }
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }
//
//}
