package io.github.unjoinable.minigamelib.api.state;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class StateContainer extends State implements Iterable<State> {
    protected final List<State> states;

    public StateContainer() {
        this.states = new ArrayList<>();
    }

    public void add(State state) {
        this.states.add(state);
    }

    public void addAll(Collection<State> newStates) {
        this.states.addAll(newStates);
    }

    public void freeze(boolean freeze) {
        states.forEach(state -> state.freeze(freeze));
    }

    @Override
    public @NotNull Iterator<State> iterator() {
        return states.iterator();
    }
}
