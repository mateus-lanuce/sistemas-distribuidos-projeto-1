package Model;

import java.io.Serializable;

/**
 * Representa um nó da rede com seu nome, porta e endereço IP.
 * é um record, ou seja, uma classe imutável que possui apenas métodos de acesso e construtor.
 */
public record NodeServer(String ip, int port, String name) implements Serializable {
    public NodeServer {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Port cannot be negative");
        }
        if (ip == null || ip.isBlank()) {
            throw new IllegalArgumentException("IP address cannot be null or empty");
        }
    }
}
