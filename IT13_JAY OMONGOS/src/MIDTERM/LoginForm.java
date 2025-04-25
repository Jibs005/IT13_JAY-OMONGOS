package MIDTERM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class LoginForm extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;

    private final String usersFilePath = "MIDTERMLOGIN" + File.separator + "users.txt";

    public LoginForm() {
        setTitle("Login Form");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        add(txtPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin = new JButton("Login");
        add(btnLogin, gbc);

        btnLogin.addActionListener((ActionEvent e) -> {
            login();
        });

        ActionListener enterKeyListener = (ActionEvent e) -> {
            login();
        };
        txtUsername.addActionListener(enterKeyListener);
        txtPassword.addActionListener(enterKeyListener);

        createUsersFile();
    }

    private void createUsersFile() {
        File folder = new File("MIDTERMLOGIN");
        
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(usersFilePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("admin,1234\n");
                        writer.write("user1,pass1\n");
                        writer.write("john,doe123\n");
                        writer.write("jayvie,omongos\n");
                        JOptionPane.showMessageDialog(this, "User file created in MIDTERMLOGIN folder with sample data.", "File Created", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Error writing to user file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error creating user file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "User file already exists in MIDTERMLOGIN folder.", "File Exists", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void login() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        boolean isAuthenticated = false;

        try {
            File file = new File(usersFilePath);
            System.out.println("Looking for file at: " + file.getAbsolutePath());

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] credentials = line.split(",");

                    if (credentials.length == 2) {
                        String fileUsername = credentials[0].trim();
                        String filePassword = credentials[1].trim();

                        if (fileUsername.equals(username) && filePassword.equals(password)) {
                            isAuthenticated = true;
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "User file not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
