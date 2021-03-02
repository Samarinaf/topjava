package ru.javawebinar.topjava.service;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StopWatchRule extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(StopWatchRule.class);
    private static final StringBuilder testInfo = new StringBuilder("\n");

    public static void logClassInfo() {
        log.info(testInfo.toString());
    }

    @Override
    protected void finished(long nanos, Description description) {
        testInfo.append(String.format("%-30s %d ms\n", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos)));
        logMethodInfo(description, nanos);
    }

    private static void logMethodInfo(Description description, long nanos) {
        String testName = description.getMethodName();
        log.info("{} {} ms\n", testName, TimeUnit.NANOSECONDS.toMillis(nanos));
    }
}
