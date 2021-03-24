package com.test.support;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import java.util.HashMap;
import java.util.Map;

public class TestExecListener implements TestExecutionListener {
    private Map<String, TestExecutionResult.Status> summary = new HashMap<>();

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        summary.put(testIdentifier.getDisplayName(), testExecutionResult.getStatus());
        for (Map.Entry<String, TestExecutionResult.Status> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
