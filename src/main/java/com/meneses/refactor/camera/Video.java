package com.meneses.refactor.camera;

import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;

import java.util.List;

public interface Video extends Camera {
    CameraFile getVideo();
    List<CameraFileMetadata> getVideosMetadata();
    Boolean startVideoRecording();
    Boolean stopVideoRecording();
}