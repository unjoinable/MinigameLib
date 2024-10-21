package io.github.unjoinable.minigamelib.team;

import net.kyori.adventure.key.Namespaced;
import org.jetbrains.annotations.NotNull;

public record StandardTeam(@NotNull Namespaced namespace,
                           @NotNull String displayName) implements Team {

    @Override
    public @NotNull Namespaced namespace() {
        return namespace;
    }

    @Override
    public @NotNull String displayName() {
        return displayName;
    }
}
