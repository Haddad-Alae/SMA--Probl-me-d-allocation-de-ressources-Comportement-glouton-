package client;

import java.util.Arrays;
import java.util.Comparator;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentClient extends GuiAgent {
    private static final long serialVersionUID = 1L;
    private ClientContainer gui;
    private String[] Restaurents = {"Restaurent4", "Restaurent1", "Restaurent2", "Restaurent3", "Restaurent5",
                                    "Restaurent6", "Restaurent7", "Restaurent8", "Restaurent9", "Restaurent10", "Restaurent11"};
    private int indexRestaurentActuel = 0;
    private boolean reservationEnCours = true;
    private int nombreTentatives = 1; // Ajout de la variable pour le nombre d'appels
    private boolean reservationAcceptee = false;

    protected void setup() {
        gui = (ClientContainer) getArguments()[0];
        gui.setClient(this);
        System.out.println("Bienvenue ! C'est le " + getLocalName());
        // Trie les réceptionnistes en fonction de la capacité
        Arrays.sort(Restaurents, Comparator.comparingInt(this::getCapaciteRestaurent).reversed());

        addBehaviour(new CyclicBehaviour() {
            private static final long serialVersionUID = 1L;
            @Override
            public void action() {
                if (!reservationEnCours) {
                    // Arrêter le comportement si aucune réservation n'est en cours
                    return;
                }

                ACLMessage message = receive();
                if (message != null) {
                    System.out.println("La réponse : " + message.getContent());
                    GuiEvent guiEvent = new GuiEvent(this, 1);
                    guiEvent.addParameter(message.getContent());
                    gui.viewMessage(guiEvent);
                    if (message.getPerformative() == ACLMessage.REJECT_PROPOSAL) {
                        envoyerAuRestaurentSuivant(message.getContent());
                    } else if (message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL && !reservationAcceptee) {
                        reservationAcceptee = true;
                        System.out.println("Nombre total d'appels avant de trouver une place : " + nombreTentatives);
                    }
                } else {
                    // Aucun message reçu, vérifier si toutes les tentatives ont échoué
                    if (indexRestaurentActuel == Restaurents.length - 1) {
                        reservationEnCours = false;
                        if (!reservationAcceptee) {
                            System.out.println("Nombre total d'appels avant de trouver une place : " + nombreTentatives);
                        }
                    }
                }
            }
        });
    }

    public int getCapaciteRestaurent(String Restaurent) {
        switch (Restaurent) {
            case "Restaurent1":
                return 1;
            case "Restaurent2":
                return 1;
            case "Restaurent3":
                return 1;
            case "Restaurent4":
                return 1;
            case "Restaurent5":
                return 2;
            case "Restaurent6":
                return 1;
            case "Restaurent7":
                return 2;
            case "Restaurent8":
                return 1;
            case "Restaurent9":
                return 1;
            case "Restaurent10":
                return 1;
            case "Restaurent11":
                return 1;
            default:
                return 0;
        }
    }

    private void envoyerAuRestaurentSuivant(String reservation) {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        aclMessage.setContent(reservation);

        // Calculer l'index du prochain réceptionniste en utilisant l'ordre trié
        indexRestaurentActuel = (indexRestaurentActuel + 1) % Restaurents.length;
        nombreTentatives++; // Incrémentation du nombre d'appels
        if (indexRestaurentActuel == 0) {
            // Si on a parcouru tous les réceptionnistes sans succès, arrêter les tentatives
            reservationEnCours = false;
            if (!reservationAcceptee) {
                System.out.println("Nombre de coups de fils avant de trouver une place : " + nombreTentatives);
            }
        }
        aclMessage.addReceiver(new AID(Restaurents[indexRestaurentActuel], AID.ISLOCALNAME));
        send(aclMessage);
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        if (guiEvent.getType() == 1) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
            String reservation = guiEvent.getParameter(0).toString();
            aclMessage.setContent(reservation);
            aclMessage.addReceiver(new AID(Restaurents[indexRestaurentActuel], AID.ISLOCALNAME));
            send(aclMessage);
        }
    }

    public String[] getRestaurents() {
        return Restaurents;
    }

    public int getIndexRestaurentActuel() {
        return indexRestaurentActuel;
    }
}
