package io.github.unjoinable.minigamelib.team;

import net.kyori.adventure.key.Namespaced;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public record ColoredTeam(@NotNull Namespaced namespace,
                          @NotNull String displayName,
                          @NotNull TextColor color) implements Team {

    @Override
    public @NotNull Namespaced namespace() {
        return namespace;
    }

    @Override
    public @NotNull String displayName() {
        return displayName;
    }
}
