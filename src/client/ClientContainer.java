package client;

import jade.gui.GuiEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientContainer extends Application {
    private AgentClient client;
    private ObservableList<String> observableList;

    public static void main(String[] args) {
        launch(ClientContainer.class);
    }

    public void startContainer() {
        try {
            jade.core.Runtime runtime = jade.core.Runtime.instance();
            jade.core.Profile profile = new jade.core.ProfileImpl(false);
            profile.setParameter(jade.core.Profile.MAIN_HOST, "localhost");
            jade.wrapper.AgentContainer agentContainer = runtime.createAgentContainer(profile);

            jade.wrapper.AgentController clientAgent5= agentContainer.createNewAgent("client5", "client.AgentClient", new Object[]{this});
            clientAgent5.start();
  

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        primaryStage.setTitle("Interface de réservation");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: beige; -fx-text-fill: black;");

        HBox hBox = new HBox();
        hBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        TextField textFieldReservation = new TextField();
        Button buttonReserverButton = new Button("Réserver maintenant");
        buttonReserverButton.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #c0c0c0, #add8e6); -fx-text-fill: black;");
        hBox.getChildren().addAll( buttonReserverButton);
        borderPane.setTop(hBox);

        VBox vBoxReceptionnistes = new VBox();
        for (String receptionniste : client.getRestaurents()) {
            Image image = new Image("file:///C:/Users/EliteBook%20840%20G4/Desktop/Restaurent/src/image/images%20(4).png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50); // Définir la largeur de l'image
            imageView.setPreserveRatio(true); // Conserver les proportions de l'image

            Label labelNomReceptionniste = new Label( receptionniste + ", Capacité Max: " + client.getCapaciteRestaurent(receptionniste));
            VBox vBoxImage = new VBox(imageView, labelNomReceptionniste);

            vBoxReceptionnistes.getChildren().add(vBoxImage);
        }
        borderPane.setRight(vBoxReceptionnistes);

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();
        observableList = FXCollections.observableArrayList();
        ListView<String> listViewMessage = new ListView<>(observableList);

        gridPane.add(listViewMessage, 0, 0);
        vBox.getChildren().add(gridPane);

        borderPane.setCenter(vBox);

        buttonReserverButton.setOnAction(event -> {
            String reservation = textFieldReservation.getText();
            GuiEvent guiEvent = new GuiEvent(this, 1);
            guiEvent.addParameter(reservation);
            client.onGuiEvent(guiEvent);
        });

        Scene scene = new Scene(borderPane, 450, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public AgentClient getClient() {
        return client;
    }

    public void setClient(AgentClient client) {
        this.client = client;
    }

    public void viewMessage(GuiEvent guiEvent) {
        String message = guiEvent.getParameter(0).toString();
        String receptionniste = getClient().getRestaurents()[getClient().getIndexRestaurentActuel()];
        String messageWithReceptionnist = "Réservation envoyée au  : " + receptionniste + "\n" + message;
        Platform.runLater(() -> {
            observableList.add(messageWithReceptionnist);
        });
    }
}
