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
public class Climate {
    /**
     * Temperature value.
     */
    private float temp;
    /**
     * Temperature value.
     */
    private float hmdt;
    

    /**
     * Creates a new instance.
     *
     * @param temp Initial value.
     * @param hmdt Initial value.
     */
    public Climate(float temp, float hmdt){
        this.temp = temp;
        this.hmdt = hmdt;
    }

    /**
     * Gets the current temperature value.
     *
     * @return Current value.
     */
    public synchronized float getTemp() {
        return temp;
    }
    
    /**
     * Gets the current humidity value.
     *
     * @return Current value.
     */
    public synchronized float getHmdt() {
        return hmdt;
    }

    /**
     * Sets the new value to the temperature.
     *
     * @param ntemp New value.
     */
    public synchronized void setTemp(float ntemp) {
        this.temp = ntemp;
    }
    
    /**
     * Sets the new value to the temperature.
     *
     * @param nhmdt New value.
     */
    public synchronized void setHmdt(float nhmdt) {
        this.hmdt = nhmdt;
    }

    /**
     * Increments the current value of the temperature.
     *
     * @param tinc Value to increment.
     */
    public synchronized void inctemp(float tinc) {
        this.temp += tinc;
    }
    
    /**
    * Increments the current value of the temperature.
    *
    * @param tdec Value to increment.
    */
    public synchronized void decTemp(float tdec) {
        this.temp -= tdec;
    }
    
    /**
     * Increments the current value of the temperature.
     *
     * @param hinc Value to increment.
     */
    public synchronized void incHmdt(float hinc) {
        this.hmdt += hinc;
    }
    
    /**
    * Increments the current value of the temperature.
    *
    * @param hdec Value to increment.
    */
    public synchronized void decHmdt(float hdec) {
        this.hmdt -= hdec;
    }
}
