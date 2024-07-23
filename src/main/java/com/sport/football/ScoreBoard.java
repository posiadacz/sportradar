package com.sport.football;

import com.sport.football.match.Match;
import com.sport.football.match.MatchPersistence;

import java.util.Comparator;
import java.util.stream.Stream;

public class ScoreBoard {

    private final MatchPersistence persistence;

    private final Comparator<Match> totalScoreComparator = Comparator.comparingInt(Match::getTotalScore).reversed();
    private final Comparator<Match> startDateComparator = Comparator.comparing(Match::getStartTimestamp).reversed();
    private final Comparator<Match> totalScoreAndDateComparator = totalScoreComparator.thenComparing(startDateComparator);

    public ScoreBoard(MatchPersistence persistence) {
        this.persistence = persistence;
    }

    public ScoreBoard addMatch(Match match) {
        persistence.add(match);
        return this;
    }

    public ScoreBoard updateMatchScore(Match match,
                                       int homeTeamScore,
                                       int awayTeamScore) {
        match.setScore(homeTeamScore, awayTeamScore);
        return this;
    }

    public ScoreBoard finishMatch(Match match) {
        persistence.delete(match);
        return this;
    }

    public Stream<Match> getMatchInProgressSummary() {
        return persistence
                .getAll()
                .stream()
                .sorted(totalScoreAndDateComparator);
    }
}
