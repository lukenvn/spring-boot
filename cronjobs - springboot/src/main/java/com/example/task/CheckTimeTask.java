package com.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nvnhung on 05/26/2017.
 */
@Component
public class CheckTimeTask implements Task {

    private static final Logger log = LoggerFactory.getLogger(CheckTimeTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    public void run() {
        reportCurrentTime();
    }
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
