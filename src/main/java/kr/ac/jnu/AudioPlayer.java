package kr.ac.jnu;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer {
    private Clip clip;

    public void load(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // URL을 통해 오디오 파일을 가져옵니다
        URL url = this.getClass().getResource(path);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioIn);
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // 클립을 시작 지점으로 리셋합니다
            clip.start(); // 노래 재생
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop(); // 노래 정지
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    // 필요한 경우 다른 제어 메서드를 추가할 수 있습니다
}
