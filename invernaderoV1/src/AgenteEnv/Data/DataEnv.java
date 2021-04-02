/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteEnv.Data;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author SANTIAGO
 */
public class DataEnv extends DataBESA {
    /**
     * Message.
     */
    private String message;
    /**
     * Value.
     */
    private String valueString;

    /** 
     * Creates a new instance of DataOdo.
     * @param mensaje
     */
    public DataEnv(String mensaje) {
        this.message = mensaje;
    }

    /**
     * Gets the menssage.
     *
     * @return Menssage.
     */
    public String getMenssage() {
        return message;
    }

    /**
     * Sets the menssage.
     *
     * @param str Menssage.
     */
    public void strToDataBesa(String str) {
        message = str;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}