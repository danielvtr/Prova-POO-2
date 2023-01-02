package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Venda {

    private LocalDate dataVenda;
    private Produto produtoVendido;
    private int quantidadeVendida;

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public List<Venda> getDatesBetween(List<Venda> vendas, String dataInicial, String dataFinal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dtInicio = LocalDate.parse(dataInicial, formatter);
        LocalDate dtFinal = LocalDate.parse(dataFinal, formatter);

        List<Venda> vendaList = vendas.stream()
                 .filter(c -> c.getDataVenda().isAfter(dtInicio) ||
                         c.getDataVenda().isBefore(dtFinal) ||
                         c.getDataVenda().isEqual(dtInicio) ||
                         c.getDataVenda().isEqual(dtFinal))
                .collect(Collectors.toList());
        
        if(vendaList.isEmpty()){
            System.out.println("Não há vendas no período informado!");
        } else {
            System.out.printf(String.format("Data") + 
                              String.format("%15s", "Produto") +
                              String.format("%55s", "Qt.Vendida\n"));

            vendaList.forEach((venda) -> {
            System.out.printf(String.format("%s", venda.getDataVenda()) +
                              String.format("%5s", venda.getProdutoVendido().getNome()) +
                              String.format("%55s\n", venda.getQuantidadeVendida()));
            });
        }
        return vendaList;       
    }  
}