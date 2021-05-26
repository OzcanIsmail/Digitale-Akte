package de.digitale.akte.runners;

import cucumber.api.cli.Main;

@SuppressWarnings("deprecation")
public final class TestRunner {

    //    private static String[] defaultOptions = {
//            "--glue", "stepdefinitions",
//            "--feature", "classpath:features/",
//            "--plugin", "pretty",
//            "--plugin", "json:cucumber.json",
//    };
//    public static void main(String[] args) {
//        SecurityManager manager = new IgnoreExitCall();
//        System.setSecurityManager(manager);
//        try {
//            cucumber.api.cli.Main.main(args);
//        } catch (SecurityException securityException) {
//            System.out.println("Ignore exit");
//        }
//        //Do some other stuff like reporting logic
//    }
    public static void main(String[] args) {
        Main.main(new String[]{
                "-g",
                "de/digitale/akte/stepDefs",
                "classpath:features",
                "-p", "pretty",
                "-m"}
        );
    }
}

