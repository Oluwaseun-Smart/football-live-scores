import org.junit.Before;
import org.junit.jupiter.api.Test;
import service.FootballScoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testStartMatchWithNullTeams() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(null, null);
        });

        String expectedMessage = "Home team and away team names cannot be null or empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testStartMatchWithEmptyTeams() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch("", "");
        });

        String expectedMessage = "Home team and away team names cannot be null or empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateScoreWithInvalidMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Romania", "Mali", 2, 1);
        });

        String expectedMessage = "No match found between Romania and Mali.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFinishMatchWithInvalidMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.finishMatch("Mali", "Turkey");
        });

        String expectedMessage = "No match found between Mali and Turkey.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
