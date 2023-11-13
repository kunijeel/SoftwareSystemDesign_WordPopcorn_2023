package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomInfoDialog extends JDialog {
    public CustomInfoDialog(JFrame owner, String title, String message, float fontSize, int width, int height) throws IOException, FontFormatException {
        super(owner, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI(message, fontSize, width, height);
    }

    private void initUI(String message, float fontSize, int width, int height) throws IOException, FontFormatException {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);

        InputStream is = getClass().getResourceAsStream("/Font/DNFBitBitv2.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        customFont = customFont.deriveFont(fontSize);
        messageLabel.setFont(customFont);

        messagePanel.add(messageLabel, BorderLayout.CENTER);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        getContentPane().add(messagePanel, BorderLayout.CENTER);

        JButton okButton = new JButton("확인");
        okButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        setSize(width, height);
        setLocationRelativeTo(getParent());
    }

    public static void showInfoDialog(JFrame owner, String title, String message, float fontSize, int width, int height) throws IOException, FontFormatException {
        CustomInfoDialog dialog = new CustomInfoDialog(owner, title, message, fontSize, width, height);
        dialog.setVisible(true);
    }
}
