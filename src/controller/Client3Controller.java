/**
 * @author : Gathsara
 * created : 6/8/2023 -- 5:19 PM
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

public class Client3Controller {
    public AnchorPane client3AP;
    public TextField txtMsgField;
    public TextArea txtChat;
    public AnchorPane emojiPane;

    /*remote socket for client 3*/
    Socket socket3;

    /*dataInputStream and dataOutputStream for streaming data */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String massage = "";
    final int Port = 6002;

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsgField.getText().trim());
        dataOutputStream.flush();
        txtChat.appendText("\n Client 3 : " + txtMsgField.getText().trim());
        txtMsgField.clear();
    }

    public void initialize() {

        new Thread(() -> {
            try {

                socket3 = new Socket("localhost", Port);
                dataInputStream = new DataInputStream(socket3.getInputStream());
                dataOutputStream = new DataOutputStream(socket3.getOutputStream());

                /*get massages from server*/
                while (!massage.equals("exit")) {
                    massage = dataInputStream.readUTF();
                    txtChat.appendText(massage);
                }

                /*close streams*/
                dataOutputStream.close();
                dataInputStream.close();

                /*close socket*/
                socket3.close();

                txtMsgField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        emojiPane.setVisible(false);
    }

    public void cryOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE22");
        emojiPane.setVisible(false);
    }

    public void angryEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE21");
        emojiPane.setVisible(false);
    }

    public void bossEmjOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE0E");
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

    public void smileEmkOnAction(MouseEvent mouseEvent) {
        txtMsgField.appendText("\uD83D\uDE0A");
        emojiPane.setVisible(false);
    }

    public void cameraOnAction(MouseEvent mouseEvent) {
    }

    public void emojiOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }
}

