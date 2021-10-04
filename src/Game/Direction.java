package Game;


public enum Direction {
    NORTH, SOUTH, WEST, EAST, UNKNOWN;

    public static Direction getDirection(String direction) {
        try {
            direction = direction.toLowerCase();
            if (direction.matches("go\s+[n]|go\s+north|north|[n]")) {
                return NORTH;
            } else if (direction.matches("go\s+[s]|go\s+south|south|[s]")) {
                return SOUTH;
            } else if (direction.matches("go\s+[w]|go\s+west|west|[w]")) {
                return WEST;
            } else if (direction.matches("go\s+[e]|go\s+east|east|[e]")) {
                return EAST;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException unknownCommand) {
            return UNKNOWN;
        }
    }
}