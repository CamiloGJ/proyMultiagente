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
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class AgEnv extends AgentBESA {
    
    private int INT_COUNTER;
    private int INT_TEMP;
    private int INT_T_OFFST;
    
    public AgEnv(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state,structAgent, passwd);
        this.INT_COUNTER = 0;
        this.INT_TEMP = 30; //temperatura promedio de villavicencio, con esta se incia la simulacion
        this.INT_T_OFFST = 0; //offset inicial de temperatura
    }

        @Override
    public void setupAgent() {
        EstadoAgEnv stateEnv = (EstadoAgEnv) this.state;
        stateEnv.setCounter(new Tcounter(INT_COUNTER));                       //Initialized the counter.
        stateEnv.setState(EstadoAgEnv.State.Init);  
        // inicializar el clima
        stateEnv.setTemperature(INT_TEMP);
        stateEnv.setToffset(INT_T_OFFST);
        ArrayList<Integer> v = new ArrayList<>();                               //Sets the initial state.
        v.add(1);
        stateEnv.initState(v);
        
        
        // aca debo incializar la matriz y sus valores internos iniciales
    }

    @Override
    public void shutdownAgent() {
    } 
    
}
