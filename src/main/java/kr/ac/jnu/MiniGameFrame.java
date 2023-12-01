package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kr.ac.jnu.avoidancegame.GamePanel; // AvoidanceGame 패널을 import

public class MiniGameFrame extends JFrame {
    private JPanel gamePanelContainer;
    private CardLayout cardLayout;

    public MiniGameFrame() {
        setTitle("Mini Game");
        setSize(700, 700);

        cardLayout = new CardLayout();
        gamePanelContainer = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        GamePanel avoidanceGamePanel = new GamePanel(this); // 회피 게임 패널

        gamePanelContainer.add(menuPanel, "Menu");
        gamePanelContainer.add(avoidanceGamePanel, "AvoidanceGame");

        add(gamePanelContainer);
        cardLayout.show(gamePanelContainer, "Menu");

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1)); // 3개의 게임 버튼을 위한 레이아웃

        JButton avoidanceGameButton = new JButton("회피 게임");
        avoidanceGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(gamePanelContainer, "AvoidanceGame");
            }
        });

        // 다른 게임 버튼 추가 및 액션 리스너 구현
        JButton game2Button = new JButton("게임 2");
        game2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(gamePanelContainer, "Game2");
            }
        });

        JButton game3Button = new JButton("게임 3");
        game3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(gamePanelContainer, "Game3");
            }
        });

        panel.add(avoidanceGameButton);
        panel.add(game2Button);
        panel.add(game3Button);

        return panel;
    }
}