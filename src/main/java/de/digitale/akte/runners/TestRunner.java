package de.digitale.akte.runners;

import io.cucumber.core.cli.Main;

public final class TestRunner {

    public static void main(String[] args) {

        SecurityManager manager = new IgnoreExitCall();
        System.setSecurityManager(manager);
        try {
            Main.main(
                    "classpath:features",
                    "-t", "@login",
                    "-g", "de.digitale.akte.stepDefs",
                    "-p", "pretty",
                    "-p", "json:target/cucumber-reports/Cucumber.json",
                    "-p", "html:target/myReports/default-html-reports",
                    "-m");
        } catch (SecurityException securityException) {
            System.out.println("Ignore exit");
        }
        //use that command in gitignore file to run jar file from command line
    }
}

