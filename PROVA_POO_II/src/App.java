import classes.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao;
        Scanner in = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        do {
            System.out.println("\n Selecione uma opção \n");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produtos");
            System.out.println("3 - Listar produtos cadastrados");
            System.out.println("4 - Vendas por periodo");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

                if (opcao == 1) {
                    Produto p = new Produto();
                    try{
                    System.out.println("Digite o codigo do produto");
                    p.setCodigoproduto(in.nextInt());

                    } catch(Exception e){
                        in.nextLine();
                        System.out.println("DIGITE APENAS NÚMEROS");
                        voltarMenu(in);
                        continue;
                    }
                    boolean verif = false;

                    for (Produto produto : produtos) {
                        if(produto.getCodigoproduto() == p.getCodigoproduto()){
                        verif = true;
                        }
                    }
                    if(verif != false){
                        in.nextLine();
                        System.out.println("\nPRODUTO JÁ CADASTRADO");
                        voltarMenu(in);
                        continue;
                    }
                    System.out.println("Nome do produto: ");
                    in.nextLine();
                    p.setNome(in.nextLine());
                    try{
                    System.out.println("Quantidade em estoque: ");
                    p.setQtdestoque(in.nextInt());
                    if ( p.getQtdEstoque() <= 0){ 
                        in.nextLine();
                        System.out.println("Quantidade inválida");
                        voltarMenu(in);
                        continue;
                    }
                    }catch(Exception e){
                        in.nextLine();
                        System.out.println("Quantidade inserida inválida");
                        voltarMenu(in);
                        continue;
                    }
                    try{
                    System.out.println("Preço do produto: ");
                    p.setPreco(in.nextInt());
                if (p.getPreco() <= 0){
                    in.nextLine();
                    System.out.println("Preço do produto inválido");
                    voltarMenu(in);
                    continue;
                }   
                    in.nextLine();
                    System.out.println("Produto inserido com sucesso !!!");
                    voltarMenu(in);
                    
                }catch(Exception e){
                    in.nextLine();
                    System.out.println("Preço inserido inválido !");
                    voltarMenu(in);
                }
                produtos.add(p);
            }
             else if (opcao == 2) {

                if (produtos.size() == 0){
                    System.out.println("Sem produtos no cadastrados");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Digite o codigo do produto: ");
                int cod = in.nextInt();
                for (Produto produto : produtos){
                if(cod != produto.getCodigoproduto()){
                    System.out.println("Produto não encontrado!!!");
                }
            }
            System.out.println("------------------------------");
            produtos.stream().filter(s -> s.getCodigoproduto() == cod)
                .forEach(s -> System.out.println("Codigo: " + s.getCodigoproduto() + " " + "Nome: " + s.getNome() + "\nQuantidade em estoque: " + s.getQtdEstoque() + " Preco: "+ s.getPreco()));
            System.out.println("------------------------------");
            in.nextLine();
            voltarMenu(in);
            continue;
               
            }else if (opcao == 3) {

                if (produtos.size() == 0){
                    System.out.println("Nenhum produto existente");
                    voltarMenu(in);
                    continue;
                }  
                Collections.sort(produtos);
                produtos.forEach(System.out::println);
                voltarMenu(in);
            } else if (opcao == 4) {
                try{
                System.out.print("Informe a data inicial no formato 'aaaa-mm-dd': ");
                String dtInicial = in.nextLine();
                System.out.print("Informe a data final no formato 'aaaa-mm-dd': ");
                String dtFinal = in.nextLine();

                Venda vendasBt = new Venda();
                vendasBt.getDatesBetween(vendas, dtInicial, dtFinal);
                voltarMenu(in);

            }catch(Exception e){
                System.out.println("ERROR: NENHUMA VENdA REALIZADA OU DATA INFORMADA É INVALIDA");
                voltarMenu(in);
            }
        }
    
            else if (opcao == 5){
                if (produtos.size() == 0){
                    System.out.println("Não há produtos cadastrados, verifique o estoque");
                    voltarMenu(in);
                    continue;
                }
                Venda v = new Venda();
                Produto p = new Produto();
                
                System.out.println("VENDA REALIZADA NO DIA DE HOJE (DE ACORDO COM O SEU SISTEMA OPERACIONAL)");
                v.setDataVenda(LocalDate.now());
                try{
                    for (Produto produto : produtos) {
                    System.out.println("Digite o codigo do produto: ");
                    int buscarCodigo = in.nextInt();
                    boolean verifc = false;
                    if(buscarCodigo == produto.getCodigoproduto()){
                        p = produto; 
                        verifc = true;

                    } if(verifc != true){
                        in.nextLine();
                        System.out.println("\n ERROR: CÓDIGO INEXISTENTE");
                        voltarMenu(in);
                        continue;
                    }
                } 
               }catch(Exception e){
                in.nextLine();
                System.out.println("ERROR: DIGITE APENAS NÚMEROS");
                voltarMenu(in);
             }
             try{
                 System.out.println("Digite a quantidade para a venda: ");
                 int quantidadeDigitada = in.nextInt();
                 if(quantidadeDigitada <= p.getQtdEstoque()){
                     v.setQuantidadeVendida(quantidadeDigitada);
                     p.removerQuantidadeEstoque(quantidadeDigitada);
                     v.setProdutoVendido(p);
                    }
                    else{
                        System.out.printf("ERROR: Não produtos no estoque o suficiente, há apenas: %s !",p.getQtdEstoque());
                        in.nextLine(); 
                        voltarMenu(in);
                        continue;
                    }
                }catch(Exception e){
                    in.nextLine();
                    System.out.println("ERROR: Apenas números é aceito aqui");
                    voltarMenu(in);
            }
                vendas.add(v);
                System.out.println("Venda realizada com sucesso!"); 
                in.nextLine();
                voltarMenu(in);
            }      
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
                voltarMenu(in);
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");
        in.close();
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}