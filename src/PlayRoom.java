import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] physicalGames = {
                Game.getDisk("Game A", Game.Genre.ACTION, "Description A"),
                Game.getDisk("Game B", Game.Genre.SPORT, "DescriptionB"),
                Game.getDisk("Game C", Game.Genre.RACE, "Description C"),
                Game.getDisk("Game D", Game.Genre.ACTION, "Description D")
        };

        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("Virtual Game 1", Game.Genre.ACTION, 4.5),
                Game.getVirtualGame("Virtual Game 2", Game.Genre.SPORT, 3.7),
                Game.getVirtualGame("Virtual Game 3", Game.Genre.RACE, 5.0),
                Game.getVirtualGame("Virtual Game 4", Game.Genre.ACTION, 4.2)
        };

        GameConsole console = new GameConsole(GameConsole.Brand.SONY, "XC123QeWR");

        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk g1, Game.GameDisk g2) {
                return g1.getData().getGenre().compareTo(g2.getData().getGenre());
            }
        });

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame g1, Game.VirtualGame g2) {
                return Double.compare(g2.getRating(), g1.getRating());
            }
        });

        System.out.println("Physical Games:");
        for (Game.GameDisk disk : physicalGames) {
            System.out.println(disk.getData().getName() + " - " + disk.getData().getGenre());
        }

        System.out.println("\nVirtual Games:");
        for (Game.VirtualGame virtualGame : virtualGames) {
            System.out.println(virtualGame.getData().getName() + " - Rating: " + virtualGame.getRating());
        }

        GameConsole console1 = new GameConsole(GameConsole.Brand.SONY, "XC123QeWR");

        console1.getFirstGamepad().powerOn();
        console1.getSecondGamepad().powerOn();

        Game game1 = new Game("Example Game", Game.Genre.ACTION, Game.Type.PHYSICAL);
        console1.loadGame(game1);
        console1.playGame();
    }
}
