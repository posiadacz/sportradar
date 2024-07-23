package com.sport.football.match;

import com.sport.football.FootballTeam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Match {
        private final FootballTeam homeTeam;
        private final FootballTeam awayTeam;
        private int homeTeamScore = 0;
        private int awayTeamScore = 0;
        @Getter
        private final Instant startTimestamp = Instant.now();

    public Match setScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        return this;
    }

    @ToString.Include
    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }
}
