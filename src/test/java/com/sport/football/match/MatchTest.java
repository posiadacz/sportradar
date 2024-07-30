package com.sport.football.match;

import com.sport.football.FootballTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    @DisplayName("Should start match with current time and 0 score")
    public void shouldStartMatch() {
        Instant now = Instant.now().minusMillis(1);
        Match match = Match.start(FootballTeam.MEXICO, FootballTeam.AUSTRALIA);
        assertTrue(match
                .getStartTimestamp()
                .isAfter(now));

        assertEquals(0, match.getTotalScore());
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
    }

    @Test
    @DisplayName("Should get total and teams score")
    public void shouldTotalScore() {
        Match match = Match.start(FootballTeam.MEXICO, FootballTeam.AUSTRALIA);

        match.setScore(2, 3);

        assertEquals(5, match.getTotalScore());
        assertEquals(2, match.getHomeTeamScore());
        assertEquals(3, match.getAwayTeamScore());
    }

}