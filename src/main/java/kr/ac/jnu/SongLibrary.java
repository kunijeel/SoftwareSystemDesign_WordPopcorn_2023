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
        songMap.put("bonnie", "아예속도를최대로올린채로롤링스톤즈의노래를플레이온");
        songMap.put("break", "너넨모두줄자가됐어맨날재고재서난삐뚤하고싶어");
        songMap.put("drama", "후회없어난맞서난깨버렸지날따라서움직일룰즈");
        songMap.put("fighting", "아침밥은패스십분더자야돼테이크아웃커피로아메아메아메아메스토리속의친구들은다왜잘나보여");
        songMap.put("firetruck", "불을지펴봐이열기를식혀줄게불만이가득찬곳더화끈하게저사이렌소리에느끼는내기분은음");
        songMap.put("jamjam", "될래그깟멍청이뭐든해봐요우리생각할겨를조차주지마요");
        songMap.put("jana", "멍때린게아니라고민하는건데왜동그랗게머리는빠지는건데");
        songMap.put("likey", "아무반응없는너땜에삐졌는데눈치없이친구들이나오라고부르네");
        songMap.put("lovelee", "비타민유가필요해만들어줘샛노랗게체크무늬벌집두눈에");
        songMap.put("lovemelikethis", "철로만든하트예쁜꽃을피워봐사막속에비를내려봐");
        songMap.put("pose", "잔뜩올라간내턱끝흔들림따윈없는무브누구도예상못할포즈");
        songMap.put("sclass", "힙합스텝큼지막이밟지특출난게특기내집처럼드나들지특집");
        songMap.put("smoke", "상어밥도안돼넌그냥벵에돔엄마젖은사치이유식을맥여더");
        songMap.put("thatthat", "되려늘어난맷집때리던분이불편하겠지너네바람대로망할거라고사지낸사람들을모아다가가볍게때찌");
        songMap.put("zero", "정말짜릿했어어디선가내게윙크한까만눈동자");
    }

    /**
     * 힌트 맵을 초기화하는 메소드입니다.
     * 이 메소드는 게임에서 사용될 각 노래의 제목과 관련된 힌트를 hintMap에 추가합니다.
     * hintMap은 게임 플레이 중 특정 노래에 대한 힌트를 제공하는 데 사용됩니다.
     */
    private void initializeHints() {
        // 힌트 정보 추가
        hintMap.put("eta", "eta에 대한 힌트");
        hintMap.put("bonnie", "추가 정보 : 호감이 있는 여사친과 드라이브를 하는 상황을 표현한 노래");
        hintMap.put("break", "추가 정보 : 타인의 기준에 맞춰 살지 않겠다는 의지를 표현한 노래");
        hintMap.put("drama", "영어 단어 : 1개 (복수형)");
        hintMap.put("fighting", "추가 정보 : 바쁜 MZ 세대의 일상을 그린 노래");
        hintMap.put("firetruck", "영어 단어 : 1개");
        hintMap.put("jamjam", "추가 정보 : 연인끼리 재고 따질 필요 없이 사랑만 듬뿍 주자는 내용");
        hintMap.put("jana", "추가 정보 : 크러쉬가 자나 깨나 하는 근심과 걱정을 표현한 노래");
        hintMap.put("likey", "추가 정보 : SNS에서 좋아요도 안 눌러준 남자에게 삐진 상황");
        hintMap.put("lovelee", "영어 단어 : 3개");
        hintMap.put("lovemelikethis", "영어 단어 : 1개");
        hintMap.put("pose", "영어 단어 : 2개");
        hintMap.put("sclass", "영어 단어 : 2개");
        hintMap.put("smoke", "추가 정보 : 노 리스펙 상대를 디스하는 내용");
        hintMap.put("thatthat", "추가 정보 : 내가 어떤 사람이었는지를 상기시키는 내용");
        hintMap.put("zero", "추가 정보 : 상대방에게 플러팅 당한 순간을 표현한 내용");
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