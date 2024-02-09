import org.junit.Before;
import org.junit.jupiter.api.Test;
import service.FootballScoreboard;

import static org.junit.Assert.assertEquals;

public class FootballScoreboardTest {

    private FootballScoreboard scoreboard;

    @Before
    public void setUp() {
        scoreboard = new FootballScoreboard();
    }

    @Test
    public void testStartMatch() {
        scoreboard.startMatch("Nigeria", "Poland");
        assertEquals(1, scoreboard.getSummary().size());
    }

    @Test
    public void testUpdateScore() {
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.updateScore("France", "Germany", 2, 1);
        assertEquals(2, scoreboard.getSummary().get(0).getHomeScore());
        assertEquals(1, scoreboard.getSummary().get(0).getAwayScore());
    }

}
