/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteInv;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author camil
 */
public class AgInv extends AgentBESA {

    public AgInv(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() {
        //iniciaizar la matriz 4*4 de plantas
        // cada celda de la matriz tiene unas variables asociadas planta, plaga, nutrientes
        // el ambiente tiene tambien unas variables asociadas que se modelan matematicamente
        // llame esos metodos de estado
    }

    @Override
    public void shutdownAgent() {
    }   
}
