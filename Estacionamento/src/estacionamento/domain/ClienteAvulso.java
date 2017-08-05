package estacionamento.domain;

import java.math.BigInteger;
import java.time.ZoneId;

public class ClienteAvulso extends Cliente {
    
    public ClienteAvulso(String nome, String cpf, String endereco, String telefone, ZoneId zone) {
        super(nome, cpf, endereco, telefone, zone);
    }
    
    @Override
    public BigInteger calcTaxa() {
        BigInteger hoursDifference = duration();
        BigInteger feeAmount = BigInteger.ZERO;

        if (hoursDifference.compareTo(BigInteger.valueOf(3)) == 1) {
            feeAmount.add(BigInteger.valueOf(15));
            return feeAmount;
        }
        
        while (hoursDifference.compareTo(BigInteger.valueOf(0)) == 1) {
            if (hoursDifference.compareTo(BigInteger.valueOf(9)) == 0 || hoursDifference.compareTo(BigInteger.valueOf(9)) == 1) {
                feeAmount.add(hoursDifference.multiply(BigInteger.valueOf(5)));
                hoursDifference.subtract(hoursDifference);
            } else {
                feeAmount.add(BigInteger.valueOf(45));
                hoursDifference.subtract(BigInteger.valueOf(24));
            }
        }
        
        return feeAmount;
    }

}
