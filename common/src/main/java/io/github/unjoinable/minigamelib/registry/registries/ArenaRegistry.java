package io.github.unjoinable.minigamelib.registry.registries;

import io.github.unjoinable.minigamelib.arena.Arena;
import io.github.unjoinable.minigamelib.registry.Registry;

public class ArenaRegistry extends Registry<String, Arena> {
    public static ArenaRegistry INSTANCE = new ArenaRegistry();

    public static synchronized ArenaRegistry getInstance() {
        return INSTANCE;
    }
}
