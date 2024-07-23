package com.sport.football.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchPersistence {

    private final List<Match> storage = new ArrayList<>();

    public MatchPersistence add(Match match) {
        Optional
                .ofNullable(match)
                .ifPresent(storage::add);
        return this;
    }

    public List<Match> getAll() {
        return storage;
    }

    public void delete(Match match) {
        storage.remove(match);
    }
}
