package io.github.unjoinable.minigamelib.api.game;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameManagerImpl implements GameManager {
    private final Set<Game> games = new HashSet<>();

    @Override
    public @NotNull Set<Game> games() {
        return games;
    }
}
