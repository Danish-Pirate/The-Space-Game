public enum Command {
    HELP, EXIT, LOOK, UNKNOWN;

    public static Command getCommand (String command) {
        command = command.toLowerCase();
        if(command.matches("help|[h]")) {
            return HELP;
        }
        else if(command.matches("look|l")) {
            return LOOK;
        }
        else if(command.matches("exit")) {
            return EXIT;
        }
        else {
            return UNKNOWN;
        }
    }
}
