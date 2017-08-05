package estacionamento.domain;

import java.math.BigInteger;
import java.time.ZoneId;

public class ClienteFixo extends Cliente {
    
    private Payment typeOfFee;

    public ClienteFixo(String nome, String cpf, String endereco, String telefone, ZoneId zone, Payment typeOfFee) {
        super(nome, cpf, endereco, telefone, zone);
        this.typeOfFee = typeOfFee;
    }
    
    @Override
    public BigInteger calcTaxa() {
        BigInteger hoursDifference = duration();
        BigInteger feeAmount = BigInteger.ZERO;
        BigInteger variation;
        
        if (typeOfFee == Payment.QUINZENAL) {
            variation = hoursDifference.divide(BigInteger.valueOf(15));
            variation = variation.add(BigInteger.ONE);
            feeAmount = variation.multiply(BigInteger.valueOf(170));
        } else if (typeOfFee == Payment.MENSAL) {
            variation = hoursDifference.divide(BigInteger.valueOf(15));
            variation = variation.add(BigInteger.ONE);
            feeAmount = variation.multiply(BigInteger.valueOf(310));
        }
        
        return feeAmount;
    }
    
    public Payment getTypeOfFee() {
        return typeOfFee;
    }

    public void setTypeOfFee(Payment typeOfFee) {
        this.typeOfFee = typeOfFee;
    }
    
    public enum Payment {
        MENSAL, QUINZENAL;
    }
    
}
