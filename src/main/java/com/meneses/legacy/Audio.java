package com.meneses.legacy;

import java.util.List;

public interface Audio extends Camera {

    CameraFile getAudio();
    List<CameraFileMetadata> getAudiosMetadata();
    Boolean startAudioRecording();
    Boolean stopAudioRecording();

}
