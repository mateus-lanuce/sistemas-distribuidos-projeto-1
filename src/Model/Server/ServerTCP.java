package Model.Server;

import Interfaces.ServerInterface;
import Interfaces.MessageInterface;
import Model.Message;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP implements Runnable{

  ServerSocket serverSocket;
  Socket ClientSocket;
    ObjectOutputStream output;
    ObjectInputStream input;
  int port;

  public ServerTCP(int port) {
    this.port = port;
  }

  public void run() {
    startServer();
  }

  private void startServer() {
    try {
      serverSocket = new ServerSocket(port);

      System.out.println("Servidor rodando na porta " + serverSocket.getLocalPort());
      System.out.println("HostAddress = " + InetAddress.getLocalHost().getHostAddress());
      System.out.println("HostName = " + InetAddress.getLocalHost().getHostName());

      System.out.println("Aguardando conexão do cliente...");

    while (!serverSocket.isClosed()) {

      ClientSocket = serverSocket.accept();

      System.out.println("Conexão estabelecida com o cliente " + ClientSocket.getInetAddress().getHostAddress());

      output = new ObjectOutputStream(ClientSocket.getOutputStream());
      input = new ObjectInputStream(ClientSocket.getInputStream());

      Message message = (Message) input.readObject();

      System.out.println("Mensagem recebida: " + message.getMessage() + " - eu sou o servidor: " + serverSocket.getLocalPort());


      // fecha a conexão com o cliente se a mensagem for "exit"
      if (message.getMessage().equalsIgnoreCase("exit")) {
        serverSocket.close();
      }
    }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } finally {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
