package Reception;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentRestaurent extends Agent {
	private static final long serialVersionUID = 1L;
    private int capaciteRestante;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            capaciteRestante = (int) args[0];
            System.out.println("Bienvenue ! C'est le Restaurent avec une capacité de " + capaciteRestante);

            addBehaviour(new CyclicBehaviour() {
            	private static final long serialVersionUID = 1L;
                @Override
                public void action() {
                    ACLMessage message = receive();
                    if (message != null) {
                        if (peutAccepterReservation()) {
                            ACLMessage reply = message.createReply();
                            reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                            reply.setContent("Votre réservation est acceptée.");
                            send(reply);
                        } else {
                            ACLMessage reply = message.createReply();
                            reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                            reply.setContent("Désolé, nous sommes complets. reserver un autre restaurent ");
                            send(reply);
                        }
                    } else {
                        block();
                    }
                }
            });
        } else {
            System.out.println("Bienvenue ! C'est le réceptionniste sans capacité spécifiée");
        }
    }

    private boolean peutAccepterReservation() {
        if (capaciteRestante > 0) {
            capaciteRestante--; // Réduit la capacité restante après chaque réservation acceptée
            return true;
        } else {
            return false; // Capacité épuisée, ne peut plus accepter de réservations
        }
    }
}