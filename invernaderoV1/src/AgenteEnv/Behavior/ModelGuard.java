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
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import AgenteEnv.EstadoAgEnv;
import AgenteEnv.Tcounter;
import BESA.ExceptionBESA;
import AgenteInv.Behavior.EnvstateGuard;

public class ModelGuard extends GuardBESA {
    public final int AVGTEMP = 30;
    /**
     * Function executed guard.
     *
     * @param dataEvent Event with DataEnv data. DataEnv contains the possible
 message: current counter.
     * @see DataEnv
     */
    /*
    @Override
    public void funcExecGuard(EventBESA dataEvent) {
        //--------------------------------------------------------------------//
        // Gets the data from event and gets the odometer state.              //
        //--------------------------------------------------------------------//
        String data = ((DataEnv) dataEvent.getData()).getMenssage();
        EstadoAgEnv stateEnv = (EstadoAgEnv) this.getAgent().getState();
        
        AgHandlerBESA ah = null;
        EventBESA ev = new EventBESA(actualstateenvGuard.class.getName(), data);   //Creates the data event for GuardOdoModify.
        try {
            ah = AdmBESA.getInstance().getHandlerByAlias("Odometro_1");         //Gets the agent handler.
            ah.sendEvent(ev);                                                   //Sends the event.
        } catch (Exception ex) {
            ReportBESA.error(ex.getMessage());
        }
        //--------------------------------------------------------------------//
        // Checks the menssage the data. If the menssage is INC, is prosseded //
        // to imcrementes the odometer counter. If the menssage is DEC, is    //
        // prosseded to decrements the odometer counter.                      //
        //--------------------------------------------------------------------//
        ReportBESA.info("ESTOY CONTANDO");
        stateEnv.incrementCounter();
        ReportBESA.debug(data + "-> Count = " + stateEnv.getCounter().getValue());
    }
    */
    /**
     * Code blocks that checks if the guard can be executed or not.
     *
     * @param objEvalBool References to the agent state.
     * @return true if the guard can be executed or false in other case.
     */
    /*
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        CounterOdo counter = ((StateOdo) objEvalBool).getCounter(); //Gets the agent state.
        ReportBESA.debug("ESTOY EVALUANDO MODIFY");
        return counter.getValue() < 1000; //Checks if activated the condition.
    }
    */
    /**
     * Function executed guard.
     *
     * @param dataEvent Event with DataOdo data. DataOdo contains the possible
     * message: increments (INC) and decrements (DEC).
     * @see GuardMoveOdo
     * @see DataOdo
     */
    /**
     * Code blocks that checks if the guard can be executed or not.
     *
     * @param objEvalBool References to the agent state.
     * @return true if the guard can be executed or false in other case.
     */
    
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        Tcounter counter = ((EstadoAgEnv) objEvalBool).getCounter(); //Gets the agent state.
        ReportBESA.debug("ESTOY EVALUANDO MODIFY");
        return counter.getValue() < 1000; //Checks if activated the condition.
        }
    
    @Override
    public void funcExecGuard(EventBESA dataEvent) {
        
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
        
        // actual state env guar recieve data and transmit to greenhouse agent 
        
        //--------------------------------------------------------------------//
        // Creates and sends the show counter messages (the event).           //
        // The messages is sent to GuardOdoRequest which display              //
        // the progress the odometer counter.                                 //
        //--------------------------------------------------------------------//
        AgHandlerBESA ah = null;
        int currentTemp = stateEnv.getTemperature();
        String valueString = String.valueOf(currentTemp);                      //Parses the currente counter value to string.
        DataEnv datEnv = new DataEnv("TEMPERATURE"); //que mensaje en el inv es leer variables?                                          //Creates the increment message.
        datEnv.setValueString(valueString);
        EventBESA ev = new EventBESA(EnvstateGuard.class.getName(), datEnv);               //Creates the data event for GuardOdoRequest.
        try {
            ah = this.agent.getAdmLocal().getHandlerByAlias("Invernadero");         //Gets the agent handler.
            ah.sendEvent(ev);                                                   //Sends the event.
            ReportBESA.debug("comunico al invernadero las variables ambientales");
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }    
    
}
