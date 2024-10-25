package io.github.unjoinable.minigamelib.api.state;

/**
 * Represents a mechanism for managing and switching between different states.
 * This class allows for controlled transitions between various states in a system.
 * 
 * Inspired by github.com/Minikloon/FSMgasm
 * @author Minikloon
 */
public class StateSwitch {
    protected State state;

    /**
     * Constructs a new StateSwitch with an initial state.
     *
     * @param state The initial state to set for this StateSwitch.
     */
    public StateSwitch(State state) {
        this.state = state;
    }

    /**
     * Changes the current state to a new state.
     * This method ends the current state, updates to the new state, and starts the new state.
     *
     * @param nextState The new state to transition to.
     */
    public void changeState(State nextState) {
        state.end();
        this.state = nextState;
        nextState.start();
    }
}