package ru.javawebinar.topjava.service;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StopWatchTest extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(StopWatchTest.class);
    public static final StringBuilder testInfo = new StringBuilder();

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        log.info(String.format("Test %s %s, spent %d milliseconds", testName, status, TimeUnit.NANOSECONDS.toMillis(nanos)));
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        testInfo.append("Test: ")
                .append(description.getMethodName())
                .append(", spent ")
                .append(TimeUnit.NANOSECONDS.toMillis(nanos))
                .append(" millis\n");
        logInfo(description, "finished", nanos);
    }
}
