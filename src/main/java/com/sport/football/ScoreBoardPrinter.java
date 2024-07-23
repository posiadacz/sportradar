package com.sport.football;

import com.sport.football.match.Match;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreBoardPrinter {


    public static final String LINE_BREAK = "\n";

    public String printMatches(Stream<Match> matches) {
        AtomicInteger index = new AtomicInteger();
        return matches
                .map(printMatchRow(index))
                .collect(Collectors.joining(LINE_BREAK));
    }

    private Function<? super Match, String> printMatchRow(AtomicInteger index) {
        return m -> String.format("%d %s %d - %s %d",
                index.incrementAndGet(),
                capitalize(m.getHomeTeam()),
                m.getHomeTeamScore(),
                capitalize(m.getAwayTeam()),
                m.getAwayTeamScore());
    }

    private String capitalize(FootballTeam team) {
        return StringUtils.capitalize(
                StringUtils.lowerCase(
                        team.name()));
    }
}
