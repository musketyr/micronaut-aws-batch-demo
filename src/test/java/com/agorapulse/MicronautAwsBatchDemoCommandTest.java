package com.agorapulse;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MicronautAwsBatchDemoCommandTest {

    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[] { "-t", Instant.now().toString() };
            PicocliRunner.run(MicronautAwsBatchDemoCommand.class, ctx, args);

            // micronaut-aws-batch-demo
            String output = baos.toString();
            assertTrue(output.contains("Event sent at 20"));
            assertTrue(output.contains("to the environments [cli, test]"));
        }
    }
}
