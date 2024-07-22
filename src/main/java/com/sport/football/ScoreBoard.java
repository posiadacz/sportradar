package com.sport.football;

import java.util.List;

public class ScoreBoard {

    private final ScoreBoardPersistence persistence;

    public ScoreBoard(ScoreBoardPersistence persistence) {
        this.persistence = persistence;
    }

    public void addNewMatch(Match match) {
        persistence.add(match);
    }

    public void updateMatchScore(Match match) {

    }

    public void finishMatch(Match match) {

    }

    public List<Match> getMatchInProgressSummary() {
        return null;
    }

}
