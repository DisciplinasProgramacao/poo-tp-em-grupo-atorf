# Correção

## Nota base 9

## Comentários

    - no diagrama, enum ligado com manutenção. No código, a ligação é com o veículo diretamente.
    - manutenção passada no construtor do veículo: errado, pois pode passar manutenção de caminhão para um carro. O correto seria o enum resolver isso ou o construtor resolver isso com o enum.
    - coleção nas rotas do veículo
    - get combustível no veículo é dispensável e piora o encapsulamento. 
    - relatório da frota dando 4 gets no veículo: era melhor chamar um método
    - erros graves de modularidade como
```
veiculoExistente.getManutencao().registrarManutencaoPeriodica(veiculoExistente.getQuilometragem());
```
  veículo recebe ele mesmo como parâmetro? Isto deveria ser um método como veiculo.verificarManutencao() e o registro é um problema privado dele. 
    - teste com carro a álcool aceitou rota de 500km
    - teste com várias rotas não gerou manutenção