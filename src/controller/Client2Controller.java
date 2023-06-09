/**
 * @author : Gathsara
 * created : 6/8/2023 -- 5:15 PM
 **/

package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2Controller {
    public AnchorPane client2AP;
    public TextField txtMsgField;
    public TextArea txtChat;
    public AnchorPane emojiPane;

    /*remote socket for client 2*/
    Socket socket2;

    /*dataInputStream and dataOutputStream for streaming data */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String massage = "";
    final int Port = 6001;


    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsgField.getText().trim());
        dataOutputStream.flush();
        txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
        txtChat.appendText("\n Client 2 : " + txtMsgField.getText().trim());
        txtMsgField.clear();
    }

    public void initialize() {

        new Thread(() -> {

            try {

                socket2 = new Socket("localhost", Port);
                dataInputStream = new DataInputStream(socket2.getInputStream());
                dataOutputStream = new DataOutputStream(socket2.getOutputStream());

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
                socket2.close();

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

    public void cryOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE22");
        emojiPane.setVisible(false);
    }
}

