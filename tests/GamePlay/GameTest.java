package GamePlay;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testStart(){
        Game game = new Game();
        assert (game.isWhiteTurn());
    }

    @Test
    public void name() {
    }
}