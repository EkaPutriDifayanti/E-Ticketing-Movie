package app.tiket.bioskop.src.program.filmList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class daftarFilm extends JDialog{
    private JButton sedangTayangButton;
    private JButton akanTayangButton;
    private JPanel panel_daftarFilm;
    private JPanel btnPanel;
    private JPanel mainlistPanel;
    private JPanel card1;
    private JPanel card2;

    public daftarFilm() {
        sedangTayangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainlistPanel.removeAll();
                mainlistPanel.add(card1);
                mainlistPanel.repaint();
                mainlistPanel.revalidate();
            }
        });
        akanTayangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainlistPanel.removeAll();
                mainlistPanel.add(card2);
                mainlistPanel.repaint();
                mainlistPanel.revalidate();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("daftarFilm");
        frame.setContentPane(new daftarFilm().panel_daftarFilm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
