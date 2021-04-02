/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteEnv;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import java.io.Serializable;
import java.util.ArrayList;

public class EstadoAgEnv extends StateBESA implements Serializable{  
    /**
     * Enumeration that indicates the possible states of the odometer.
     */
    public enum State {
        Init, Start, Stop
    }
    /**
     * Current odometer state.
     */
    private State state;
    /**
     * Initializes into initState method using the profile.
     */
    private int step;
    /**
     * Counter of the odometer.
     */
    private Tcounter counter;
    /**
     * Temperature of the environment
     */
    private int temperature;
    /**
     * Temperature offset provided by greenhouse
     */
    private int tempOffset;
    /**
     * Temperature increment
     */
    //private int inc;
    /**
     * Creates a new instance.
     *
     */
    public EstadoAgEnv() {
        super();       
    }

    /**
     * Change the state.
     * 
     * @param state
     */
    public void changueState(State state) {
        this.state = state;
    }

    /**
     * Initializes the state.
     * 
     * @param profile Startup parameters.
     */
    public synchronized void initState(ArrayList profile) {
        step = ((Integer) profile.get(0));
    }

    /**
     * Sets the state.
     * 
     * @param state State.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets state.
     *
     * @return State.
     */
    public State getState() {
        return state;
    }
    
    /**
     * Gets the step.
     *
     * @return Step.
     */
    public synchronized int getStep() {
        return step;
    }

    /**
     * Sets step.
     *
     * @param step Step.
     */
    public synchronized void setStep(int step) {
        this.step = step;
    }

    /**
     * Gets the counter.
     * @return Counter.
     */
    public Tcounter getCounter() {
        return counter;
    }

    /**
     * Sets the counter.
     * 
     * @param counter Counter.
     */
    public void setCounter(Tcounter counter) {
        this.counter = counter;
    }

    /**
     * Increments the counter.
     */
    public synchronized void incrementCounter() {
        counter.addValue(step);
        update();
    }

    /**
     * Decrements the counter.
     */
    public synchronized void decrementCounter() {
        counter.addValue(-1 * step);
        update();
    }
    
    /**
    * Gets the temperature.
    * @return Counter.
    */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     * 
     * @param temp temperature.
     */
    public void setTemperature(int temp) {
        this.temperature = temp;
    }
    
    /**
     * Increments the temperature.
     * 
     * @param inc increment.
     */
    public synchronized void incrementTemperature(int inc) {
        this.temperature += inc;
        update();
    }

    /**
     * Decrements the counter.
     * 
     * @param inc increment.
     */
    public synchronized void decrementTemperature(int inc) {
        this.temperature += (-1 * inc);
        update();
    }
    
    /**
     * Increments the temperature.
     * 
     * @param inc increment.
     */
    public synchronized void calculateTemperature(int offst, int temp) {
        
        this.temperature += inc;
        update();
    }
    
    /**
     * Gets the temp offset.
     *
     * @return temp offset.
     */
    public synchronized int getToffset() {
        return tempOffset;
    }

    /**
     * Sets temp offset.
     *
     * @param offst temp offset.
     */
    public synchronized void setToffset(int offst) {
        this.tempOffset = offst;
    }
    
}
