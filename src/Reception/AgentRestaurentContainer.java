package Reception;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class AgentRestaurentContainer {

    public static void main(String[] args) {
        try {
            // On récupère l'instance singleton JADE Runtime
            Runtime runtime = Runtime.instance();

            // Crée une implémentation de profil
            Profile profile = new ProfileImpl(false);

            // On spécifie l'hôte réseau sur lequel s'exécute le conteneur principal JADE
            profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");

            // On Crée un conteneur périphérique JADE
            AgentContainer agentContainer = runtime.createAgentContainer(profile);

            // Capacités des réceptionnistes
            Integer capacity1 = Integer.valueOf(1);
            Integer capacity2 = Integer.valueOf(1);
            Integer capacity3 = Integer.valueOf(1);
            Integer capacity4 = Integer.valueOf(1);
            Integer capacity5 = Integer.valueOf(2);
            Integer capacity6 = Integer.valueOf(1);
            Integer capacity7 = Integer.valueOf(2);
            Integer capacity8 = Integer.valueOf(1);
            Integer capacity9 = Integer.valueOf(1);
            Integer capacity10 = Integer.valueOf(1);
            Integer capacity11 = Integer.valueOf(1);
            // Créer et démarrer chaque instance de l'agent réceptionniste avec sa capacité respective
            AgentController Restaurent1 = agentContainer.createNewAgent("Restaurent1",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity1});
            Restaurent1.start();

            AgentController Restaurent2 = agentContainer.createNewAgent("Restaurent2",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity2});
            Restaurent2.start();

            AgentController Restaurent3 = agentContainer.createNewAgent("Restaurent3",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity3});
            Restaurent3.start();

            AgentController Restaurent4 = agentContainer.createNewAgent("Restaurent4",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity4});
            Restaurent4.start();

            AgentController Restaurent5 = agentContainer.createNewAgent("Restaurent5",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity5});
            Restaurent5.start();

            AgentController Restaurent6 = agentContainer.createNewAgent("Restaurent6",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity6});
            Restaurent6.start();

            // Créez et démarrez les instances pour les réceptionnistes 7 à 11 de la même manière que ci-dessus
            AgentController Restaurent7 = agentContainer.createNewAgent("Restaurent7",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity7});
            Restaurent7.start();

            // Répétez le processus pour les réceptionnistes 8 à 11
            AgentController Restaurent8 = agentContainer.createNewAgent("Restaurent8",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity8});
            Restaurent8.start();

            AgentController Restaurent9 = agentContainer.createNewAgent("Restaurent9",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity9});
            Restaurent9.start();

            AgentController Restaurent10 = agentContainer.createNewAgent("Restaurent10",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity10});
            Restaurent10.start();

            AgentController Restaurent11 = agentContainer.createNewAgent("Restaurent11",
                    Reception.AgentRestaurent.class.getName(), new Object[]{capacity11});
            Restaurent11.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}