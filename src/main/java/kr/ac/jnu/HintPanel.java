package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HintPanel extends JPanel {
    private JButton btnSlow, btnSpace, btnIncorrect, btnChar;
    private int hintBtnWidth = 510;
    private int hintBtnHeight = 320;
    public HintPanel() {
        setLayout(null);
        setOpaque(false); // InitialPanel을 투명하게 설정

        btnSpace = new JButton();
        UIUtils.setButtonGraphics(btnSpace, "/Image/Button/space.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnSpace.setBounds(180, 50, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnSpace.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            // EditingPanel 표시
            wordPopcorn.showCard("EditingPanel");
            btnSpace.setVisible(false);

            // 4초 후 MainPanel로 전환하는 타이머 설정 및 시작
            Timer timer = new Timer(4000, event -> {
                mainPanel.updateBoardImage(wordPopcorn.getCurrentSongName()); // board 이미지 업데이트
                mainPanel.updateRoundLabel();
                wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            });
            timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
            timer.start(); // 타이머 시작
        });

        btnChar = new JButton();
        UIUtils.setButtonGraphics(btnChar, "/Image/Button/char.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnChar.setBounds(710, 50, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnChar.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            while (true) {
                String positionStr = JOptionPane.showInputDialog(this, "알고 싶은 글자의 위치를 입력하세요 (첫 글자는 1번):");
                if (positionStr == null || positionStr.isEmpty()) {
                    // 사용자가 취소하거나 빈 문자열을 입력한 경우 루프 종료
                    break;
                }

                try {
                    int position = Integer.parseInt(positionStr) - 1; // 사용자 입력은 1부터 시작하지만 배열 인덱스는 0부터 시작
                    String character = mainPanel.getCharacterAt(position);

                    if (!character.equals("잘못된 위치")) {
                        JOptionPane.showMessageDialog(this, "선택한 위치의 글자: " + character, "글자 정보", JOptionPane.INFORMATION_MESSAGE);
                        mainPanel.updateRoundLabel();
                        wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
                        btnChar.setVisible(false);
                        break; // 올바른 위치가 입력되면 루프 종료
                    } else {
                        JOptionPane.showMessageDialog(this, "잘못된 위치입니다. 다시 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "유효한 숫자를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnSlow = new JButton();
        UIUtils.setButtonGraphics(btnSlow, "/Image/Button/slow.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnSlow.setBounds(180, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnSlow.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            wordPopcorn.showCard("SlowDownPanel");
            btnSlow.setVisible(false);

            // 4초 후 MainPanel로 전환하는 타이머 설정 및 시작
            Timer timer = new Timer(4000, event -> {
                mainPanel.getBtnPlaySlow().setVisible(true);
                mainPanel.updateRoundLabel();
                wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            });
            timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
            timer.start(); // 타이머 시작
        });

        btnIncorrect = new JButton();
        UIUtils.setButtonGraphics(btnIncorrect, "/Image/Button/incorrect.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnIncorrect.setBounds(710, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnIncorrect.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            int incorrectCount = mainPanel.getIncorrectAnswersCount();
            mainPanel.updateRoundLabel();
            JOptionPane.showMessageDialog(this, "오답의 개수: " + incorrectCount, "오답 정보", JOptionPane.INFORMATION_MESSAGE);
            wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            btnIncorrect.setVisible(false);
        });

        add(btnSpace);
        add(btnChar);
        add(btnSlow);
        add(btnIncorrect);
    }
}