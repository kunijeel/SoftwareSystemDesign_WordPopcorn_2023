package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 사용자 정의 폰트와 스타일을 적용한 정보 대화 상자를 구현하는 클래스입니다.
 * 이 대화 상자는 주어진 메시지를 중앙에 표시하고, 확인 버튼을 제공합니다.
 *
 * @author Tam Oh
 */
public class CustomInfoDialog extends JDialog {
    /**
     * CustomInfoDialog의 생성자입니다.
     * 이 생성자는 대화 상자의 기본 설정을 초기화하고 UI를 구성합니다.
     *
     * @param owner 대화 상자의 부모 프레임
     * @param title 대화 상자의 제목
     * @param message 대화 상자에 표시될 메시지
     * @param fontSize 메시지의 폰트 크기
     * @param width 대화 상자의 너비
     * @param height 대화 상자의 높이
     * @throws IOException 폰트 파일 로딩 중 오류 발생 시
     * @throws FontFormatException 폰트 형식이 잘못되었을 경우
     */
    public CustomInfoDialog(JFrame owner, String title, String message, float fontSize, int width, int height) throws IOException, FontFormatException {
        super(owner, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI(message, fontSize, width, height);
    }

    /**
     * 대화 상자의 UI를 초기화합니다.
     * 이 메소드는 메시지 패널을 생성하고, 사용자 정의 폰트를 적용합니다.
     *
     * @param message 대화 상자에 표시될 메시지
     * @param fontSize 메시지의 폰트 크기
     * @param width 대화 상자의 너비
     * @param height 대화 상자의 높이
     * @throws IOException 폰트 파일 로딩 중 오류 발생 시
     * @throws FontFormatException 폰트 형식이 잘못되었을 경우
     */
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

    /**
     * 정보 대화 상자를 표시하는 정적 메소드입니다.
     * 이 메소드는 CustomInfoDialog 객체를 생성하고, 표시합니다.
     *
     * @param owner 대화 상자의 부모 프레임
     * @param title 대화 상자의 제목
     * @param message 대화 상자에 표시될 메시지
     * @param fontSize 메시지의 폰트 크기
     * @param width 대화 상자의 너비
     * @param height 대화 상자의 높이
     * @throws IOException 폰트 파일 로딩 중 오류 발생 시
     * @throws FontFormatException 폰트 형식이 잘못되었을 경우
     */
    public static void showInfoDialog(JFrame owner, String title, String message, float fontSize, int width, int height) throws IOException, FontFormatException {
        CustomInfoDialog dialog = new CustomInfoDialog(owner, title, message, fontSize, width, height);
        dialog.setVisible(true);
    }
}