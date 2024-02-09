package service;

import model.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FootballScoreboard {

    private List<Match> matches;

    public FootballScoreboard() {
        matches = new ArrayList<>();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Home team and away team names cannot be null or empty.");
        }
        if (findMatch(homeTeam, awayTeam).isPresent()) {
            throw new IllegalArgumentException("A match between these teams is already in progress.");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        System.out.println(String.format("Match between %s and %s started.", homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam).orElseThrow(() -> new IllegalArgumentException(String.format("No match found between %s and %s.", homeTeam, awayTeam)));
        match.setScore(homeScore, awayScore);
        System.out.println(String.format("Score updated for match between %s and %s.", homeTeam, awayTeam));
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam).orElseThrow(() -> new IllegalArgumentException(String.format("No match found between %s and %s.", homeTeam, awayTeam)));
        matches.remove(match);
        System.out.println(String.format("Match between %s and %s finished.", homeTeam, awayTeam));
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst();
    }

    public List<Match> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort(Comparator.comparing(Match::getTotalScore).reversed().thenComparing(Match::getStartTime));
        return sortedMatches;
    }
}
