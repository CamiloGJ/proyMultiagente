/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteEnv;

/**
 *
 * @author SANTIAGO
 */
public class Tcounter {
     /**
     * Odometer counter value.
     */
    private int value;

    /**
     * Creates a new instance.
     *
     * @param value Initial value.
     */
    public Tcounter(int value) {
        this.value = value;
    }

    /**
     * Gets the current counter value.
     *
     * @return Current value.
     */
    public synchronized int getValue() {
        return value;
    }

    /**
     * Sets the new value to the counter.
     *
     * @param value New value.
     */
    public synchronized void setValue(int value) {
        this.value = value;
    }

    /**
     * Increments the current value of the counter.
     *
     * @param step Value to increment.
     */
    public synchronized void addValue(int step) {
        this.value += step;
    }
}
