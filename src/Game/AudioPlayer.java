package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    Clip[] audioClips = new Clip[4];

    public AudioPlayer() {

    }

    public void playClip(int clipIndex) {
        audioClips[clipIndex].start();
    }
}
