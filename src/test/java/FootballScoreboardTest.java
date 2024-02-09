import org.junit.jupiter.api.Test;
import service.FootballScoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FootballScoreboardTest {

    @Test
    public void testStartMatch() {
        FootballScoreboard scoreboard = new FootballScoreboard();
        scoreboard.startMatch("Nigeria", "Poland");
        assertEquals(1, scoreboard.getSummary().size());
    }

    @Test
    public void testUpdateScore() {
        FootballScoreboard scoreboard = new FootballScoreboard();
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.updateScore("Nigeria", "Poland", 2, 1);
        assertEquals(2, scoreboard.getSummary().get(0).getHomeScore());
        assertEquals(1, scoreboard.getSummary().get(0).getAwayScore());
    }

    @Test
    public void testFinishMatch() {
        FootballScoreboard scoreboard = new FootballScoreboard();
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.finishMatch("Nigeria", "Poland");
        assertEquals(0, scoreboard.getSummary().size());
    }

    @Test
    public void testGetSummary() {
        FootballScoreboard scoreboard = new FootballScoreboard();
        scoreboard.startMatch("Nigeria", "Poland");
        scoreboard.updateScore("Nigeria", "Poland", 4, 2);
        scoreboard.startMatch("Germany", "Italy");
        scoreboard.updateScore("Germany", "Italy", 2, 2);
        scoreboard.startMatch("France", "USA");
        assertEquals("Nigeria", scoreboard.getSummary().get(0).getHomeTeam());
        assertEquals("Poland", scoreboard.getSummary().get(0).getAwayTeam());
        assertEquals("Germany", scoreboard.getSummary().get(1).getHomeTeam());
        assertEquals("Italy", scoreboard.getSummary().get(1).getAwayTeam());
        assertEquals("France", scoreboard.getSummary().get(2).getHomeTeam());
        assertEquals("USA", scoreboard.getSummary().get(2).getAwayTeam());
    }

    @Test
    public void testStartMatchWithNullTeams() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FootballScoreboard scoreboard = new FootballScoreboard();
            scoreboard.startMatch(null, null);
        });

        String expectedMessage = "Home team and away team names cannot be null or empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void testStartMatchWithEmptyTeams() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FootballScoreboard scoreboard = new FootballScoreboard();
            scoreboard.startMatch("", "");
        });

        String expectedMessage = "Home team and away team names cannot be null or empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void testUpdateScoreWithInvalidMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FootballScoreboard scoreboard = new FootballScoreboard();
            scoreboard.updateScore("Romania", "Mali", 2, 1);
        });

        String expectedMessage = "No match found between Romania and Mali.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void testFinishMatchWithInvalidMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FootballScoreboard scoreboard = new FootballScoreboard();
            scoreboard.finishMatch("Mali", "Turkey");
        });

        String expectedMessage = "No match found between Mali and Turkey.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void testUpdateScoreWithNegativeScores() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FootballScoreboard scoreboard = new FootballScoreboard();
            scoreboard.startMatch("Nigeria", "Mali");
            scoreboard.updateScore("Nigeria", "Mali", -1, 1);
        });

        String expectedMessage = "Scores cannot be negative.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }
}
