package com.sport.football;

import com.sport.football.match.Match;
import com.sport.football.match.MatchPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
import static org.mockito.Mockito.when;

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
        Match mexicoCanada = Match.start(MEXICO, CANADA);
        Match spainBrazil = Match.start(SPAIN, BRAZIL);
        Match finishedMatch = Match.start(AUSTRALIA, MEXICO);
        Match germanyFrance = Match.start(GERMANY, FRANCE);
        Match uruguayItaly = Match.start(URUGUAY, ITALY);
        Match argentinaAustralia = Match.start(ARGENTINA, AUSTRALIA);

        MatchPersistence persistence = new MatchPersistence();
        ScoreBoard scoreBoard = new ScoreBoard(persistence)
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
                .updateMatchScore(argentinaAustralia, 3, 1);

        Stream<Match> matchesInProgress = scoreBoard.getMatchesInProgressSorted();
        List<Match> matchesInProgressList = matchesInProgress.toList();

        assertEquals(5, matchesInProgressList.size());
        assertEquals(Stream.of(
                                uruguayItaly,
                                spainBrazil,
                                mexicoCanada,
                                argentinaAustralia,
                                germanyFrance)
                        .toList(),
                matchesInProgressList);
        assertEquals("""
                        1 Uruguay 6 - Italy 6
                        2 Spain 10 - Brazil 2
                        3 Mexico 0 - Canada 5
                        4 Argentina 3 - Australia 1
                        5 Germany 2 - France 2""",
                scoreBoard.printMatchInProgressSummary(new ScoreBoardPrinter()));
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

    @Test
    @DisplayName("Should sort matches by total score and start date")
    public void shouldSortByScoreDate() {
        MatchPersistence persistence = mock(MatchPersistence.class);
        ScoreBoard scoreBoard = new ScoreBoard(persistence);

        Instant startTime = Instant.now().minus(3, ChronoUnit.HOURS);

        Match match1 = mock(Match.class);
        when(match1.getTotalScore())
                .thenReturn(10);
        when(match1.getStartTimestamp())
                .thenReturn(startTime);

        Match match2 = mock(Match.class);
        when(match2.getTotalScore())
                .thenReturn(3);
        when(match2.getStartTimestamp())
                .thenReturn(startTime.plus(90, ChronoUnit.MINUTES));

        Match match3 = mock(Match.class);
        when(match3.getTotalScore())
                .thenReturn(3);
        when(match3.getStartTimestamp())
                .thenReturn(startTime);

        Match match4 = mock(Match.class);
        when(match4.getTotalScore())
                .thenReturn(1);
        when(match4.getStartTimestamp())
                .thenReturn(startTime);

        when(persistence.getAll())
                .thenReturn(Arrays.asList(match4, match3, match2, match1));

        List<Match> matchesSorted = scoreBoard.getMatchesInProgressSorted().toList();

        assertEquals(
                Arrays.asList(match1, match2, match3, match4),
                matchesSorted
        );
    }
}