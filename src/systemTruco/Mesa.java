package systemTruco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Mesa {

    private ArrayList<Carta> baralho = new ArrayList<>();
    private ArrayList<Jogador> jogador = new ArrayList<>();
    private Carta manilha;
    private Carta vira;
    Jogador vencedor = null;
    private int rodada = 0;
    private int valorTruco = 1;
    private boolean acabar = false;
    private boolean[] empate = new boolean[3];
    private Time vencedorJogo;

    public Mesa(ArrayList<Carta> baralho, ArrayList<Jogador> jogador) {
        this.baralho = baralho;
        this.jogador = jogador;
        rodada = 0;
        vencedor = jogador.get(0);
        manilha();
        distribuirCartas();
    }

    public void iniciaJogo() {
       /* while (rodada < 3) {
            System.out.println("\nVira - " + getVira().getValor() + " " + getVira().getNaipe());
            ordemJogadas();
            verificarGanhador();
            vencedorMao();
            vencedorRodada();
            vencedorJogo();
            limparMesa();
            proximaRodada();
        }*/
    }

    //Limpar carta jogada
    public void limparMesa() {
        for (Jogador aJogador : jogador)
            aJogador.limparCartaJogada();
    }

    //Verificar manilha
    public void manilha() {
        vira = baralho.get(2);
        for (Carta c : baralho) {
            if (c.getId() == (vira.getId() + 1)) {
                manilha = c;
            }
        }
        if (manilha == null)
            manilha = baralho.get(0);
        baralho.remove(0);
    }


    private void distribuirCartas() {
        for (Jogador aJogador : jogador) aJogador.distribuiCartas(baralho);
    }

    //Verificar ganhador, da rodada/ e ou tmeporário
    public void verificarGanhador() {

        vencedor = null;
        int valorMaior = -1;
        for (Jogador aJogador : jogador) {
            if (aJogador.cartaJogada() != null) {
                if (Objects.equals(manilha.getValor(), aJogador.cartaJogada().getValor())) {
                    if (aJogador.cartaJogada().getId() > valorMaior) {
                        valorMaior = aJogador.cartaJogada().getId();
                        vencedor = aJogador;
                    }
                }
            }
        }
        if (vencedor == null) {
            vencedor = jogador.get(0);
            for (Jogador bJogador : jogador) {
                if (bJogador.cartaJogada() != null) {
                    if ((bJogador.cartaJogada().getId() % 10 > vencedor.cartaJogada().getId() % 10) && (bJogador.getTime() != vencedor.getTime())) {
                        vencedor = bJogador;
                    }
                }
            }
        }
    }

    public void vencedorMao() {
        verificarGanhador();
        System.out.println("\nMaior carta - " + vencedor.cartaJogada().getValor() + " " + vencedor.cartaJogada().getNaipe());
        System.out.println("\nJogador da Maior carta - " + vencedor.getNome() + vencedor.getTime().getNome());
        vencedor.getTime().setPlacarRodada();
    }

    public void vencedorRodada() {
            if (jogador.get(0).getTime().getPlacarRodada() > jogador.get(1).getTime().getPlacarRodada()) {
                System.out.println("\n VENCEDOR RODADA - " + jogador.get(0).getTime().getNome());
                jogador.get(0).getTime().setPlacarGeral(valorTruco);
            } else {
                System.out.println("\n VENCEDOR RODADA - " + jogador.get(1).getTime().getNome());
                jogador.get(1).getTime().setPlacarGeral(valorTruco);

            }
        jogador.get(0).getTime().zerarRodada();
        jogador.get(1).getTime().zerarRodada();

    }

    public void vencedorJogo() {
        if (jogador.get(0).getTime().getPlacarGeral() >= 12) {
            vencedorJogo = jogador.get(0).getTime();
            setAcabar();
        }
        if (jogador.get(1).getTime().getPlacarGeral() >= 12) {
            vencedorJogo = jogador.get(0).getTime();
            setAcabar();
        }
    }

    public void ordemJogadas() {
        /*if (vencedor.isJogadorIA()) {
            vencedor.getIA().setManilha(getManilha());
            vencedor.gerarJogada();
            vencedor.visualCartaJogada();}*/
        for (Jogador aJogador : jogador) {
            if (aJogador.getJogada() == null) {
                aJogador.getIA().setManilha(getManilha());
                aJogador.gerarJogada();
                aJogador.cartaJogada().virar();
                aJogador.visualCartaJogada();
                verificarGanhador();
                aJogador.getIA().setVencedorTemp(vencedor);
            }
        }
    }

    public void setRodada() {
        this.rodada = 0;
    }

    public Time getVencedorJogo() {
        return vencedorJogo;
    }

    public void proximaRodada() {
        this.rodada++;
    }

    public boolean isAcabar() {
        return acabar;
    }

    public void setAcabar() {
        this.acabar = true;
    }

    public Carta getVira() {
        return vira;
    }

    public ArrayList<Jogador> getJogador() {
        return jogador;
    }

    public Carta getManilha() {
        return manilha;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public int getRodada() {
        return rodada;
    }

    public int getValorTruco() {
        return valorTruco;
    }

    public void setValorTruco() {
        valorTruco = valorTruco == 1 ? 3 : valorTruco + 3;
    }

    public void setRodadaR(int v) {
        this.rodada = v;
    }
}
