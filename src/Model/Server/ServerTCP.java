package Model.Server;

import Interfaces.ServerInterface;
import Interfaces.MessageInterface;
import Model.Message;
import Model.NodeServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTCP implements Runnable, ServerInterface {

  ServerSocket serverSocket;
  Socket ClientSocket;
  NodeServer nextNodeServer;
  ObjectOutputStream output;
  ObjectInputStream input;
  private ArrayList<String> messagesLog = new ArrayList<>();
  NodeServer Iam;

  public ServerTCP(NodeServer Iam, NodeServer nextNodeServer) {
    this.Iam = Iam;
    this.nextNodeServer = nextNodeServer;
  }

  public void run() {
    startServer();
  }

  /**
   * @param message the message to be sent
   * @return
   */
  @Override
  public void sendMessage(MessageInterface message) {

    // não enviar mensagens para mim mesmo
    if (message.getReceiver().name().equals(Iam.name())) {
      return;
    }

    try (Socket socket = new Socket(nextNodeServer.ip(), nextNodeServer.port())) {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Enviando mensagem para o próximo nó: " + nextNodeServer.name() + " - eu sou o servidor: " + Iam.name());

        //se a mensagem for do tipo broadcast, modificar o receiver para o próximo nó
        if (message.getType().equals("broadcast")) {
            message.setReceiver(nextNodeServer);
        }

        out.writeObject(message);
        out.flush();

        saveLog((Message) message);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
      System.out.println("Conexão encerrada");
    }
  }

  private boolean isMessageForMe(MessageInterface messageInterface) {
    Message message = (Message) messageInterface;
    return message.getReceiver().name().equals(Iam.name());
  }

  private void receiveMessage(MessageInterface message) {

    //verificar se a mensagem é para mim
    if (isMessageForMe(message)) {
      saveLog(message);

      //se a mensagem for um broadcast, enviar para o próximo nó
        if (message.getType().equals("broadcast")) {
            sendMessage(message);
        }
    } else {
      sendMessage(message);
    }

  }

  public ArrayList<String> getLog() {
    return messagesLog;
  }

  private void saveLog(MessageInterface message) {
      String log;
      if (isMessageForMe(message) || message.getType().equals("broadcast")) {

        log = "Messagem Recebida: " + message.getMessage();
        log += "\nCriada em: " + message.getDateTime();
        log += "\nEnviada por: " + message.getSender().name();
        log += "\nRecebida por mim: " + message.getReceiver().name();

        messagesLog.add(log);
      } else {
        log = "Messagem Recebida: " + message.getMessage();
        log += "\nCriada em: " + message.getDateTime();
        log += "\nEnviada por: " + message.getSender().name();
        log += "\nRedirecionada para: " + message.getReceiver().name();

        messagesLog.add(log);
      }

  }

  private void startServer() {
    try {
      serverSocket = new ServerSocket(Iam.port());

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

      //receber a mensagem
      receiveMessage(message);

      // fecha a conexão com o cliente se a mensagem for "exit" e for para mim
      if (message.getMessage().equalsIgnoreCase("exit") && isMessageForMe(message)) {
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
