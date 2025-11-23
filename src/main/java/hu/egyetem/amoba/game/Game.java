package hu.egyetem.amoba.game;

public class Game {
    private final Board board;
    private final Player human;
    private final Player ai;

    public Game(int rows, int cols) {
        board = new Board(rows, cols);
        human = new Player("Human", 'x');
        ai = new Player("Computer", 'o');
    }

    public void start() {
        int midRow = board.getRows() / 2;
        int midCol = board.getCols() / 2;
        board.placeMove(new Move(midRow, midCol), human);

        System.out.println("Welcome to Amőba!");
        board.printBoard();
    }
    
    public Player getAi() {
    return ai;
    }
}