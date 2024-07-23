package com.sport.football.match;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MatchPersistenceTest {

    @Test
    @DisplayName("Should store and retrieve matches")
    public void shouldStoreAndRetrieveMatches() {
        Match match1 = mock(Match.class);
        Match match2 = mock(Match.class);
        MatchPersistence persistence = new MatchPersistence();

        persistence
                .add(match1)
                .add(match2)
                .add(null);

        assertEquals(Arrays.asList(match1, match2),
                persistence.getAll());
    }
}