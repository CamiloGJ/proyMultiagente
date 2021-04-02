package invernaderoV1;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import AgenteEF.AgEF;
import AgenteEF.EstadoAgEF;
import AgenteEF.Behavior.actiondoneEFGuard;
import AgenteEF.Behavior.orderEFGuard;
import AgenteEF.Behavior.positionEFGuard;
import AgenteSR.AgSR;
import AgenteSR.EstadoAgSR;
import AgenteSR.Behavior.actiondoneSRGuard;
import AgenteSR.Behavior.orderSRGuard;
import AgenteSR.Behavior.positionSRGuard;
import AgenteInv.AgInv;
import AgenteInv.EstadoAgInv;
import AgenteInv.Behavior.conflictGuard;
import AgenteInv.Behavior.efactionGuard;
import AgenteInv.Behavior.sractionGuard;
import AgenteInv.Behavior.EnvstateGuard;
import AgenteInv.Behavior.invactionGuard;
import AgenteEnv.AgEnv;
import AgenteEnv.EstadoAgEnv;
import AgenteEnv.Behavior.actualstateenvGuard;
import AgenteEnv.Behavior.ModelGuard;
import AgenteEnv.Behavior.newstateenvGuard;
import AgenteEnv.Data.DataEnv;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Util.PeriodicDataBESA;
import AgenteEnv.Behavior.RefreshenvGuard;
import AgenteEnv.Tcounter;
/*
import OdoAgent.AgentOdo;
import OdoAgent.Behavior.GuardOdoModify;
import DisplayAgent.Behavior.DisplayGuard;
import OdoAgent.Behavior.GuardMoveOdo;
import DisplayAgent.Behavior.StopGuard;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Util.PeriodicDataBESA;
import DisplayAgent.DisplayAgent;
import DisplayAgent.DisplayState;
*/
public class InvernaderoV1 {

    private static long PERIODIC_TIME = 1000;
    
    /**
    InvernaderoV1 method
    */
    public InvernaderoV1() {
    }
    
