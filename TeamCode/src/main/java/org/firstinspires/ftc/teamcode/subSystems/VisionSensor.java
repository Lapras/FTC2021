package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.WebcamConfiguration;
import com.qualcomm.robotcore.util.Hardware;

public class VisionSensor {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";

    private static final String VUFORIA_KEY = "AcWJXfv/////AAABmXTvMEsBOEYkiGqMsGZyklUBGfk5cSsLyBZx0YTUz4txj9n9lF3yHQWwQIFc+gC2pdWkKk3iWgbSza68dp0T2zqc+1sG6S5G6VAXxnNBUsH6rjRa+6p1kPIsiEQdezRl4m5VeATR5SzGECvIbIhtc3nWjjquoM/d8+R7QCMOrAPRwf9bhK6Ah2tgIuPkwVwkp+G1q4/qaFrLMGcDxRDlwNTFMZfnmTLt18Xe6Q54eDPb6Bw2MHmfUXSXbeqiahjFGQz40bh0TH3vAp47L88U3oXSo+YgV2TyT1PhQE7SUE71ucVNQ9PUM0OfIKFTtHy2MaXsE2dOsj+WAJG5J3iGO530Gaod7DtKZSCPC02SyADM";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private List<Recognition> recognitionList;
    //Initializing Variables & the Vuforia Key

    public VisionSensor(HardwareMap hardwareMap) {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);

        recognitionList = tfod.getRecognitions();
    }

    public void activateTfod() {
        if(tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
        //Activates tensorflow and adjusts the Camera magnification to
        //reccomended value
    }
    public boolean isNull() {
        if(tfod != null) {
            return true;
        } else {
            return false;
        }
        //Returns true if Tfod is not Null, returns false if Tfod is null
    }
    public void shutDownTfod() {
        if(tfod != null) {
            tfod.shutdown();
        }
        //Shuts down Tensorflow
    }

    public List<Recognition> getRecognitions() {
        return recognitionList;
    }
    //Returns an ArrayList of Recognitions equal to the recognitionList

    public void updateRecognitions() {
        recognitionList = tfod.getRecognitions();
    }
    //Updates Recognitions from Tensorflow. You are usually supposed to use
    //tfod.getUpdatedRecognitions, but for some rreason that does not work.

    public int getRecognitionSize() {
        if (recognitionList != null) {
            return recognitionList.size();
        } else {
            return 0;
        }
    }
    //returns the size of recognitionList, returns 0 if null

    public String getLabel(int index) {
        return recognitionList.get(index).getLabel();
    }
    //Returns the label of the recognition from a given index

}
