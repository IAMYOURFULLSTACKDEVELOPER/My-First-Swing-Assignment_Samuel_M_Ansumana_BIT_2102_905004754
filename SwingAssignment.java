import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Inheritance: AssignmentFrame extends JFrame, AssignmentPanel extends JPanel.
 * Constructors configure each component when it is created.
 */
class AssignmentFrame extends JFrame {

    // Constructor: sets title, size, close behaviour and application icon
    public AssignmentFrame(String title, int width, int height, ImageIcon icon) {
        super(title);                                   // call JFrame constructor
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // app closes on close button
        setLocationRelativeTo(null);                    // centre on screen
        setIconImage(icon.getImage());                  // application icon
    }
}

class AssignmentPanel extends JPanel {

    // Constructor: sets layout and starting background colour
    // Using BoxLayout with Y_AXIS for vertical stacking of components
    public AssignmentPanel(Color background) {
        super();  // Call default JPanel constructor first
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Now 'this' is available
        setBackground(background);
    }
}

public class SwingAssignment {

    // Variables required by the brief
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    private int textIndex = 0;
    private int colorIndex = 0;

    private final String[] messages = {
        "Welcome to Java Swing First Individual Assignment by 'Samuel Mansfield Ansumana'",
        "Event-Driven Programming is fun! and we are been Lectured by Mr Amandus.B.Coker",
        "I am a Student of the BIT-2102 Class",
        "Thank you very much Sir Amandus.B.Coker for going through My Swing Assignment."
    };

    private final Color[] colors = {
        new Color(224, 242, 254), Color.LIGHT_GRAY, new Color(254, 226, 226),
        new Color(220, 252, 231), new Color(254, 249, 195)
    };

    public SwingAssignment() {
        ImageIcon icon = loadIcon("icon.png");  // your own icon (ImageIcon)

        // JFrame named frame (instance of subclass AssignmentFrame)
        frame = new AssignmentFrame("Java Swing Assignment", 800, 800, icon);

        // JPanel named panel
        panel = new AssignmentPanel(colors[0]);

        // JLabel named label, with text and an ImageIcon
        label = new JLabel(messages[0]);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setIcon(new ImageIcon(icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Addition: buttons with event handling
        JButton textButton = new JButton("Change Text");
        JButton colorButton = new JButton("Change Background");

        // Style both buttons with default appearance
        styleButton(textButton);
        styleButton(colorButton);

        // Add hover effects to each button
        // Change Text button: hover turns blue with black text
        addHoverEffect(textButton, new Color(37, 99, 235), Color.BLACK);

        // Change Background button: hover turns red with black text
        addHoverEffect(colorButton, new Color(220, 38, 38), Color.BLACK);

        // ActionListener for Change Text button (existing functionality)
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textIndex = (textIndex + 1) % messages.length;
                label.setText(messages[textIndex]);
            }
        });

        // ActionListener for Change Background button (existing functionality)
        colorButton.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % colors.length;
            panel.setBackground(colors[colorIndex]);
        });

        // Create button panel for horizontal alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(colors[0]);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(textButton);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(colorButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel with spacing
        panel.add(Box.createVerticalStrut(80));  // Top spacing
        panel.add(label);
        panel.add(Box.createVerticalStrut(50));  // Spacing between label and buttons
        panel.add(buttonPanel);
        panel.add(Box.createVerticalStrut(80));  // Bottom spacing

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Applies default styling to a button.
     * Sets black background, white text, bold font, rounded corners, and consistent size.
     */
    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(160, 42));
        button.setFocusPainted(false);           // Remove focus border
        button.setBorderPainted(false);           // Remove default border
        button.setOpaque(true);                  // Ensure background color is painted
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Custom padding
    }

    /**
     * Adds hover effect to a button using MouseAdapter.
     * When mouse enters: background changes to hoverColor, text changes to hoverTextColor.
     * When mouse exits: returns to default black background and white text.
     * This does not interfere with existing ActionListener functionality.
     */
    private void addHoverEffect(JButton button, Color hoverColor, Color hoverTextColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(hoverTextColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }
        });
    }

    // Loads icon.png from the project folder; falls back to a generated icon
    private ImageIcon loadIcon(String path) {
        File f = new File(path);
        if (f.exists()) {
            return new ImageIcon(path);
        }
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(37, 99, 235));
        g.fillRoundRect(0, 0, 64, 64, 20, 20);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 32));
        g.drawString("S", 22, 44);
        g.dispose();
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAssignment::new);
    }
}
