public class Game {
    private final String name;
    private final Genre genre;
    private final Type type;

    Game(String name, Genre genre, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    public static GameDisk getDisk(String name, Genre genre, String description) {
        return new GameDisk(name, genre, description);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre, double rating) {
        return new VirtualGame(name, genre, rating);
    }

    public enum Genre {
        ACTION, SPORT, RACE;
    }

    public enum Type {
        VIRTUAL, PHYSICAL;
    }

    public static class GameDisk {
        private final String description;
        private final Game data;

        private GameDisk(String name, Genre genre, String description) {
            this.description = description;
            this.data = new Game(name, genre, Type.PHYSICAL);
        }

        public String getDescription() {
            return description;
        }

        public Game getData() {
            return data;
        }
    }

    public static class VirtualGame {
        private final double rating;
        private final Game data;

        private VirtualGame(String name, Genre genre, double rating) {
            this.rating = rating;
            this.data = new Game(name, genre, Type.VIRTUAL);
        }

        public double getRating() {
            return rating;
        }

        public Game getData() {
            return data;
        }
    }
}
