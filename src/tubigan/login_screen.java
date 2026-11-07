
package tubigan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Tubigan customer login screen.
 * Opened from WelcomeScreen when the Customer option is chosen.
 */
public class login_screen extends JFrame {

    private static final Color FIELD_FILL = new Color(217, 217, 217);
    private static final Color FIELD_EDGE = new Color(150, 150, 165);

    private final RoundedTextField emailField = new RoundedTextField();
    private final RoundedPasswordField passwordField = new RoundedPasswordField();

    public login_screen() {

        setTitle("Tubigan - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcome_screen.BackgroundPanel root =
                new welcome_screen.BackgroundPanel();

        root.setLayout(null);
        root.setPreferredSize(new Dimension(655, 462));

        setContentPane(root);

        // ---- Login Card ----
        welcome_screen.RoundedPanel card =
                new welcome_screen.RoundedPanel(26);

        card.setLayout(null);
        card.setBounds(192, 78, 270, 306);

        root.add(card);

        // ---- Heading ----
        JLabel title = new JLabel("Welcome Back!");
        title.setFont(new Font("Poppins", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(28, 14, 220, 30);
        card.add(title);

        JLabel subtitle = new JLabel("Sign in to continue");
        subtitle.setFont(new Font("Poppins", Font.PLAIN, 11));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(28, 42, 220, 16);
        card.add(subtitle);

        // ---- Email / Username ----
        card.add(smallLabel("Email or Username", 34, 68));

        emailField.setBounds(28, 84, 214, 30);
        card.add(emailField);

        // ---- Password ----
        card.add(smallLabel("Password", 34, 124));

        passwordField.setBounds(28, 140, 214, 30);
        card.add(passwordField);

        // ---- Show / Hide Password ----
        EyeButton eye = new EyeButton();

        eye.setBounds(208, 145, 26, 20);

        eye.addActionListener(e -> {
            boolean show = eye.toggle();

            passwordField.setEchoChar(
                    show ? (char) 0 : '\u2022'
            );
        });

        card.add(eye);

        // ---- Forgot Password ----
        JLabel forgot = new JLabel(
                "Forgot password?",
                SwingConstants.RIGHT
        );

        forgot.setFont(new Font("Poppins", Font.PLAIN, 9));
        forgot.setForeground(Color.WHITE);
        forgot.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        forgot.setBounds(112, 173, 130, 14);

        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        login_screen.this,
                        "Forgot password - not implemented yet"
                );
            }
        });

        card.add(forgot);

        // ---- Login Button ----
        BlueButton login = new BlueButton("Log in");

        login.setBounds(28, 200, 214, 30);

        login.addActionListener(e -> handleLogin());

        card.add(login);

        // Pressing Enter triggers login
        getRootPane().setDefaultButton(login);

        // ---- Footer ----
        JLabel footer = new JLabel(
                "<html>Don't have an account yet? " +
                        "<font color='#5ce1ff'>Create an account</font>" +
                        "</html>",
                SwingConstants.CENTER
        );

        footer.setFont(new Font("Poppins", Font.PLAIN, 9));
        footer.setForeground(Color.WHITE);
        footer.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        footer.setBounds(0, 262, 270, 16);

