package de.digitale.akte.runners;

public final class TestRunner {

    private static String[] defaultOptions = {
            "--glue", "stepdefinitions",
            "--plugin", "pretty",
            "--plugin", "json:cucumber.json"
    };
    public static void main(String[] args) {
        SecurityManager manager = new IgnoreExitCall();
        System.setSecurityManager(manager);
        try {
            cucumber.api.cli.Main.main(args);
        } catch (SecurityException securityException) {
            System.out.println("Ignore exit");
        }
        //Do some other stuff like reporting logic
    }
}

