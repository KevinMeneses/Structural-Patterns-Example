package com.meneses.legacy.camera;

import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.refactor.camera.Camera;

import java.util.List;

public interface ImageRecorder extends Camera {
    CameraFile getPhoto();
    List<CameraFileMetadata> getPhotosMetadata();
    Boolean takePhoto();
    Boolean saveMetadata(CameraFileMetadata metadata);
}
