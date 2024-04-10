package com.meneses.legacy.camera.impl;

import com.meneses.legacy.*;
import com.meneses.legacy.camera.Photo;
import com.meneses.legacy.camera.Video;
import com.meneses.legacy.camera.model.CameraCommand;
import com.meneses.legacy.camera.model.CameraCommandResult;
import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.legacy.logger.LocalLogger;
import com.meneses.legacy.logger.NewRelicLogger;

import java.util.List;

public class CameraY implements Photo, Video {
    private final CameraService cameraService;
    private NewRelicLogger newRelicLogger;
    private LocalLogger localLogger;

    public CameraY(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    public void setNewRelicLogger(NewRelicLogger newRelicLogger) {
        this.newRelicLogger = newRelicLogger;
    }

    public void setLocalLogger(LocalLogger localLogger) {
        this.localLogger = localLogger;
    }

    @Override
    public CameraFile getPhoto() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setInformation("file")
                .setType("photo")
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
                .setType("photo")
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
        Boolean isSuccess = parseToBoolean(result);

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("takePhoto", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("takePhoto", isSuccess.toString());
        }

        return isSuccess;
    }

    private Boolean parseToBoolean(CameraCommandResult result) {
        return true;
    }

    @Override
    public CameraFile getVideo() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setFetchSize(10)
                .setInformation("file")
                .setType("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    @Override
    public List<CameraFileMetadata> getVideosMetadata() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(2)
                .setInformation("metadata")
                .setType("videos")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToMetadataList(result);
    }

    @Override
    public Boolean startVideoRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(4)
                .setInformation("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        Boolean isSuccess = parseToBoolean(result);

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("startVideoRecording", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("startVideoRecording", isSuccess.toString());
        }

        return isSuccess;
    }

    @Override
    public Boolean stopVideoRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(5)
                .setInformation("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        Boolean isSuccess = parseToBoolean(result);

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("stopVideoRecording", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("stopVideoRecording", isSuccess.toString());
        }

        return isSuccess;
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