   public static void main(String[] args) {
       //-------------------------------------//
       //Creates and starts the BESA container//
       //-------------------------------------//
       AdmBESA adm = AdmBESA.getInstance();
       /*
       //----------------//
       // Create EF Agent//
       //----------------//
       AgEF agenteEF = null;
       String alias = "AgenteEF_1";
       EstadoAgEF estadoEF = new  EstadoAgEF();
       StructBESA structEF = new StructBESA();
       double passwdAg = 0.51;
       try {
           structEF.addBehavior("behaviorEF");
           structEF.bindGuard("behaviorEF",positionEFGuard.class);//Movimiento del agente
           structEF.bindGuard("behaviorEF",actiondoneEFGuard.class);//Notificar ambiente si elimino o fertilizo
           structEF.bindGuard("behaviorEF",orderEFGuard.class);//Recibir ordenes del invernadero
           agenteEF = new AgEF(alias, estadoEF, structEF, passwdAg);
       } catch (ExceptionBESA ex){
           ReportBESA.error(ex);
       }
       agenteEF.start();
       
       //----------------//
       // Create SR Agent//
       //----------------//
       AgSR agenteSR = null;
       alias = "AgenteSR_1";
       EstadoAgSR estadoSR = new  EstadoAgSR();
       StructBESA structSR = new StructBESA();
       passwdAg = 0.52;
       try {
           structSR.addBehavior("behaviorSR");
           structSR.bindGuard("behaviorSR", positionSRGuard.class);//Movimiento del agente
           structSR.bindGuard("behaviorSR", actiondoneSRGuard.class);//Notificar ambiente si sembro o recolecto
           structSR.bindGuard("behaviorSR", orderSRGuard.class);//Recibir ordenes del invernadero
           agenteSR = new AgSR(alias, estadoSR, structSR, passwdAg);
       } catch (ExceptionBESA ex){
           ReportBESA.error(ex);
       }
       agenteSR.start();
       */
       //----------------//
       // Create Invernadero Agent//
       //----------------//
       AgInv agenteInv = null;
       String alias = "Invernadero";
       EstadoAgInv estadoInv = new  EstadoAgInv();
       StructBESA structInv = new StructBESA();
       double passwdAg = 0.53;
       try {
           structInv.addBehavior("behaviorInv");
           //structInv.bindGuard("behaviorInv", conflictGuard.class);//evalua si alguna variable del abmeinte esta en conflicto
           //structInv.bindGuard("behaviorInv",sractionGuard.class);//envia comunicacion de que accion tomar al AgenteSR
           structInv.bindGuard("behaviorInv",efactionGuard.class);//envia comunicacion de que accion tomar al AgenteEF
           //structInv.bindGuard("behaviorInv",EnvstateGuard.class);//recibe comunicacion del estado de las variables del ambiente
           //structInv.bindGuard("behaviorInv",invactionGuard.class); //envia accion realizada al Agambiente
           agenteInv = new AgInv(alias, estadoInv, structInv, passwdAg);
       } catch (ExceptionBESA ex){
           ReportBESA.error(ex);
       }
       agenteInv.start();
       
       //----------------//
       // Create Ambiente Agent//
       //----------------//
       AgEnv agenteEnv = null;
       alias = "Ambiente";
       EstadoAgEnv estadoEnv = new  EstadoAgEnv(); //
       StructBESA structEnv = new StructBESA();
       passwdAg = 0.54;
       try {structEnv.addBehavior("behaviorEnv");
            structEnv.bindGuard("behaviorEnv", RefreshenvGuard.class);//Modelar el ambiente
            structEnv.bindGuard("behaviorEnv", ModelGuard.class);//Modelar el ambiente
           
           //structEnv.bindGuard("behaviorEnv", actualstateenvGuard.class);//Envia estado actual del ambiente
           //structEnv.bindGuard("behaviorEnv", newstateenvGuard.class);//Nuevo estado modificado por los otros ambientes
            agenteEnv = new AgEnv(alias, estadoEnv, structEnv, passwdAg);
       } catch (ExceptionBESA ex){
           ReportBESA.error(ex);
       }
       agenteEnv.start();
       
       //--------------------------------------------------------------------//
       // Starts the odometer, creates a PeriodicDataBESA guard and start    //
       // command (for starts the process the increments the counter). The   //
       // PeriodicDataBESA is a data type predefine for the configuration of // 
       // the periodic guard.                                                //
       //--------------------------------------------------------------------//
       AgHandlerBESA ah = null;
       PeriodicDataBESA periodicData = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);//Creates the start message for periodic guard.
       EventBESA startPeriodicEv = new EventBESA(RefreshenvGuard.class.getName(), periodicData);//Creates the event and sends to the GuardMoveOdo.
       try {
           ah = adm.getHandlerByAid(agenteEnv.getAid());           
           ah.sendEvent(startPeriodicEv);
           System.out.println("main gp ev sent");
       } catch (ExceptionBESA e) {
           e.printStackTrace();
       }
       /*
       //-----------------------------------------------------//
       //Create event with a start message for starts modelEnv//
       //-----------------------------------------------------//
       AgHandlerBESA ah = null;
       //EventBESA startTimeOut = new EventBESA(ModelGuard.class.getName(), null);
       EventBESA msg = new EventBESA(ModelGuard.class.getName(),new DataEnv("hola"));
       try {
            ah = adm.getHandlerByAlias("Ambiente");
            // construye el mensaje!!
             // ult param es el dato q le mete
            ah.sendEvent(msg);
        } catch (ExceptionBESA e) {
            e.printStackTrace();
        }
       
       /*
       //-----------------------------------------------------//
       //Create event with a start message for starts modelEnv//
       //-----------------------------------------------------//
       AgHandlerBESA ah = null;
       //EventBESA startTimeOut = new EventBESA(ModelGuard.class.getName(), null);
       try {
            ah = adm.getHandlerByAlias("Ambiente");
            // construye el mensaje!!
            EventBESA msg = new EventBESA(ModelGuard.class.getName(),new DataEnv("hola")); // ult param es el dato q le mete
            ah.sendEvent(msg);
        } catch (ExceptionBESA e) {
            e.printStackTrace();
        }
       
       PeriodicDataBESA periodicData = new PeriodicDataBESA(PERIODIC_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);//Creates the start message for periodic guard.
       //EventBESA modelEnv = new EventBESA(ModelGuard.class.getName(),null);
       EventBESA startPeriodicEv = new EventBESA(ModelGuard.class.getName(), periodicData);//Creates the event and sends to the GuardMoveOdo.
        try {
            ah = adm.getHandlerByAid(agenteEnv.getAid());
            ah.sendEvent(startPeriodicEv);
        } catch (ExceptionBESA e) {
            e.printStackTrace();
        }
        */
    }
}
// cual es la forma en la que arrancan los agentes
// timer task instancia una clase nueva dentro del ag ambiente la extiende de timer task, el timer task lo asigna a un atributo del agente ambiente y ese atributo lo invoca 