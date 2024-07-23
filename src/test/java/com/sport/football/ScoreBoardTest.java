package com.sport.football;

import com.sport.football.match.Match;
import com.sport.football.match.MatchPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.sport.football.FootballTeam.ARGENTINA;
import static com.sport.football.FootballTeam.AUSTRALIA;
import static com.sport.football.FootballTeam.BRAZIL;
import static com.sport.football.FootballTeam.CANADA;
import static com.sport.football.FootballTeam.FRANCE;
import static com.sport.football.FootballTeam.GERMANY;
import static com.sport.football.FootballTeam.ITALY;
import static com.sport.football.FootballTeam.MEXICO;
import static com.sport.football.FootballTeam.SPAIN;
import static com.sport.football.FootballTeam.URUGUAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScoreBoardTest {


    @Test
    @DisplayName("Should add match to score board")
    public void shouldAddMatch() {
        Match match = mock(Match.class);
        MatchPersistence persistence = mock(MatchPersistence.class);

        new ScoreBoard(persistence)
                .addMatch(match);

        verify(persistence, times(1))
                .add(match);
    }

    @Test
    @DisplayName("Should add, update score and generate summary")
    public void shouldAddUpdateSummary() {
        Match mexicoCanada = new Match(MEXICO, CANADA);
        Match spainBrazil = new Match(SPAIN, BRAZIL);
        Match finishedMatch = new Match(AUSTRALIA, MEXICO);
        Match germanyFrance = new Match(GERMANY, FRANCE);
        Match uruguayItaly = new Match(URUGUAY, ITALY);
        Match argentinaAustralia = new Match(ARGENTINA, AUSTRALIA);

        MatchPersistence persistence = new MatchPersistence();
        Stream<Match> summary = new ScoreBoard(persistence)
                .addMatch(mexicoCanada)
                .addMatch(spainBrazil)
                .addMatch(germanyFrance)
                .addMatch(finishedMatch)
                .addMatch(uruguayItaly)
                .addMatch(argentinaAustralia)
                .updateMatchScore(mexicoCanada, 0, 5)
                .updateMatchScore(spainBrazil, 10, 2)
                .updateMatchScore(finishedMatch, 8, 8)
                .updateMatchScore(germanyFrance, 2, 2)
                .finishMatch(finishedMatch)
                .updateMatchScore(uruguayItaly, 6, 6)
                .updateMatchScore(argentinaAustralia, 3, 1)
                .getMatchInProgressSummary();
        List<Match> summaryMatchesList = summary.toList();

        assertEquals(5, summaryMatchesList.size());
        assertEquals(Stream.of(
                                uruguayItaly,
                                spainBrazil,
                                mexicoCanada,
                                argentinaAustralia,
                                germanyFrance)
                        .toList(),
                summaryMatchesList);
    }

    @Test
    @DisplayName("Should update match score")
    public void shouldUpdateMatchScore() {
        Match match = mock(Match.class);

        new ScoreBoard(mock(MatchPersistence.class))
                .updateMatchScore(match, 5, 2);

        verify(match, times(1))
                .setScore(5, 2);
    }

}