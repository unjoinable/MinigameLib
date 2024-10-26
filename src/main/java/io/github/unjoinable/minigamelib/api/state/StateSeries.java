package io.github.unjoinable.minigamelib.api.state;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a series of states that can be executed in sequence.
 * This class extends the State class and manages a list of states.
 */
public class StateSeries extends State {
    protected int current = 0;
    protected boolean skipping = false;
    protected final List<State> states;

    /**
     * Constructs a new StateSeries with an empty list of states.
     * The series is initialized with no states, and states can be added later.
     */
    public StateSeries() {
        this.states = new ArrayList<>();
    }

    /**
     * Marks the current state for skipping.
     * When a state is skipped, the series will move to the next state
     * without waiting for the current state to complete naturally.
     */
    public void skip() {
        skipping = true;
    }

    /**
     * Adds a new state to the series
     *
     * @param state The new State to be added to the series
     */
    public void add(State state) {
        this.states.add(state);
    }

    /**
     * Adds a new state to the series, inserting it immediately after the current state.
     * This allows for dynamic modification of the series during execution.
     *
     * @param state The new State to be added to the series.
     */
    public void addNext(State state) {
        this.states.add(current + 1, state);
    }

    /**
     * Adds a new state to the series, inserting it immediately after the current state.
     * This allows for dynamic modification of the series during execution.
     *
     * @param newStates The new State to be added to the series.
     */
    public void addNext(List<State> newStates) {
        int i = 1;
        for (State state : newStates) {
            states.add(current + i, state);
            i++;
        }
    }

    @Override
    protected @NotNull Duration duration() {
        Duration total = Duration.ZERO;
        for (State state : states) {
            total = total.plus(state.duration());
        }
        return total;
    }

    @Override
    protected void onStart() {
        if (states.isEmpty()) {
            end();
            return;
        }

        states.get(current).start();
    }

    @Override
    protected void onUpdate() {
         State state = states.get(current);
         state.update();

        if((state.isReadytoEnd() && !state.frozen()) || skipping) {
            if (skipping) skipping = false;

            state.end();
            ++current;

            if(current >= states.size()) {
                end();
                return;
            }
            states.get(current).start();
        }

    }

    @Override
    protected void onEnd() {
        if(current < states.size()) {
            states.get(current).end();
        }
    }
}
