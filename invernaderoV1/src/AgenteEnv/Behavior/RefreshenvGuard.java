/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteEnv.Behavior;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import AgenteSR.EstadoAgSR;
import BESA.Log.ReportBESA;
import AgenteEnv.Data.DataEnv;
import AgenteEnv.EstadoAgEnv;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;

public class RefreshenvGuard extends PeriodicGuardBESA {
    /**
     * Creates a new instance.
     *
     * @param ag References to agent.
     */
    public RefreshenvGuard() {
        super();
    }
    /**
     * Code block that executed in function of the periodic time that was
     * specificated.
     *
     * @param event Automatic event that marks the time execution periodic
     * time.
     */
    @Override
    public void funcPeriodicExecGuard(EventBESA event) {
        //--------------------------------------------------------------------//
        // 
        //--------------------------------------------------------------------//       
        DataEnv datEnv;                                                              
        datEnv = new DataEnv("INC");  //Creates the increment time message.
        
        //--------------------------------------------------------------------//
        // Gets the data from event and gets the environment state.              //
        //--------------------------------------------------------------------//
        String data = ((DataEnv) dataEvent.getData()).getMenssage();
        EstadoAgEnv stateEnv = (EstadoAgEnv) getAgent().getState();
        //--------------------------------------------------------------------//
        // Checks the menssage the data. If the menssage is INC, is prosseded //
        // to imcrementes the odometer counter. If the menssage is DEC, is    //
        // prosseded to decrements the odometer counter.                      //
        //--------------------------------------------------------------------//
        ReportBESA.info("ESTOY MODIFICANDO EL AMBIENTE");
        if (stateEnv.getState() == EstadoAgEnv.State.Init) {                       //Checks if the odometer was in initial state.
            stateEnv.changueState(EstadoAgEnv.State.Start);                        //Indicates that odometer is moved.
        }
        if (data.equals("INC")) {                                               //Checks if the menssage is of increment.
            stateEnv.incrementCounter();
            double b = Math.sin(stateEnv.getCounter().getValue()/25);
            double tempe = stateEnv.getTemperature();
            //double newTemp = tempe + (2*b);
            double newTemp = AVGTEMP + (10*b);
            double perturbacion = stateEnv.getToffset();
            double finalTemp = newTemp + perturbacion;
            stateEnv.setTemperature((int)finalTemp);
            // temperatura incial(cte)+10*sin(t) -> t=90-> 40 si t=0 30 si t+270 -> 20
        }
        ReportBESA.debug(data + "-> Count = " + stateEnv.getCounter().getValue());
        ReportBESA.debug(data + "-> Temperature = " + stateEnv.getTemperature());
        
        //--------------------------------------------------------------------//
        // Creates and sends the increment counter messages (the event).      //
        // The messages is sent to GuardOdoModify which simulates             //
        // the progress the odometer counter.                                 //
        //--------------------------------------------------------------------//
        AgHandlerBESA ah = null;
        EventBESA ev = new EventBESA(ModelGuard.class.getName(), datEnv);   //Creates the data event for GuardOdoModify.
        try {
            ah = AdmBESA.getInstance().getHandlerByAlias("Ambiente");         //Gets the agent handler.
            ah.sendEvent(ev);                                                   //Sends the event.
            System.out.println("Rguard ev enviado");
        } catch (Exception ex) {
            ReportBESA.error(ex.getMessage());
        }
    }
}
