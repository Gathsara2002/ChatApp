/**
 * @author : Gathsara
 * created : 6/8/2023 -- 5:15 PM
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

public class Client2Controller {
    public AnchorPane client2AP;
    public TextField txtMsgField;
    public TextArea txtChat;

    /*remote socket for client 1*/
    Socket socket2;

    /*dataInputStream and dataOutputStream for streaming data */
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String massage = "";
    final int Port = 6001;


    public void btnSendOnAction(ActionEvent actionEvent) {
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
                    txtChat.appendText("\n Server : " + massage);
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
    }
}
