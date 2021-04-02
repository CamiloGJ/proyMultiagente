/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteEF;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author SANTIAGO
 */
public class AgEF extends AgentBESA{
    
    public AgEF(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }
    @Override
    public void setupAgent() {
        EstadoAgEF posicion = (EstadoAgEF) this.getState();
        posicion.setPosicion(0);
    }

    @Override
    public void shutdownAgent() {
    }
}