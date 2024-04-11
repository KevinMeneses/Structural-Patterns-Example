package com.meneses.legacy.camera.impl;

import com.meneses.legacy.*;
import com.meneses.legacy.camera.AudioRecorder;
import com.meneses.legacy.camera.ImageRecorder;
import com.meneses.legacy.camera.VideoRecorder;
import com.meneses.legacy.camera.model.CameraCommand;
import com.meneses.legacy.camera.model.CameraCommandResult;
import com.meneses.legacy.camera.model.CameraFile;
import com.meneses.legacy.camera.model.CameraFileMetadata;
import com.meneses.legacy.logger.DataDogLogger;
import com.meneses.legacy.logger.LocalLogger;
import com.meneses.legacy.logger.NewRelicLogger;

import java.util.List;

public class CameraZ implements ImageRecorder, VideoRecorder, AudioRecorder {
    private final CameraService cameraService;
    private static List<CameraFileMetadata> photosMetadata;
    private static List<CameraFileMetadata> videosMetadata;
    private static List<CameraFileMetadata> audiosMetadata;
    private DataDogLogger dataDogLogger;
    private NewRelicLogger newRelicLogger;
    private LocalLogger localLogger;

    public CameraZ(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    public void setDataDogLogger(DataDogLogger dataDogLogger) {
        this.dataDogLogger = dataDogLogger;
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
        if (photosMetadata == null) {
            CameraCommand command = new CameraCommand.Builder()
                    .setToken(cameraService.getToken())
                    .setCode(2)
                    .setInformation("metadata")
                    .setType("photo")
                    .build();
            CameraCommandResult result = cameraService.sendCommand(command);
            photosMetadata = parseToMetadataList(result);
        }

        return photosMetadata;
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

        if (dataDogLogger != null) {
            dataDogLogger.logEvent("takePhoto", isSuccess.toString());
        }

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
                .setOffset(3)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    @Override
    public List<CameraFileMetadata> getVideosMetadata() {
        if (videosMetadata == null) {
            CameraCommand command = new CameraCommand.Builder()
                    .setToken(cameraService.getToken())
                    .setCode(2)
                    .setInformation("metadata")
                    .setType("videos")
                    .build();
            CameraCommandResult result = cameraService.sendCommand(command);
            videosMetadata = parseToMetadataList(result);
        }

        return videosMetadata;
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

        if (dataDogLogger != null) {
            dataDogLogger.logEvent("startVideoRecording", isSuccess.toString());
        }

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

        if (dataDogLogger != null) {
            dataDogLogger.logEvent("stopVideoRecording", isSuccess.toString());
        }

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("stopVideoRecording", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("stopVideoRecording", isSuccess.toString());
        }

        return isSuccess;
    }

    @Override
    public CameraFile getAudio() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(1)
                .setFetchSize(10)
                .setInformation("file")
                .setType("audio")
                .setOffset(3)
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        return parseToFile(result);
    }

    @Override
    public List<CameraFileMetadata> getAudiosMetadata() {
        if (audiosMetadata == null) {
            CameraCommand command = new CameraCommand.Builder()
                    .setToken(cameraService.getToken())
                    .setCode(2)
                    .setInformation("metadata")
                    .setType("audios")
                    .build();
            CameraCommandResult result = cameraService.sendCommand(command);
            audiosMetadata = parseToMetadataList(result);
        }

        return audiosMetadata;
    }

    @Override
    public Boolean startAudioRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(6)
                .setInformation("audio")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        Boolean isSuccess = parseToBoolean(result);

        if (dataDogLogger != null) {
            dataDogLogger.logEvent("startAudioRecording", isSuccess.toString());
        }

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("startAudioRecording", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("startAudioRecording", isSuccess.toString());
        }

        return isSuccess;
    }

    @Override
    public Boolean stopAudioRecording() {
        CameraCommand command = new CameraCommand.Builder()
                .setToken(cameraService.getToken())
                .setCode(7)
                .setInformation("video")
                .build();
        CameraCommandResult result = cameraService.sendCommand(command);
        Boolean isSuccess = parseToBoolean(result);

        if (dataDogLogger != null) {
            dataDogLogger.logEvent("stopAudioRecording", isSuccess.toString());
        }

        if (newRelicLogger != null) {
            newRelicLogger.logEvent("stopAudioRecording", isSuccess.toString());
        }

        if (localLogger != null) {
            localLogger.logEvent("stopAudioRecording", isSuccess.toString());
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
