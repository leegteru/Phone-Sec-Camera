package com.v8r1.sec.camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textId1);
        String tempText = textView.getText().toString();
        final int numCameras = Camera.getNumberOfCameras();
        tempText = tempText + numCameras;
        textView.setText(tempText);

        final Button button = (Button) findViewById(R.id.camera_name_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView editText = (TextView) findViewById(R.id.editText);
                Log.d("inputValue", editText.getText().toString());
                Context context = getApplicationContext();
                CharSequence text = "Successfully set your front camera name to " + editText.getText().toString();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                int numberOfCameras = Camera.getNumberOfCameras();
                int cameraId = 0;
                for (int i = 0; i < numberOfCameras; i++) {
                    Camera.CameraInfo info = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, info);
                    if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        Log.d("CamDebug", "Camera found");
                        cameraId = i;
                        break;
                    }
                }
                Log.d("Camera id", String.valueOf(cameraId));
                toast.show();
                setContentView(R.layout.second_activity);

            }
        });


    }
}
