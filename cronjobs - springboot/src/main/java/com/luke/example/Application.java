package com.luke.example;

import com.luke.example.config.Properties;
import com.luke.example.task.CheckTimeTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 * Created by nvnhung on 05/26/2017.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        boolean runInAutoMode = false;

        if (args.length== 0) {
            showError();
            return;
        }
        switch (args[0]) {
            case "0":
                runInAutoMode=true;
                break;
            case "1":
                runInAutoMode=false;
                break;
            default:
                showError();
                return;
        }
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        if (runInAutoMode) {
            runInAutoMode(context);
        } else {
            runInManualMode(context, args[1]);
        }

    }

    private static void showError(){
        System.out.println("There are no option like your command, Please follow the parameters!");
        System.out.println("0/1 taskName");
        System.out.println("0 : Run In Auto Mode");
        System.out.println("1 : Run In Manually Mode With taskName");
    }

    private static void runInAutoMode(ApplicationContext context) {
        System.out.println("Run in Auto Mode");
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Properties properties = context.getBean(Properties.class);
        scheduler.schedule(() -> context.getBean(CheckTimeTask.class).reportCurrentTime(), new CronTrigger(properties.getCheckTimeCron()));
    }

    private static void runInManualMode(ApplicationContext context, String option) {
        System.out.println("Run in Manual Mode");
        switch (option) {
            case "checkTime":
                context.getBean(CheckTimeTask.class).reportCurrentTime();
                break;
        }
    }

}