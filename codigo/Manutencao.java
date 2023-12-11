package codigo;

import java.io.*;
import java.util.*;

public interface Manutencao {
    boolean precisaManutencaoPeriodica(int kmAtual);
    boolean precisaTrocaPneus(int kmAtual);
    void registrarManutencaoPeriodica(int kmAtual);
    void registrarTrocaPneus(int kmAtual);
}