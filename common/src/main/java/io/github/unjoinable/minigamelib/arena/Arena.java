package io.github.unjoinable.minigamelib.arena;

import io.github.unjoinable.minigamelib.team.Team;
import io.github.unjoinable.minigamelib.utils.Coordinate;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Map;

/**
 * Represents an arena in a minigame.
 */
public interface Arena {

    /**
     * Returns the name of the arena.
     *
     * @return the name of the arena
     */
    String name();

    /**
     * Returns the fallback location for players when their team's location is not found.
     * or used for teleporting at the start of game.
     *
     * @return the fallback location
     */
    @NotNull Coordinate fallbackLoc();

    /**
     * Returns a map of teams to their respective locations in the arena.
     *
     * @return a map of teams to their locations
     */
    @NotNull Map<Team, Coordinate> locations();

    /**
     * Returns the path to the world file of the arena.
     *
     * @return the path to the world file
     */
    @NotNull Path worldPath();

    /**
     * Returns the location of the specified team in the arena.
     * If the team's location is not found, throws an IllegalArgumentException.
     *
     * @param team the team whose location is to be retrieved
     * @return the location of the specified team
     * @throws IllegalArgumentException if the team's is not present in current instance
     */
    default @NotNull Coordinate locationByTeam(@NotNull Team team) {
        if  (locations().containsKey(team)) {
            return locations().get(team);
        } else throw new IllegalArgumentException("Team not found. " + team.displayName());

    }

    /**
     * Adds a new team and its corresponding location to the arena.
     *
     * @param team the team to be added
     * @param loc the location of the team in the arena
     */
    default void addLoc(@NotNull Team team, @NotNull Coordinate loc) {
        locations().put(team, loc);
    }
}
