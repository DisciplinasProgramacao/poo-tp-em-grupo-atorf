package codigo;

import java.io.*;
import java.util.*;

public class MVan implements Manutencao {
    private static final int KM_MANUTENCAO_PERIODICA = 10000;
    private static final int KM_TROCA_PNEUS = 12000;
    private int kmUltimaManutencaoPeriodica = 0;
    private int kmUltimaTrocaPneus = 0;

    @Override
    public boolean precisaManutencaoPeriodica(int kmAtual) {
        return kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA;
    }

    @Override
    public boolean precisaTrocaPneus(int kmAtual) {
        return kmAtual - kmUltimaTrocaPneus >= KM_TROCA_PNEUS;
    }

    @Override
    public void registrarManutencaoPeriodica(int kmAtual) {
        this.kmUltimaManutencaoPeriodica = kmAtual;
    }

    @Override
    public void registrarTrocaPneus(int kmAtual) {
        this.kmUltimaTrocaPneus = kmAtual;
    }
}

