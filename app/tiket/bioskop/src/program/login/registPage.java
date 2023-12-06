package app.tiket.bioskop.src.program.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class registPage extends JDialog{
    private JTextField tfusername;
    private JTextField tfEmail;
    private JButton btnbatal;
    private JPasswordField pfPassword;
    private JPasswordField pfkonfirmasiPassword;
    private JButton btndaftar;
    private JPanel registpanel;

    public registPage(JFrame parent){
        super(parent);
        setTitle("Buat Akun baru");
        setContentPane(registpanel);
        setMinimumSize(new Dimension(450,474));
        //setMaximumSize(new Dimension(720,1080));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btndaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();

            }
        });
        btnbatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String name = tfusername.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String konfirmasiPassword = String.valueOf(pfkonfirmasiPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Mohon Masukkan Input Dengan Benar",
                    "Coba lagi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(konfirmasiPassword)){
            JOptionPane.showMessageDialog(this,
                    "Mohon Konfirmasi Ulang password Dengan Benar",
                    "Coba lagi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
      user =  adduserToDatabase(name,email,password);
        if (user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,
                    "Gagal melakukan pendaftaran oleh user ",
                    "Coba lagi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User adduserToDatabase(String name,String email,String password){
        User user = null;
        final String url = "jdbc:mysql://localhost:3306/e-tiket_bioskop";
        final String username = "root";
        final String Password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,Password);

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO customer (name,email,password)" + "VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);

            int addRows = preparedStatement.executeUpdate();
            if(addRows > 0 ){
                user = new User();
                user.name = name;
                user.email = email;
                user.password = password;
            }

            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

    public static void main(String[] args){
        registPage myForm = new registPage(null);
        User user = myForm.user;
        if(user != null){
            System.out.println("Daftar Berhasil Atas nama "+user.name);

            loginPage userlog = new loginPage(null);
            //User Ulog = userlog.user;

        }else{
            System.out.println(" Proses Daftar Dibatalkan ");
        }

    }


}
