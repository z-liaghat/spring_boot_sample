package com.example.springbootsample.service;

import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import static com.cronutils.model.CronType.QUARTZ;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service
public class CronSchedulerServiceImpl implements CronSchedulerService {

    @Value("${schedule.interval}")
    private int interval;

    @Value("${schedule.repetition}")
    private int repetition;

    private static final String timePattern = "yyyy-MM-dd_HH:mm";

    @Override
    public List<String> computeCron(String startingTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
        LocalDateTime localDateTime = LocalDateTime.parse(startingTime, formatter);

        ZonedDateTime now = ZonedDateTime.now();
        ZoneId zoneId = now.getZone();
        ZonedDateTime startTime = ZonedDateTime.of(localDateTime, zoneId);
        String cronExpression = String.format("* */%d * * * ?", interval);
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(cronExpression));

        // Get date for next execution
        Optional<ZonedDateTime> nextExecutionTime = executionTime.nextExecution(startTime);

        List<String> intervalArrayList = new ArrayList<>();
        intervalArrayList.add(startTime.format(formatter));
        ZonedDateTime nextTime;

        if (nextExecutionTime.isPresent()) {
            nextTime = nextExecutionTime.get();
            intervalArrayList.add(nextTime.format(formatter));
            int compareResult =nextTime.compareTo(startTime);
            int index = 2;
            if (compareResult == 0){
                intervalArrayList.remove(1);
                index = 1;
            }
            for (; index <= repetition; index++){
                nextTime =nextTime.plusMinutes(interval);
                intervalArrayList.add(nextTime.format(formatter));
            }

        }

        int x = interval + repetition;
        return intervalArrayList;
    }


}
