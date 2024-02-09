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

}
