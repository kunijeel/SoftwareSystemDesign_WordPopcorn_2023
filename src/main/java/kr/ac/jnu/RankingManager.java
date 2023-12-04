package kr.ac.jnu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는 게임의 랭킹 관리를 담당합니다.
 * 플레이어의 성적을 파일에 저장하고, 저장된 랭킹 기록을 조회하는 기능을 제공합니다.
 *
 * @author Tam Oh
 */
public class RankingManager {
    /**
     * 랭킹 기록을 저장할 파일의 이름.
     */
    private final String fileName = "ranking.txt"; // 랭킹 기록을 저장할 파일 이름

    /**
     * 랭킹 기록을 파일에 추가합니다.
     *
     * @param playerName 플레이어의 이름.
     * @param songName 플레이어가 성공한 노래의 제목.
     * @param round 플레이어가 도달한 라운드 번호.
     * @throws IOException 파일 쓰기 중 발생할 수 있는 입출력 예외를 처리합니다.
     */
    public void addRecord(String playerName, String songName, int round) throws IOException {
        String record = playerName + " : " + songName + " : " + round;
        writeToFile(record);
    }

    /**
     * 랭킹 기록을 파일에 작성합니다.
     *
     * @param record 파일에 작성할 랭킹 기록 문자열.
     * @throws IOException 파일 쓰기 중 발생할 수 있는 입출력 예외를 처리합니다.
     */
    private void writeToFile(String record) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(record);
            writer.newLine();
        }
    }

    /**
     * 파일에서 랭킹 기록을 불러옵니다.
     *
     * @return 파일에서 불러온 랭킹 기록 목록.
     * @throws IOException 파일 읽기 중 발생할 수 있는 입출력 예외를 처리합니다.
     */
    public List<String> getRecords() throws IOException {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        }
        return records;
    }
}
