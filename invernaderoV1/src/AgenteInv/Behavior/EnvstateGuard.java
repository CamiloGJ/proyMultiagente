/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenteInv.Behavior;

import AgenteEnv.Data.DataEnv;
import AgenteEnv.EstadoAgEnv;
import AgenteEnv.Tcounter;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import AgenteInv.EstadoAgInv;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;

public class EnvstateGuard extends GuardBESA{    
    @Override
    public void funcExecGuard(EventBESA dataEvent) {
        
        //--------------------------------------------------------------------//
        // Gets the data from event and gets the environment state.              //
        //--------------------------------------------------------------------//
        String data = ((DataEnv) dataEvent.getData()).getMenssage();
        EstadoAgInv stateInv = (EstadoAgInv) getAgent().getState();
        //--------------------------------------------------------------------//
        // Checks the menssage the data. If the menssage is INC, is prosseded //
        // to imcrementes the odometer counter. If the menssage is DEC, is    //
        // prosseded to decrements the odometer counter.                      //
        //--------------------------------------------------------------------//
        ReportBESA.info("RECIBI LO QUE EL AMBIENTE MANDO");
        // ACA LA LOGICA LA CREA YA UNO
        // 2 OPCIONES, ACA MANDO TODA LA LOGICA DE ANALISIS DE AGENTE INVERNADERO
        // O MANDO A LA SIGUIENTE GUARDA QUE EVALUA LO QUE RECIBE ESTA   GUARDA ?
        // LA LOGICA:
        // DEFINE CONSTANTE DE TEMPERATURA MINIMA APTA Y TEMPERATURA MAXIMA APTA
        
        //--------------------------------------------------------------------//
        // Creates and sends the show counter messages (the event).           //
        // The messages is sent to GuardOdoRequest which display              //
        // the progress the odometer counter.                                 //
        //--------------------------------------------------------------------//
        AgHandlerBESA ah = null;
        int currentTemp = stateEnv.getTemperature();
        String valueString = String.valueOf(currentTemp);                      //Parses the currente counter value to string.
        DataEnv datEnv = new DataEnv("SHOW"); //que mensaje en el inv es leer variables?                                          //Creates the increment message.
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
