
package tubigan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 * Tubigan welcome screen.
 * Run the main method in IntelliJ.
 */
public class welcome_screen extends JFrame {

    private static final String LOGO_PATH = "/logo.png";
    private static final Color CARD_FILL = new Color(88, 92, 190, 235);
    private static final Color BUTTON_FILL = new Color(228, 227, 243);
    private static final Color BUTTON_SHADOW = new Color(40, 30, 140);
    private static final Color TEXT_DARK = new Color(20, 20, 30);

    public welcome_screen() {

        setTitle("Tubigan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel root = new BackgroundPanel();
        root.setLayout(null);
        root.setPreferredSize(new Dimension(655, 462));

        setContentPane(root);

        // ---- Logo ----
        JComponent logo = createLogo();
        logo.setBounds(285, 28, 90, 60);
        root.add(logo);

        // ---- Card ----
        RoundedPanel card = new RoundedPanel(30);
        card.setLayout(null);
        card.setBounds(123, 100, 414, 252);
        root.add(card);

        // ---- Title ----
        JLabel title = new JLabel(
                "Welcome to Tubigan!",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Poppins", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 18, 414, 40);
        card.add(title);

        // ---- Subtitle ----
        JLabel subtitle = new JLabel(
                "How would you like to use our service?",
                SwingConstants.CENTER
        );

        subtitle.setFont(new Font("Poppins", Font.PLAIN, 14));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(0, 55, 414, 22);
        card.add(subtitle);

        // ---- Customer Button ----
        OptionButton customer = new OptionButton(
                "Customer",
                "Order and track your water",
                IconType.USER
        );

        customer.setBounds(28, 95, 163, 77);

        customer.addActionListener(e -> {
            new login_screen().setVisible(true);
            dispose();
        });

        card.add(customer);

        // ---- Admin Button ----
        OptionButton admin = new OptionButton(
                "Admin",
                "Manage your water business",
                IconType.LOCK
        );

        admin.setBounds(222, 95, 163, 77);

        admin.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Admin selected"
                )
        );

        card.add(admin);

        // ---- Tagline ----
        JLabel tagline = new JLabel(
                "Simple. Convenient. Delivered.",
                SwingConstants.CENTER
        );

        tagline.setFont(new Font("Poppins", Font.PLAIN, 12));
        tagline.setForeground(Color.WHITE);
        tagline.setBounds(0, 202, 414, 20);
        card.add(tagline);

        // ---- Frame Settings ----
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // =========================================================
    // Logo
    // =========================================================

    private JComponent createLogo() {

        java.net.URL url = getClass().getResource(LOGO_PATH);

        if (url != null) {

            Image img = new ImageIcon(url)
                    .getImage()
                    .getScaledInstance(
                            90, 60,
                            Image.SCALE_SMOOTH
                    );

            return new JLabel(new ImageIcon(img));
        }

        // Draw a simple logo if no image is available
        return new JComponent() {

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

                g2.setPaint(
                        new GradientPaint(
                                0, 0,
                                new Color(0, 220, 255),
                                0, 60,
                                new Color(20, 90, 200)
                        )
                );

                g2.setStroke(
                        new BasicStroke(
                                7f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND
                        )
                );

                g2.draw(
                        new Arc2D.Double(
                                8, 6, 44, 44,
                                60, 250,
                                Arc2D.OPEN
                        )
                );

                g2.setStroke(
                        new BasicStroke(
                                4f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND
                        )
                );

                g2.draw(
                        new Arc2D.Double(
                                18, 16, 24, 24,
                                90, 220,
                                Arc2D.OPEN
                        )
                );

                g2.setColor(Color.WHITE);
                g2.setFont(
                        new Font("SansSerif", Font.BOLD, 13)
                );

                g2.drawString("TUBIGAN", 32, 34);

                g2.dispose();
            }
        };
    }

    // =========================================================
    // Background Panel
    // =========================================================

    static class BackgroundPanel extends JPanel {

        private final Random rnd = new Random(7);
        private final int[][] bubbles = new int[70][3];

