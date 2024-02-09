# Football Scoreboard Library

The Football Scoreboard library provides functionality to manage ongoing football matches and display their scores in real-time. It supports operations such as starting a new match, updating scores, finishing matches, and getting a summary of ongoing matches.

## Features

- Start a new match with initial score 0 - 0.
- Update scores for ongoing matches.
- Finish matches that have been completed.
- Get a summary of ongoing matches ordered by total score. Matches with the same total score are ordered by the most recently started match.

## Usage

1. **Installation**: Simply include the `FootballScoreboard.java` file in your Java project.

2. **Creating a FootballScoreboard instance**:

   ```java
   FootballScoreboard scoreboard = new FootballScoreboard();
   ```

3. **Starting a new match**:

   ```java
   scoreboard.startMatch("Home Team Name", "Away Team Name");
   ```

4. **Updating scores**:

   ```java
   scoreboard.updateScore("Home Team Name", "Away Team Name", homeScore, awayScore);
   ```

5. **Finishing a match**:

   ```java
   scoreboard.finishMatch("Home Team Name", "Away Team Name");
   ```

6. **Getting a summary of ongoing matches**:

   ```java
   List<Match> summary = scoreboard.getSummary();
   ```

   Each `Match` object in the summary list contains information about the home team, away team, scores, and start time of the match.

## Example

```java
FootballScoreboard scoreboard = new FootballScoreboard();
scoreboard.startMatch("Germany", "Brazil");
scoreboard.updateScore("Germany", "Brazil", 2, 1);
scoreboard.startMatch("France", "Italy");
scoreboard.updateScore("France", "Italy", 1, 1);
scoreboard.startMatch("Argentina", "Spain");
scoreboard.updateScore("Argentina", "Spain", 0, 0);

List<Match> summary = scoreboard.getSummary();
for (Match match : summary) {
    System.out.println(match.getHomeTeam() + " " + match.getHomeScore() + " - " + match.getAwayScore() + " " + match.getAwayTeam());
}
```

Output:
```
Germany 2 - 1 Brazil
France 1 - 1 Italy
Argentina 0 - 0 Spain
```

## Notes

- Ensure that team names are unique and meaningful to distinguish between matches.
- Negative scores are not allowed and will result in an IllegalArgumentException.