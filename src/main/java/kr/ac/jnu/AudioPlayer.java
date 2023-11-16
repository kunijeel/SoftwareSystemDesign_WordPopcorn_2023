package kr.ac.jnu;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 이 클래스는 오디오 파일을 재생하기 위한 기능을 제공합니다.
 * 오디오 파일은 URL을 통해 로드되며, 이 클래스를 사용하여 오디오를 재생, 정지 및 상태 확인이 가능합니다.
 *
 * @author Tam Oh
 */
public class AudioPlayer {
    /**
     * 재생할 오디오 클립을 위한 Clip 객체입니다.
     * 이 필드는 오디오 데이터를 저장하고, 재생 제어를 담당합니다.
     */
    private Clip clip;

    /**
     * 지정된 경로의 오디오 파일을 로드합니다.
     *
     * @param path 오디오 파일의 경로
     * @throws UnsupportedAudioFileException 지원하지 않는 오디오 파일 형식일 경우 발생
     * @throws IOException 파일 입출력 오류 발생 시
     * @throws LineUnavailableException 오디오 라인이 사용 중이거나 다른 이유로 사용할 수 없을 때 발생합니다.
     */
    public void load(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // URL을 통해 오디오 파일을 가져옵니다
        URL url = this.getClass().getResource(path);
        assert url != null;
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioIn);
    }

    /**
     * 오디오 클립을 재생합니다.
     * 이 메소드는 클립을 처음부터 재생시키며, 클립은 이 메소드가 호출되기 전에 미리 로드되어 있어야 합니다.
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // 클립을 시작 지점으로 리셋합니다
            clip.start(); // 노래 재생
        }
    }

    /**
     * 오디오 클립을 정지합니다.
     * 클립이 재생 중일 경우에만 작동합니다.
     */
    public void stop() {
        if (clip != null) {
            clip.stop(); // 노래 정지
        }
    }

    /**
     * 현재 오디오가 재생 중인지 여부를 반환합니다.
     *
     * @return 오디오가 재생 중이면 true, 그렇지 않으면 false
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}