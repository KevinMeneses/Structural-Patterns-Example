package com.meneses.refactor.camera.impl;

import com.meneses.refactor.camera.ImageRecorder;
import com.meneses.refactor.camera.model.CameraFile;
import com.meneses.refactor.camera.model.CameraFileMetadata;
import com.meneses.refactor.CameraService;
import com.meneses.refactor.camera.Camera;
import com.meneses.refactor.camera.model.CameraCommand;
import com.meneses.refactor.camera.model.CameraCommandResult;

import java.util.List;

public class CameraX implements Camera, ImageRecorder {
    private final CameraService cameraService;

    public CameraX(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @Override
    public CameraFile getPhoto() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setFetchSize(10)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    private CameraFile parseToFile(CameraCommandResult result) {
        return new CameraFile();
    }

    @Override
    public List<CameraFileMetadata> getPhotosMetadata() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(2)
                .setInformation("metadata")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToMetadataList(result);
    }

    private List<CameraFileMetadata> parseToMetadataList(CameraCommandResult result) {
        return List.of(new CameraFileMetadata());
    }

    @Override
    public Boolean takePhoto() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(3)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    private Boolean parseToBoolean(CameraCommandResult result) {
        return true;
    }

    @Override
    public Boolean saveMetadata(CameraFileMetadata metadata) {
        String information = parseToString(metadata);
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(10)
                .setInformation(information)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToBoolean(result);
    }

    private String parseToString(CameraFileMetadata metadata) {
        return "";
    }
}
