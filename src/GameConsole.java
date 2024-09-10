
interface Powered {
    void powerOn();
    void powerOff();
}

class ConsoleShutdownException extends RuntimeException {
    public ConsoleShutdownException(String message) {
        super(message);
    }
}

public class GameConsole implements Powered {
    private final Brand brand;
    private final String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.model = "Default Model";
        this.serial = serial;
        this.isOn = false;
        this.waitingCounter = 0;
        this.firstGamepad = new Gamepad(brand, 1);
        this.secondGamepad = new Gamepad(brand, 2);
    }

    @Override
    public void powerOn() {
        isOn = true;
        System.out.println("GameConsole powered on.");
    }

    @Override
    public void powerOff() {
        isOn = false;
        System.out.println("GameConsole powered off.");
    }

    public void loadGame(Game game) {
        activeGame = game;
        System.out.println("Игра " + game.getName() + " загружается");
    }

    public void playGame() {
        checkStatus();

        if (activeGame == null) {
            System.out.println("Нет активной игры.");
            return;
        }

        System.out.println("Играем в " + activeGame.getName());

        if (firstGamepad.isOn()) {
            updateGamepad(firstGamepad);
        }

        if (secondGamepad.isOn()) {
            updateGamepad(secondGamepad);
        }

        checkStatus();
    }

    private void updateGamepad(Gamepad gamepad) {
        double newCharge = gamepad.getChargeLevel() - 10.0;
        gamepad.setChargeLevel(Math.max(newCharge, 0.0));

        if (gamepad.getChargeLevel() == 0.0) {
            gamepad.setOn(false);
        }

        System.out.println("Джойстик " + gamepad.getConnectedNumber() + " заряд: " + gamepad.getChargeLevel() + "%");
    }

    private void checkStatus() {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            waitingCounter++;
            System.out.println("Подключите джойстик");
            if (waitingCounter > 5) {
                powerOff();
                throw new ConsoleShutdownException("Приставка завершает работу из-за отсутствия активности");
            }
        } else {
            waitingCounter = 0;
        }
    }

    enum Brand {
        SONY, MICROSOFT, SAMSUNG, XIAOMI, NOKIA;
    }

    enum Color {
        BLACK, WHITE, RED, BLUE;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public class Gamepad implements Powered {
        private final Brand brand;
        private final String consoleSerial;
        private final int connectedNumber;
        private Color color;
        private double chargeLevel;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.consoleSerial = GameConsole.this.serial;
            this.connectedNumber = connectedNumber;
            this.color = Color.BLACK;
            this.chargeLevel = 100.0;
            this.isOn = false;
        }

        @Override
        public void powerOn() {
            isOn = true;
            System.out.println("Gamepad " + connectedNumber + " powered on.");
            if (!GameConsole.this.isOn) {
                GameConsole.this.powerOn();
            }
        }

        @Override
        public void powerOff() {
            isOn = false;
            System.out.println("Gamepad " + connectedNumber + " powered off.");
        }

        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
            if (on && !GameConsole.this.isOn) {
                GameConsole.this.powerOn();
            }
        }
    }
}
