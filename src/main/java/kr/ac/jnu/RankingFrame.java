package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는 게임 랭킹을 표시하는 프레임을 구현합니다.
 * 저장된 랭킹 데이터를 읽어와 테이블 형식으로 보여줍니다.
 *
 * @author Tam Oh
 */
public class RankingFrame extends JFrame {
    /**
     * 게임 랭킹을 표시하는 테이블입니다. 이 테이블은 랭킹 데이터를 사용자에게 보여주기 위한 구성 요소입니다.
     */
    private JTable table;

    /**
     * 테이블에 스크롤 기능을 추가하기 위한 스크롤팬입니다.
     * 사용자가 랭킹 데이터를 더 편리하게 볼 수 있도록 도와줍니다.
     */
    private JScrollPane scrollPane;

    /**
     * 테이블의 열 제목을 나타내는 문자열 배열입니다.
     * "플레이어", "노래 제목", "성공 라운드"와 같은 카테고리를 표시합니다.
     */
    private String[] columnNames = {"플레이어", "노래 제목", "성공 라운드"};

    /**
     * 랭킹 데이터를 저장하는 2차원 문자열 배열입니다.
     * 이 배열은 테이블에 표시될 실제 랭킹 데이터를 담고 있습니다.
     */
    private String[][] data;

    /**
     * 랭킹 데이터에서 노래 제목을 실제 곡명으로 변환하는 데 사용되는 SongLibrary 객체입니다.
     * 이 객체는 프로그램 내에서 사용되는 곡명을 실제 곡명으로 매핑하는 기능을 제공합니다.
     */
    private SongLibrary songLibrary; // SongLibrary 객체 추가

    /**
     * RankingFrame의 생성자.
     * 랭킹 프레임을 초기화하고 랭킹 데이터를 로드합니다.
     *
     * @param songLibrary SongLibrary 객체를 이용해 실제 곡명을 조회합니다.
     * @throws IOException 파일 입출력 중 발생한 오류를 처리합니다.
     */
    public RankingFrame(SongLibrary songLibrary) throws IOException {
        this.songLibrary = songLibrary; // SongLibrary 객체 할당
        setTitle("랭킹");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadRankingData();
        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        Font tableFont = new Font("맑은 고딕", Font.PLAIN, 12);
        table.setFont(tableFont);
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 14));

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * 랭킹 데이터를 로드하는 메소드.
     * "ranking.txt" 파일에서 랭킹 데이터를 읽어와 테이블에 표시합니다.
     *
     * @throws IOException 파일 입출력 중 발생한 오류를 처리합니다.
     */
    private void loadRankingData() throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("ranking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(" : ");
                int roundNumber = Integer.parseInt(splitData[2]);
                splitData[2] = (roundNumber + 1) + "라운드 성공";
                splitData[1] = songLibrary.getRealSongName(splitData[1]); // 프로그램 내 곡명을 실제 곡명으로 변환
                records.add(splitData);
            }
        }

        data = new String[records.size()][3];
        for (int i = 0; i < records.size(); i++) {
            data[i] = records.get(i);
        }
    }
}
