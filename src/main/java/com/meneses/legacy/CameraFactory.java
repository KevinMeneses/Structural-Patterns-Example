package com.meneses.legacy;

import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.impl.CameraX;
import com.meneses.legacy.camera.impl.CameraY;
import com.meneses.legacy.camera.impl.CameraZ;

public class CameraFactory {
    private final CameraService cameraService;

    public CameraFactory(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    public Camera create(int type) {
        return switch (type) {
            case 1 -> new CameraX(cameraService);
            case 2 -> new CameraY(cameraService);
            case 3 -> new CameraZ(cameraService);
            default -> null;
        };
    }
}
