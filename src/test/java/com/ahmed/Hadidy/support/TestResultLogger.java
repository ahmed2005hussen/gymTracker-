package com.ahmed.Hadidy.support;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

/** Prints a concise result for every test that uses this extension. */
public class TestResultLogger implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("[PASS] " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("[FAIL] " + context.getDisplayName() + " - " + cause.getMessage());
    }
}
