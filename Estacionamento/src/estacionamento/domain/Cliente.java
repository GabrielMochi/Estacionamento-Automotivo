package estacionamento.domain;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class Cliente {
    
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private ZoneId zone;
    private final ZonedDateTime entrada;

    public Cliente(String nome, String cpf, String endereco, String telefone, ZoneId zone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.zone = zone;
        this.entrada = ZonedDateTime.of(LocalDateTime.now(), zone);
    }
    
    /**
     * Calculates the rate at which the specific customer should be charged.
     * <p>
     * Depending on the type of customer (single, covenant or fixed), there may be
     * variations in the final value of the fee. For this reason, it is necessary to implement
     * different calculations depending on the plan by which the customer is inserted (basic, monthly / fortnightly or the discount)
     * 
     * @return A {@code double} with the final value of the implemented rate, for which the customer must pay, not null
     */
    public abstract BigInteger calcTaxa();
    
    public BigInteger duration() {
        return BigInteger.valueOf(Duration.between(entrada.toLocalDateTime(), LocalDateTime.now()).toHours());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ZoneId getZone() {
        return zone;
    }

    public void setZone(ZoneId zone) {
        this.zone = zone;
    }

    public ZonedDateTime getEntrada() {
        return entrada;
    }
    
}
