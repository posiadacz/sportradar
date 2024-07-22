package com.sport.football;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScoreBoardTest {

    @Test
    public void shouldAddNewMatchToScoreBoard() {
        Match match = mock(Match.class);
        ScoreBoardPersistence persistence = mock(ScoreBoardPersistence.class);

        new ScoreBoard(persistence)
                .addNewMatch(match);

        verify(persistence, times(1))
                .add(match);
    }

}