        BackgroundPanel() {

            for (int[] b : bubbles) {

                b[0] = rnd.nextInt(655);
                b[1] = rnd.nextInt(462);
                b[2] = 3 + rnd.nextInt(12);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = getWidth();
            int h = getHeight();

            // Blue gradient background
            g2.setPaint(
                    new GradientPaint(
                            0, 0,
                            new Color(30, 60, 140),
                            w, h,
                            new Color(12, 20, 70)
                    )
            );

            g2.fillRect(0, 0, w, h);

            // Soft light effects
            g2.setPaint(
                    new RadialGradientPaint(
                            new Point(120, 90),
                            220,
                            new float[]{0f, 1f},
                            new Color[]{
                                    new Color(80, 140, 255, 90),
                                    new Color(80, 140, 255, 0)
                            }
                    )
            );

            g2.fillRect(0, 0, w, h);

            g2.setPaint(
                    new RadialGradientPaint(
                            new Point(540, 380),
                            240,
                            new float[]{0f, 1f},
                            new Color[]{
                                    new Color(60, 90, 200, 80),
                                    new Color(60, 90, 200, 0)
                            }
                    )
            );

            g2.fillRect(0, 0, w, h);

            // Water bubbles
            g2.setStroke(new BasicStroke(1.2f));

            for (int[] b : bubbles) {

                int r = b[2];

                g2.setColor(
                        new Color(255, 255, 255, 25)
                );

                g2.fillOval(
                        b[0], b[1],
                        r * 2, r * 2
                );

                g2.setColor(
                        new Color(255, 255, 255, 70)
                );

                g2.drawOval(
                        b[0], b[1],
                        r * 2, r * 2
                );
            }

            // Outer border
            g2.setColor(new Color(40, 90, 190));
            g2.setStroke(new BasicStroke(4f));

            g2.drawRect(
                    0, 0,
                    w - 1, h - 1
            );

            g2.dispose();
        }
    }

    // =========================================================
    // Rounded Card Panel
    // =========================================================

    static class RoundedPanel extends JPanel {

        private final int arc;

        RoundedPanel(int arc) {

            this.arc = arc;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(CARD_FILL);

            g2.fill(
                    new RoundRectangle2D.Double(
                            0, 0,
                            getWidth() - 1,
                            getHeight() - 1,
                            arc, arc
                    )
            );

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1.5f));

            g2.draw(
                    new RoundRectangle2D.Double(
                            1, 1,
                            getWidth() - 3,
                            getHeight() - 3,
                            arc, arc
                    )
            );

            g2.dispose();
        }
    }

    // =========================================================
    // Customer and Admin Button
    // =========================================================

    enum IconType {
        USER, LOCK
    }

    static class OptionButton extends JButton {

        private final String title;
        private final String subtitle;
        private final IconType icon;

        private boolean hover;

        OptionButton(
                String title,
                String subtitle,
                IconType icon
        ) {

            this.title = title;
            this.subtitle = subtitle;
            this.icon = icon;

            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);

            setCursor(
                    Cursor.getPredefinedCursor(
                            Cursor.HAND_CURSOR
                    )
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

            int w = getWidth();
            int h = getHeight();

            // Button shadow
            g2.setColor(BUTTON_SHADOW);

            g2.fill(
                    new RoundRectangle2D.Double(
                            0, 3, w, h - 3,
                            18, 18
                    )
            );

            // Button face
            g2.setColor(
                    hover
                            ? new Color(240, 239, 252)
                            : BUTTON_FILL
            );

            g2.fill(
                    new RoundRectangle2D.Double(
                            0, 0, w, h - 4,
                            18, 18
                    )
            );

            // Icon
            g2.setColor(TEXT_DARK);

            g2.setStroke(
                    new BasicStroke(
                            1.8f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            int cx = w / 2;
            int top = 12;

            if (icon == IconType.USER) {

                // Head
                g2.drawOval(
                        cx - 5, top,
                        10, 10
                );

                // Shoulders
                g2.draw(
                        new Arc2D.Double(
                                cx - 9,
                                top + 13,
                                18, 16,
                                0, 180,
                                Arc2D.OPEN
                        )
                );

                g2.drawLine(
                        cx - 9, top + 21,
                        cx + 9, top + 21
                );

            } else {

                // Lock body
                g2.draw(
                        new RoundRectangle2D.Double(
                                cx - 8,
                                top + 8,
                                16, 13,
                                4, 4
                        )
                );

                // Lock shackle
                g2.draw(
                        new Arc2D.Double(
                                cx - 5,
                                top,
                                10, 16,
                                0, 180,
                                Arc2D.OPEN
                        )
                );

                // Keyhole
                g2.fillOval(
                        cx - 2,
                        top + 12,
                        4, 4
                );
            }

            // Title
            g2.setFont(
                    new Font("Poppins", Font.BOLD, 14)
            );

            FontMetrics fm = g2.getFontMetrics();

            g2.drawString(
                    title,
                    (w - fm.stringWidth(title)) / 2,
                    50
            );

            // Subtitle
            g2.setFont(
                    new Font("Poppins", Font.PLAIN, 9)
            );

            fm = g2.getFontMetrics();

            g2.drawString(
                    subtitle,
                    (w - fm.stringWidth(subtitle)) / 2,
                    63
            );

            g2.dispose();
        }
    }

    // =========================================================
    // Main Method
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new welcome_screen().setVisible(true)
        );
    }
}
