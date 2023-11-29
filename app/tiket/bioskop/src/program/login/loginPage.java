package app.tiket.bioskop.src.program.login;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginPage extends JDialog {
    private JPanel loginPanel;
    private JTextField tfEmail;
    private JButton btnmasuk;
    private JPasswordField pfPassword;
    private JButton btndaftar;

    public loginPage(JFrame parent) {
        super(parent);
        setTitle("User Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnmasuk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

               user = getAuthenticatedUser(email,password);

               if(user != null){
                   dispose();
               }else{
                   JOptionPane.showMessageDialog(loginPage.this,
                           "Email atau Password anda Salah",
                           "Coba lagi",
                           JOptionPane.ERROR_MESSAGE);
               }
            }
        });
        btndaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                // Membuka jendela registPage
                registPage registerForm = new registPage(null);
                User newUser = registerForm.user;

//                if (newUser != null) {
//                    // Di sini Anda dapat melakukan sesuatu setelah pengguna mendaftar,
//                    // misalnya login otomatis atau menampilkan pesan bahwa pendaftaran berhasil
//                    // atau memperbarui UI di halaman login
//                }

                // Membuka kembali jendela loginPage setelah pendaftaran selesai
                new loginPage(null);
            }
        });
        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String url = "jdbc:mysql://localhost:3306/e-tiket_bioskop";
        final String username = "root";
        final String Password = "";

        try{
            Connection connection = DriverManager.getConnection(url,username,Password);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM customer WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args){
        loginPage loginform = new loginPage(null);
        User user = loginform.user;
        if(user != null){
            System.out.println("Proses Login Berhasil!!");
            System.out.println("Nama        :"+user.name);
            System.out.println("Email       :"+user.email);

        }else{
            System.out.println("Proses Login Gagal");
        }
    }

}
