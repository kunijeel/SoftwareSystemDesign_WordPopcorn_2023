package kr.ac.jnu;

import java.util.HashMap;
import java.util.Map;

public class SongLibrary {
    private Map<String, String> songMap;
    public SongLibrary() {
        songMap = new HashMap<>();
        initializeSongs();
    }
    private void initializeSongs() {
        // 노래 정보 추가
        songMap.put("eta", "뉴진스이티에이");
        songMap.put("hypeboy", "뉴진스하입보이");
        songMap.put("omg", "뉴진스오엠지");
        songMap.put("zero", "뉴진스제로");
    }

    // 노래 제목으로 가사 얻기
    public String getLyricsByTitle(String title) {
        return songMap.get(title);
    }
}