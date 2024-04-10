package com.meneses.legacy;

import java.util.List;

public interface Video extends Camera {

    CameraFile getVideo();
    List<CameraFileMetadata> getVideosMetadata();
    Boolean startVideoRecording();
    Boolean stopVideoRecording();


}
