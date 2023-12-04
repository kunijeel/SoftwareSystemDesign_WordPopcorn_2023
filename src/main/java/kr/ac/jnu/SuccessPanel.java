package kr.ac.jnu;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

/**
 * SuccessPanel 클래스는 게임 성공 후 표시되는 패널입니다.
 * 이 패널은 사용자가 성공한 결과를 확인하고 기록을 저장할 수 있는 기능을 제공합니다.
 * 사용자는 '뒤로 가기' 버튼을 통해 초기 화면으로 돌아갈 수 있고,
 * '기록 저장' 버튼을 통해 성공한 결과를 기록으로 남길 수 있습니다.
 *
 * @author Tam Oh
 */
public class SuccessPanel extends JPanel {
    /**
     * '뒤로 가기' 버튼.
     * 사용자가 이 버튼을 클릭하면 초기 화면으로 돌아갑니다.
     */
    private JButton btnBack;

    /**
     * '기록 저장' 버튼.
     * 사용자가 이 버튼을 클릭하면 게임 결과를 기록으로 저장할 수 있습니다.
     */
    private JButton btnSaveRecord; // 기록 저장 버튼

    /**
     * 성공 메시지를 표시하는 레이블.
     */
    private JLabel labelSuccess;

    /**
     * 메인 패널의 참조.
     * 게임의 상태 정보에 접근하기 위해 사용됩니다.
     */
    private MainPanel mainPanel;

    /**
     * SuccessPanel의 생성자입니다.
     * 이 생성자는 패널의 레이아웃을 설정하고 컴포넌트를 초기화합니다.
     *
     * @param mainPanel MainPanel의 인스턴스. 게임의 상태 정보에 접근하기 위해 사용됩니다.
     */
    public SuccessPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(null);
        setOpaque(false);

        initializeComponents();
        addComponents();
        setActionListeners();
    }

    /**
     * 컴포넌트를 초기화하는 메소드.
     * 버튼과 레이블을 초기화하고 설정합니다.
     */
    private void initializeComponents() {
        // 뒤로 가기 버튼 초기화
        btnBack = new JButton();
        UIUtils.setButtonGraphics(btnBack, "/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 95);

        // 성공 메시지 레이블 초기화
        labelSuccess = new JLabel();
        ImageIcon iconSuccess = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/success.png")));
        labelSuccess.setIcon(UIUtils.resizeImageIcon(iconSuccess, 1050, 700));
        labelSuccess.setBounds(175, 80, 1050, 700);

        // 기록 저장 버튼 초기화
        btnSaveRecord = new JButton();
        UIUtils.setButtonGraphics(btnSaveRecord, "/Image/Button/saverecord.png", 330, 95);
        btnSaveRecord.setBounds(990, 545, 330, 95);
    }

    /**
     * 컴포넌트를 패널에 추가하는 메소드.
     */
    private void addComponents() {
        add(btnBack);
        add(labelSuccess);
        add(btnSaveRecord);
    }

    /**
     * 각 버튼에 액션 리스너를 설정하는 메소드.
     * '뒤로 가기' 버튼과 '기록 저장' 버튼에 대한 액션 리스너를 설정합니다.
     */
    private void setActionListeners() {
        // 뒤로 가기 버튼 액션 리스너
        btnBack.addActionListener(e -> goBackToInitialPanel());

        // 기록 저장 버튼 액션 리스너
        btnSaveRecord.addActionListener(e -> openSaveRecordDialog());
    }

    /**
     * 초기 화면으로 돌아가는 메소드.
     * '뒤로 가기' 버튼이 클릭되었을 때 호출됩니다.
     */
    private void goBackToInitialPanel() {
        WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
        HintPanel hintPanel = (HintPanel) wordPopcorn.getCardPanel().getComponent(4);
        MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3);
        hintPanel.resetPanel();
        mainPanel.resetPanel();
        mainPanel.updateRoundLabel();
        wordPopcorn.showCard("InitialPanel");
    }

    /**
     * 기록 저장 대화 상자를 열어 사용자로부터 이름을 입력받고 기록을 저장하는 메소드.
     * '기록 저장' 버튼이 클릭되었을 때 호출됩니다.
     */
    private void openSaveRecordDialog() {
        String promptMessage = "이름을 입력하세요 (대문자 알파벳 3글자):";
        while (true) {
            String playerName = JOptionPane.showInputDialog(this, promptMessage);

            // 사용자가 취소 버튼을 누르거나 창을 닫은 경우
            if (playerName == null) {
                break;
            }

            // 이름이 알파벳 대문자 3글자인지 검증
            if (playerName.matches("[A-Z]{3}")) {
                try {
                    String songName = mainPanel.getCurrentSongName();
                    int currentRound = mainPanel.getCurrentRound();
                    RankingManager rankingManager = new RankingManager();
                    rankingManager.addRecord(playerName, songName, currentRound);
                    JOptionPane.showMessageDialog(this, "기록이 성공적으로 저장되었습니다!");
                    break; // 정상적으로 기록을 저장한 후 반복문 종료
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "기록 저장 중 오류가 발생했습니다.");
                    break; // 오류 발생 시 반복문 종료
                }
            } else {
                // 유효하지 않은 이름 입력 시
                JOptionPane.showMessageDialog(this, "유효하지 않은 이름입니다. 대문자 알파벳 3글자를 입력해주세요.");
            }
        }
    }
}