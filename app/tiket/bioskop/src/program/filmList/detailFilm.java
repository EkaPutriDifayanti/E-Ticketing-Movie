package app.tiket.bioskop.src.program.filmList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class detailFilm {
    private JButton kodeKursiButton;
    private JButton kodeKursiButton3;
    private JButton kodeKursiButton8;
    private JButton kodeKursiButton23;
    private JButton kodeKursiButton15;
    private JButton kodeKursiButton19;
    private JButton pilihTempatDudukButton;
    private JPanel Panel_detailFilm;
    private JTextPane adagiumAdalahFilmDramaTextPane;

    public detailFilm() {
        pilihTempatDudukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
            }
        });
    }

    private void dispose() {
    }
}
