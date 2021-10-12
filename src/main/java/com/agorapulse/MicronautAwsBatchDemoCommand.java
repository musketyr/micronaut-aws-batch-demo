package com.agorapulse;

import io.micronaut.configuration.picocli.PicocliRunner;

import io.micronaut.context.env.Environment;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.Instant;

@Command(name = "micronaut-aws-batch-demo", description = "AWS Batch Demo", mixinStandardHelpOptions = true)
public class MicronautAwsBatchDemoCommand implements Runnable {

    @Option(names = {"-t", "--timestamp"}, description = "the time when the event was triggered")
    Instant timestamp;

    @Inject Environment environment;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(MicronautAwsBatchDemoCommand.class, args);
    }

    public void run() {
        System.out.printf("Event sent at %s to the environments %s", timestamp, environment.getActiveNames());
    }
}
