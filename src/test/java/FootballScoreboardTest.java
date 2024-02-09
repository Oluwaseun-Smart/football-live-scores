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

    @Test
    public void testFinishMatch() {
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.finishMatch("Italy", "England");
        assertEquals(0, scoreboard.getSummary().size());
    }

    @Test
    public void testGetSummary() {
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.updateScore("India", "England", 1, 2);
        scoreboard.startMatch("Germany", "Italy");
        scoreboard.updateScore("Ghana", "South Africa", 2, 2);
        scoreboard.startMatch("France", "USA");
        assertEquals("Germany", scoreboard.getSummary().get(0).getHomeTeam());
        assertEquals("Italy", scoreboard.getSummary().get(0).getAwayTeam());
        assertEquals("Nigeria", scoreboard.getSummary().get(1).getHomeTeam());
        assertEquals("Poland", scoreboard.getSummary().get(1).getAwayTeam());
        assertEquals("France", scoreboard.getSummary().get(2).getHomeTeam());
        assertEquals("USA", scoreboard.getSummary().get(2).getAwayTeam());
    }
}
