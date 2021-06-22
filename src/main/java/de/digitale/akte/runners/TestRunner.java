package de.digitale.akte.runners;

import io.cucumber.core.cli.Main;

public final class TestRunner {

    public static void main(String[] args) {

        SecurityManager manager = new IgnoreExitCall();
        System.setSecurityManager(manager);
        try {
            Main.main(
                    "classpath:features",
                    "-t", "@all",
                    "-g", "de.digitale.akte.stepDefs",
                    "-p", "pretty",
                    "-p", "json:target/cucumber-reports/Cucumber.json",
                    "-p", "html:target/myReports/default-html-reports",
                    "-m");
        } catch (SecurityException securityException) {
            System.out.println("Ignore exit");
        }
        //use this command to run jar file from command line--> java -jar fileName.jar
        //If you want to specify tags or other parameters, tailor following command. Browser must be defined before filename, others after
        //java -Dbrowser=chrome -jar File_Name.jar --glue cucumber/stepdefinition --plugin pretty classpath:features/MyTestFeature.feature
        //NOTE: Congiguration.properties file CAN NOT be reached in jar file, so all callings in code were hard coded!!
    }
}

