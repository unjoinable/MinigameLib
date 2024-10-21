package io.github.unjoinable.minigamelib.state;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class StateContainer extends State implements Iterable<State> {
    protected final List<State> states;

    public StateContainer() {
        this.states = new LinkedList<>();
    }

    public void add(State state) {
        this.states.add(state);
    }

    public void addAll(Collection<State> newStates) {
        this.states.addAll(newStates);
    }


    @Override
    public Iterator<State> iterator() {
        return states.iterator();
    }
}
