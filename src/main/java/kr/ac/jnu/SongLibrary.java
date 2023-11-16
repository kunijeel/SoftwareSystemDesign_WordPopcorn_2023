package kr.ac.jnu;

import java.util.HashMap;
import java.util.Map;

/**
 * SongLibrary 클래스는 노래 제목과 가사를 관리하는 클래스입니다.
 * 이 클래스는 노래 제목과 그에 해당하는 가사를 매핑하여 저장합니다.
 *
 * @author Tam Oh
 */
public class SongLibrary {
    /**
     * 노래 제목과 가사를 매핑하는 Map.
     * 키는 노래 제목이고, 값은 해당 노래의 가사입니다.
     */
    private Map<String, String> songMap;

    /**
     * SongLibrary의 생성자.
     * 새로운 HashMap을 생성하고 노래 정보를 초기화합니다.
     */
    public SongLibrary() {
        songMap = new HashMap<>();
        initializeSongs();
    }

    /**
     * 노래 정보를 초기화하는 메소드.
     * 여기에서 노래 제목과 가사를 songMap에 추가합니다.
     */
    private void initializeSongs() {
        // 노래 정보 추가
        songMap.put("eta", "뉴진스이티에이");
        songMap.put("hypeboy", "뉴진스하입보이");
        songMap.put("omg", "뉴진스오엠지");
        songMap.put("zero", "뉴진스제로");
    }

    /**
     * 주어진 노래 제목에 해당하는 가사를 반환하는 메소드.
     *
     * @param title 노래 제목. songMap에서 해당 제목의 가사를 검색합니다.
     * @return 해당 노래 제목에 해당하는 가사. 만약 제목이 존재하지 않으면 null을 반환합니다.
     */
    public String getLyricsByTitle(String title) {
        return songMap.get(title);
    }
}