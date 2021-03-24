package com.test.support;

/*

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

import com.test.stepdefs.secondIT;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

public class RerunLauncher {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Discover and filter tests
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(select)
                .selectors(selectPackage("com.test.stepdefs"),
                        selectClass(secondIT.class))
                .filters(includeClassNamePatterns(".*IT")).build();

       
		
        Launcher launcher = LauncherFactory.create();
        TestPlan plan = launcher.discover(request);

        // Executing tests
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request, listener);
    }

} */

 