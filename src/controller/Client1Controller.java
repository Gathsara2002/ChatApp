/**
 * @author : Gathsara
 * created : 6/8/2023 -- 5:06 PM
 **/

package controller;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client1Controller {

    /*remote socket for client 1*/
    Socket socket1;

    /*dataInputStream and dataOutputStream for streaming data */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String massage = "";
    final int Port = 6000;

    public AnchorPane client1AP;
    public TextField txtMsgField;
    public TextArea txtChat;
    public AnchorPane emojiPane;

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsgField.getText().trim());
        dataOutputStream.flush();
        txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
        txtChat.appendText("\n Client 1 : " + txtMsgField.getText().trim());
        txtMsgField.clear();
        emojiPane.setVisible(false);
    }

    public void initialize() {

        new Thread(() -> {
            try {

                socket1 = new Socket("localhost", Port);
                dataOutputStream = new DataOutputStream(socket1.getOutputStream());
                dataInputStream = new DataInputStream(socket1.getInputStream());

                /*get massages from server*/
                while (!massage.equals("exit")) {
                    massage = dataInputStream.readUTF();
                    txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
                    txtChat.appendText(massage);
                }

                /*close streams*/
                dataOutputStream.close();
                dataInputStream.close();

                /*close socket*/
                socket1.close();

                txtMsgField.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        emojiPane.setVisible(false);

    }

    public void cameraOnAction(MouseEvent mouseEvent) {
    }

    public void emojiOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    public void smileEmkOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE0A");
        emojiPane.setVisible(false);
    }

    public void cryEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE22");
        emojiPane.setVisible(false);
    }

    public void laughEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83E\uDD23");
        emojiPane.setVisible(false);
    }

    public void upEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDC4D");
        emojiPane.setVisible(false);
    }

    public void bossEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE0E");
        emojiPane.setVisible(false);
    }

    public void angryEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE21");
        emojiPane.setVisible(false);
    }
}

