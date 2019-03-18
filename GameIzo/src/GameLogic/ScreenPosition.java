package GameLogic;

public class ScreenPosition {

    private int x;
    private int y;

    private int gameScreenX;
    private int gameScreenY;

    private int maxX;
    private int maxY;

    public ScreenPosition(int x, int y, int gameScreenX, int gameScreenY, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.gameScreenX = gameScreenX;
        this.gameScreenY = gameScreenY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGameScreenX() {
        return gameScreenX;
    }

    public int getGameScreenY() {
        return gameScreenY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
