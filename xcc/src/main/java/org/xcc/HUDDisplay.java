package org.xcc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.xcc.DTOs.CampaignVinDto;

public class HUDDisplay {
    private Label campaignLabel;
    private Stage primaryStage;
    private StackPane stackPane;

    public HUDDisplay() {
        // This line is needed to initialize the JavaFX runtime
        new JFXPanel();

        Platform.runLater(() -> {
            primaryStage = new Stage();
            campaignLabel = new Label();
            campaignLabel.setTextFill(Color.WHITE); // Set text color to white

            // Create an ImageView for the picture
            ImageView imageView = new ImageView(new Image("https://media.wired.com/photos/5932628e2a990b06268aa262/master/w_2560%2Cc_limit/volvo_ios_01-email.jpeg")); // Replace with your image path
            imageView.setFitWidth(1024); // Adjust these values as per your image size
            imageView.setFitHeight(768);

            // Create a Rectangle for the black box
            Rectangle rectangle = new Rectangle(imageView.getFitWidth() / 4, imageView.getFitHeight() / 3);
            rectangle.setFill(Color.BLACK);

            // Create a StackPane to overlay the ImageView, Rectangle and Label
            stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rectangle, campaignLabel);
            StackPane.setAlignment(rectangle, Pos.CENTER); // Position the rectangle in the center


            Scene scene = new Scene(stackPane, 1024, 768);
            primaryStage.setScene(scene);
        });
    }

    public void displayCampaign(CampaignVinDto campaignVin) {
        // This method needs to be called on the JavaFX Application Thread
        Platform.runLater(() -> {
            campaignLabel.setText(campaignVin.campaign.getMessage());
            campaignLabel.setFont(new Font("Arial", 20)); // Set font size to 1/5 of the picture size

            if (!primaryStage.isShowing()) {
                primaryStage.show();
            }
        });
    }
}