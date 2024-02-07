package org.example;



import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.xcc.DTOs.CampaignVinDto;


public class HUDDisplay {
    private Label campaignLabel;
    private Stage primaryStage;

    public HUDDisplay() {
        // This line is needed to initialize the JavaFX runtime
        new JFXPanel();

        Platform.runLater(() -> {
            primaryStage = new Stage();
            campaignLabel = new Label();
            Scene scene = new Scene(campaignLabel, 200, 100);
            primaryStage.setScene(scene);
        });
    }

    public void displayCampaign(CampaignVinDto campaignVin) {
        // This method needs to be called on the JavaFX Application Thread
        Platform.runLater(() -> {
            campaignLabel.setText(campaignVin.campaign.getMessage());
            if (!primaryStage.isShowing()) {
                primaryStage.show();
            }
        });
    }
}