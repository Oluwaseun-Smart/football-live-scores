package service;

import model.Match;

import java.util.ArrayList;
import java.util.List;

public class FootballScoreboard {

    private List<Match> matches;

    public FootballScoreboard() {
        matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Home team and away team names cannot be null or empty.");
        }
        if (findMatch(homeTeam, awayTeam) != null) {
            throw new IllegalArgumentException("A match between these teams is already in progress.");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        System.out.println(String.format("Match between %s and %s started.", homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        match.setScore(homeScore, awayScore);
        System.out.println(String.format("Score updated for match between %s and %s.", homeScore, awayScore));
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
        System.out.println("Match between " + homeTeam + " and " + awayTeam + " finished.");
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No match found between %s and %s.", homeTeam, awayTeam)));
    }
}
