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
     * 각 노래 제목에 해당하는 힌트를 저장하는 맵입니다.
     * 이 맵은 노래 제목을 키로 사용하며, 해당 노래에 대한 힌트를 값으로 저장합니다.
     * 게임에서 노래에 관련된 힌트를 제공할 때 이 맵을 조회하여 사용자에게 적절한 힌트를 제공합니다.
     */
    private Map<String, String> hintMap;

    /**
     * SongLibrary의 생성자.
     * 새로운 HashMap을 생성하고 노래 정보와 힌트를 초기화합니다.
     */
    public SongLibrary() {
        songMap = new HashMap<>();
        hintMap = new HashMap<>();
        initializeSongs();
        initializeHints();
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
     * 힌트 맵을 초기화하는 메소드입니다.
     * 이 메소드는 게임에서 사용될 각 노래의 제목과 관련된 힌트를 hintMap에 추가합니다.
     * hintMap은 게임 플레이 중 특정 노래에 대한 힌트를 제공하는 데 사용됩니다.
     */
    private void initializeHints() {
        // 힌트 정보 추가
        hintMap.put("eta", "이티에이에 대한 힌트");
        hintMap.put("hypeboy", "하입보이에 대한 힌트");
        hintMap.put("omg", "오엠지에 대한 힌트");
        hintMap.put("zero", "제로에 대한 힌트");
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

    /**
     * 제공된 노래 제목에 대한 힌트를 반환합니다.
     * 이 메소드는 hintMap에서 주어진 노래 제목에 해당하는 힌트를 조회합니다.
     * 만약 해당 제목의 힌트가 hintMap에 존재하지 않는 경우, 기본 메시지 "힌트가 없습니다"를 반환합니다.
     *
     * @param title 노래 제목. 힌트를 조회할 노래의 제목입니다.
     * @return 해당 노래 제목에 대한 힌트. 힌트가 없을 경우 "힌트가 없습니다"를 반환합니다.
     */
    public String getHintByTitle(String title) {
        return hintMap.getOrDefault(title, "힌트가 없습니다"); // 해당 제목의 힌트가 없으면 기본 메시지 반환
    }
}