package com.sport.football.match;

import com.sport.football.FootballTeam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@EqualsAndHashCode
@ToString
@Getter
public class Match {
    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;
    private final Instant startTimestamp;

    private Match(
            FootballTeam homeTeam,
            FootballTeam awayTeam,
            Instant startTimestamp) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTimestamp = startTimestamp;
    }

    public static Match start(FootballTeam homeTeam, FootballTeam awayTeam) {
        return new Match(
                homeTeam,
                awayTeam,
                Instant.now()
        );
    }

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
