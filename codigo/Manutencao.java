package codigo;

import java.io.*;
import java.util.*;

public interface Manutencao {
    boolean precisaManutencaoPeriodica(double kmAtual);
    boolean precisaTrocaPneus(double kmAtual);
    double calcularCusto(double kmAtual);
    void registrarManutencaoPeriodica(double kmAtual);
    void registrarTrocaPneus(double kmAtual);
}