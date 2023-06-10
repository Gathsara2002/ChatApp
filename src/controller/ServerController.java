/**
 * @author : Gathsara
 * created : 6/6/2023 -- 8:31 PM
 **/

package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    public AnchorPane serverAP;
    public TextArea txtChat;
    public TextField txtMsgField;

    ServerSocket serverSocket, serverSocket2, serverSocket3;

    /*data input streams for catch data*/
    DataInputStream dataInputStream1, dataInputStream2, dataInputStream3;

    /*data output streams for send data*/
    DataOutputStream dataOutputStream1, dataOutputStream2, dataOutputStream3;

    /*local sockets for each client*/
    Socket localSocket1;
    Socket localSocket2;
    Socket localSocket3;

    /*local ports for clients*/
    final int Port1 = 6000;
    final int Port2 = 6001;
    final int Port3 = 6002;

    /*get massage from clients*/
    String massage1 = "", massage2 = "", massage3 = "";

    /*store client's massages separately*/
    String chat1 = "server connected", chat2 = "server connected", chat3 = "server connected";

    /*status of sockets*/
    boolean isSocket1IsConnected = false;
    boolean isSocket2IsConnected = false;
    boolean isSocket3IsConnected = false;


    public void txtMsg(ActionEvent actionEvent) {
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {

        String reply = txtMsgField.getText().trim();
        txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
        txtChat.appendText("\n Server : " + txtMsgField.getText().trim());

        if (isSocket1IsConnected) {
            dataOutputStream1.writeUTF("\n Server : " + reply);
            dataOutputStream1.flush();
        }

        if (isSocket2IsConnected) {
            dataOutputStream2.writeUTF("\n Server : " + reply);
            dataOutputStream2.flush();
        }

        if (isSocket3IsConnected) {
            dataOutputStream3.writeUTF("\n Server : " + reply);
            dataOutputStream3.flush();
        }

        txtMsgField.clear();
    }

    public void initialize() {

        /*use threads for load clients*/

        /*client 1 data transfer*/

        new Thread(() -> {
            try {

                /*accept client req and server start*/
                serverSocket = new ServerSocket(Port1);
                txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
                txtChat.appendText("\n * Server is started..");
                localSocket1 = serverSocket.accept();
                isSocket1IsConnected = true;
                txtChat.appendText("\n Client 1  accepted");

                /* accepting input and output streams */
                dataInputStream1 = new DataInputStream(localSocket1.getInputStream());
                dataOutputStream1 = new DataOutputStream(localSocket1.getOutputStream());

                while (!massage1.equals("exit")) {

                    /*get client 1 massage to server*/
                    massage1 = dataInputStream1.readUTF();
                    txtChat.appendText("\n Client 1 : " + massage1.trim());

                    /*store massage to another variable*/
                    chat1 = massage1;

                    if (isSocket2IsConnected) {
                        /*send massage to client 2*/
                        dataOutputStream2.writeUTF("\n client 1 : " + chat1.trim());
                        dataOutputStream2.flush();
                    }

                    if (isSocket3IsConnected) {
                        /*send massage to client 3*/
                        dataOutputStream3.writeUTF("\n client 1 : " + chat1.trim());
                        dataOutputStream3.flush();
                    }


                }

                localSocket1.close();
                dataInputStream1.close();
                dataInputStream2.close();
                dataInputStream3.close();
                dataOutputStream1.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        /*client 2 data transfer*/

        new Thread(() -> {
            try {

                serverSocket2 = new ServerSocket(Port2);
                localSocket2 = serverSocket2.accept();
                isSocket2IsConnected = true;
                txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
                txtChat.appendText("\n Client 2  accepted");

                dataInputStream2 = new DataInputStream(localSocket2.getInputStream());
                dataOutputStream2 = new DataOutputStream(localSocket2.getOutputStream());

                while (!massage2.equals("exit")) {

                    /*get client 2 massage to server*/
                    massage2 = dataInputStream2.readUTF();
                    txtChat.appendText("\n Client 2 : " + massage2.trim());

                    /*store massage to another variable*/
                    chat2 = massage2;

                    if (isSocket1IsConnected) {
                        /*send massage to client 1*/
                        dataOutputStream1.writeUTF("\n client 2 : " + chat2.trim());
                        dataOutputStream1.flush();
                    }

                    if (isSocket3IsConnected) {
                        /*send massage to client 3*/
                        dataOutputStream3.writeUTF("\n client 2 : " + chat2.trim());
                        dataOutputStream3.flush();
                    }

                }

                localSocket2.close();
                dataInputStream1.close();
                dataInputStream2.close();
                dataInputStream3.close();
                dataOutputStream1.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        /*client 3 data transfer*/

        new Thread(() -> {
            try {

                serverSocket3 = new ServerSocket(Port3);
                localSocket3 = serverSocket3.accept();
                isSocket3IsConnected = true;
                txtChat.setStyle("-fx-font-size: 20px;" + "-fx-font-family : Cambria");
                txtChat.appendText("\n Client 3  accepted");

                dataInputStream3 = new DataInputStream(localSocket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(localSocket3.getOutputStream());

                while (!massage3.equals("exit")) {

                    /*get client 3 massage to server*/
                    massage3 = dataInputStream3.readUTF();
                    txtChat.appendText("\n Client 3 : " + massage3.trim());

                    /*store massage to another variable*/
                    chat3 = massage3;

                    if (isSocket1IsConnected) {
                        /*send massage to client 1*/
                        dataOutputStream1.writeUTF("\n client 3 : " + chat3.trim());
                        dataOutputStream1.flush();
                    }

                    if (isSocket2IsConnected) {
                        /*send massage to client 2*/
                        dataOutputStream2.writeUTF("\n client 3 : " + chat3.trim());
                        dataOutputStream2.flush();
                    }
                }

                localSocket3.close();
                dataInputStream1.close();
                dataInputStream2.close();
                dataInputStream3.close();
                dataOutputStream1.close();
                dataOutputStream2.close();
                dataOutputStream3.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

