package com.meneses.legacy;

import com.meneses.legacy.camera.AudioRecorder;
import com.meneses.legacy.camera.Camera;
import com.meneses.legacy.camera.ImageRecorder;
import com.meneses.legacy.camera.VideoRecorder;
import com.meneses.legacy.camera.impl.CameraY;
import com.meneses.legacy.camera.impl.CameraZ;
import com.meneses.legacy.logger.DataDogLogger;
import com.meneses.legacy.logger.LocalLogger;
import com.meneses.legacy.logger.NewRelicLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CameraService cameraService = CameraService.getInstance();
        cameraService.setToken("token");

        System.out.println(
                "Seleccione su camara:" +
                " 1. CamaraX" +
                        " 2. CamaraY" +
                        " 3. CamaraZ"
        );

        Scanner scanner = new Scanner(System.in);
        int cameraType = scanner.nextInt();
        CameraFactory cameraFactory = new CameraFactory(cameraService);
        Camera camera = cameraFactory.create(cameraType);
        if (camera == null) {
            System.out.println("Opcion no soportada");
            return;
        }

        if (camera instanceof CameraY) {
            ((CameraY) camera).setNewRelicLogger(new NewRelicLogger());
            ((CameraY) camera).setLocalLogger(new LocalLogger());
        }

        if (camera instanceof CameraZ) {
            ((CameraZ) camera).setDataDogLogger(new DataDogLogger());
            ((CameraZ) camera).setNewRelicLogger(new NewRelicLogger());
            ((CameraZ) camera).setLocalLogger(new LocalLogger());
        }

        System.out.println(
                "Que desea hacer:" +
                        " 1. Tomar foto" +
                        " 2. Iniciar grabación de video" +
                        " 3. Iniciar grabación de audio"
        );

        int action = scanner.nextInt();
        Boolean result;

        try {
            switch (action) {
                case 1:
                    result = ((ImageRecorder)camera).takePhoto();
                    System.out.println("Foto tomada: " + result);
                    break;
                case 2:
                    result = ((VideoRecorder)camera).startVideoRecording();
                    System.out.println("Grabacion de video iniciada: " + result);
                    System.out.println("Ingrese cualquier tecla para finalizar la grabación");
                    scanner.nextByte();
                    result = ((VideoRecorder)camera).stopVideoRecording();
                    System.out.println("Grabacion de video terminada: " + result);
                    break;
                case 3:
                    result = ((AudioRecorder)camera).startAudioRecording();
                    System.out.println("Grabacion de audio iniciada: " + result);
                    System.out.println("Ingrese cualquier tecla para finalizar la grabación");
                    scanner.nextByte();
                    result = ((AudioRecorder)camera).stopAudioRecording();
                    System.out.println("Grabacion de audio terminada: " + result);
                    break;
                default:
                    System.out.println("Opcion no soportada");
                    break;
            }
        } catch (ClassCastException exception) {
            System.out.println("Opcion no soportada");
        }
    }
}
