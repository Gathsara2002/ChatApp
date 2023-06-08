/**
 * @author : Gathsara
 * created : 6/8/2023 -- 5:06 PM
 **/

package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsgField.getText().trim());
        dataOutputStream.flush();
        txtChat.appendText("\n Client 1 : " + txtMsgField.getText().trim());
        txtMsgField.clear();
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
    }
}
