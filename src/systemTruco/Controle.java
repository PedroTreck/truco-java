package systemTruco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class Controle implements ActionListener, MouseListener {

    private Gui gui;
    private Jogo jogo;
    private Mesa mesa;


    public Controle(Jogo jogo, Gui gui, Mesa mesa) {
        this.jogo = jogo;
        this.gui = gui;
        this.mesa = mesa;
        addActionListeners();
        gui.getRodada().addActionListener(this);
        updatefield();
    }

    public void addActionListeners() {
        ArrayList<CartaButton> cartasp1 = this.gui.getCartasPanel1().getCartas();
        ArrayList<CartaButton> maop1 = this.gui.getMaop1().getCartabuttons();
        // ArrayList<CardButton> hidp1 = this.gui.getP1hid().getHandButtons();

        for (int i = 0; i < maop1.size(); i++) {
            maop1.get(i).addActionListener(this);
            maop1.get(i).addMouseListener(this);
        }

        cartasp1.get(0).addActionListener(this);
        cartasp1.get(0).addMouseListener(this);
    }

    private void updatefield() {

        if (mesa.isAcabar()) {
            Object[] options = {"Final de jogo!!!"};
            int choice = JOptionPane.showOptionDialog(gui, "Jogo acabou, o time vencedor é " + mesa.getVencedorJogo().getNome() + "", null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                System.exit(0);
            } else {
                try {
                    Gui.main(null);
                    gui.setVisible(false);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        gui.setVira(mesa.getVira());
        gui.setValorPlacar(mesa.getValorTruco());
        gui.setValorRodada(mesa.getRodada() + 1);

        gui.getSp1().remove(gui.getMaop1());
        gui.getPanelP1().remove(gui.getSp1());
        gui.setMaop1(new MaoPanel(jogo.getJogador().get(0)));
        JScrollPane sp1 = new JScrollPane(gui.getMaop1());
        sp1.setPreferredSize(new Dimension(500, 150));
        sp1.setBorder(null);
        sp1.getViewport().setOpaque(false);
        sp1.setOpaque(false);
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setSp1(sp1);
        gui.getPanelP1().add(gui.getSp1(), BorderLayout.SOUTH);
        gui.revalidate();

        gui.getSp2().remove(gui.getMaop2());
        gui.getPanelP2().remove(gui.getSp2());
        gui.setMaop2(new MaoPanel(jogo.getJogador().get(3)));
        JScrollPane sp2 = new JScrollPane(gui.getMaop2());
        sp2.setPreferredSize(new Dimension(500, 150));
        sp2.setBorder(null);
        sp2.getViewport().setOpaque(false);
        sp2.setOpaque(false);
        sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setSp2(sp2);
        gui.getPanelP2().add(gui.getSp2(), BorderLayout.NORTH);
        gui.revalidate();

        gui.getSp3().remove(gui.getMaop3());
        gui.getPanelP3().remove(gui.getSp3());
        gui.setMaop3(new MaoPanel(jogo.getJogador().get(2)));
        JScrollPane sp3 = new JScrollPane(gui.getMaop3());
        sp3.setPreferredSize(new Dimension(500, 150));
        sp3.setBorder(null);
        sp3.getViewport().setOpaque(false);
        sp3.setOpaque(false);
        sp3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setSp3(sp3);
        gui.getPanelP3().add(gui.getSp3(), BorderLayout.WEST);
        gui.revalidate();

        gui.getSp4().remove(gui.getMaop4());
        gui.getPanelP4().remove(gui.getSp4());
        gui.setMaop4(new MaoPanel(jogo.getJogador().get(1)));
        JScrollPane sp4 = new JScrollPane(gui.getMaop4());
        sp4.setPreferredSize(new Dimension(500, 150));
        sp4.setBorder(null);
        sp4.getViewport().setOpaque(false);
        sp4.setOpaque(false);
        sp4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setSp4(sp4);
        gui.getPanelP4().add(gui.getSp4(), BorderLayout.EAST);
        gui.revalidate();


        if (jogo.getJogador().get(0).getJogada() != null) {
            if (gui.getCartasPanel1() != null)
                gui.getPanelP1().remove(gui.getCartasPanel1());
            gui.setCartasPanel1(new CartasPanel(jogo.getJogador().get(0)));
            gui.getPanelP1().add(gui.getCartasPanel1(), BorderLayout.NORTH);
        }

        if (jogo.getJogador().get(3).getJogada() != null) {
            if (gui.getCartasPanel2() != null)
                gui.getPanelP2().remove(gui.getCartasPanel2());
            gui.setCartasPanel2(new CartasPanel(jogo.getJogador().get(3)));
            gui.getPanelP2().add(gui.getCartasPanel2(), BorderLayout.SOUTH);
        }

        if (jogo.getJogador().get(2).getJogada() != null) {
            if (gui.getCartasPanel3() != null)
                gui.getPanelP3().remove(gui.getCartasPanel3());
            gui.setCartasPanel3(new CartasPanel(jogo.getJogador().get(2)));
            gui.getPanelP3().add(gui.getCartasPanel3(), BorderLayout.SOUTH);
        }

        if (jogo.getJogador().get(1).getJogada() != null) {
            if (gui.getCartasPanel4() != null)
                gui.getPanelP4().remove(gui.getCartasPanel4());
            gui.setCartasPanel4(new CartasPanel(jogo.getJogador().get(1)));
            gui.getPanelP4().add(gui.getCartasPanel4(), BorderLayout.SOUTH);
        }

       /* if (gui.getP1().getField().getGraveyard().size() > 0) {
            String url;
            if (gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size() - 1) instanceof MonsterCard) {
                url = "Cards Images Database/Monsters/" + gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size() - 1).getName() + ".png";
            } else {
                url = "Cards Images Database/Spells/" + gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size() - 1).getName() + ".png";
            }
            ImageIcon img = new ImageIcon(url);
            Image img2 = img.getImage();
            Image newimg = img2.getScaledInstance(62, 91, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            gui.getGravep1().setIcon(newIcon);
        }*/

        addActionListeners();
        gui.revalidate();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() instanceof Rodada) {
            updatefield();
        }
        if (arg0.getSource() instanceof CartaButton) {
            mesa.limparMesa();
            Carta carta = ((CartaButton) arg0.getSource()).getCarta();
            jogo.getJogador().get(0).setJogada(carta);
            jogo.getJogador().get(0).getCartasJogador().remove(carta);
            mesa.ordemJogadas();
            updatefield();
            return;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