        footer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        login_screen.this,
                        "Create account - not implemented yet"
                );
            }
        });

        card.add(footer);

        // ---- Frame Settings ----
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // ---- Small Labels ----
    private JLabel smallLabel(String text, int x, int y) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Poppins", Font.PLAIN, 9));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 180, 14);

        return label;
    }

    // ---- Login Authentication ----
    private void handleLogin() {

        String user = emailField.getText().trim();

        String pass = new String(
                passwordField.getPassword()
        );

        if (user.isEmpty() || pass.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your email/username and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // TODO: Replace with actual database authentication.

        JOptionPane.showMessageDialog(
                this,
                "Welcome, " + user + "!"
        );

        // Example:
        // new CustomerDashboard().setVisible(true);
        // dispose();
    }

    // =========================================================
    // Rounded Gray Input Fields
    // =========================================================

    private static void paintFieldBackground(
            Graphics g,
            JComponent component
    ) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(FIELD_FILL);

        g2.fill(
                new RoundRectangle2D.Double(
                        0, 0,
                        component.getWidth() - 1,
                        component.getHeight() - 1,
                        14, 14
                )
        );

        g2.setColor(FIELD_EDGE);
        g2.setStroke(new BasicStroke(1.5f));

        g2.draw(
                new RoundRectangle2D.Double(
                        1, 1,
                        component.getWidth() - 3,
                        component.getHeight() - 3,
                        14, 14
                )
        );

        g2.dispose();
    }

    // ---- Rounded Text Field ----

    static class RoundedTextField extends JTextField {

        RoundedTextField() {

            setOpaque(false);

            setBorder(
                    BorderFactory.createEmptyBorder(
                            0, 12, 0, 12
                    )
            );

            setFont(new Font("Poppins", Font.PLAIN, 12));
            setForeground(new Color(30, 30, 40));
            setCaretColor(new Color(30, 30, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {

            paintFieldBackground(g, this);

            super.paintComponent(g);
        }
    }

    // ---- Rounded Password Field ----

    static class RoundedPasswordField extends JPasswordField {

        RoundedPasswordField() {

            setOpaque(false);

            setBorder(
                    BorderFactory.createEmptyBorder(
                            0, 12, 0, 36
                    )
            );

            setFont(new Font("Poppins", Font.PLAIN, 12));
            setForeground(new Color(30, 30, 40));
            setCaretColor(new Color(30, 30, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {

            paintFieldBackground(g, this);

            super.paintComponent(g);
        }
    }

    // =========================================================
    // Eye Icon (Show / Hide Password)
    // =========================================================

    static class EyeButton extends JButton {

        private boolean visiblePassword = false;

        EyeButton() {

            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setFocusable(false);

            setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );
        }

        boolean toggle() {

            visiblePassword = !visiblePassword;

            repaint();

            return visiblePassword;
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(new Color(30, 30, 40));

            g2.setStroke(
                    new BasicStroke(
                            1.5f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            int w = getWidth();
            int h = getHeight();

            // Eye outline
            g2.draw(
                    new Ellipse2D.Double(
                            3,
                            h / 2.0 - 6,
                            w - 6,
                            12
                    )
            );

            // Eye pupil
            g2.fill(
                    new Ellipse2D.Double(
                            w / 2.0 - 3,
                            h / 2.0 - 3,
                            6,
                            6
                    )
            );

            // Slash when password is hidden
            if (!visiblePassword) {
                g2.drawLine(
                        5, h - 4,
                        w - 5, 4
                );
            }

            g2.dispose();
        }
    }

    // =========================================================
    // Blue Login Button
    // =========================================================

    static class BlueButton extends JButton {

        private boolean hover = false;

        BlueButton(String text) {

            super(text);

            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);

            setForeground(Color.WHITE);

            setFont(new Font("Poppins", Font.BOLD, 13));

            setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON
            );

            // Button background
            g2.setColor(
                    hover
                            ? new Color(30, 30, 255)
                            : new Color(0, 0, 255)
            );

            g2.fill(
                    new RoundRectangle2D.Double(
                            0, 0,
                            getWidth() - 1,
                            getHeight() - 1,
                            14, 14
                    )
            );

            // Button border
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1.5f));

            g2.draw(
                    new RoundRectangle2D.Double(
                            1, 1,
                            getWidth() - 3,
                            getHeight() - 3,
                            14, 14
                    )
            );

            // Button text
            g2.setFont(getFont());

            FontMetrics fm = g2.getFontMetrics();

            int x = (getWidth() -
                    fm.stringWidth(getText())) / 2;

            int y = (getHeight() -
                    fm.getHeight()) / 2 +
                    fm.getAscent();

            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }

    // =========================================================
    // Main Method
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new login_screen().setVisible(true)
        );
    }
}
