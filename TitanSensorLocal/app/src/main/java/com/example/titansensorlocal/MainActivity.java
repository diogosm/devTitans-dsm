package com.example.titansensorlocal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
//import android.location.Location;
//import android.location.LocationManager;
//import android.location.LocationRequest;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.*;


public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private Button updateLocation;
    TextView latitudeTextView, longitTextView;
    private SensorManager sensorManager;
    private boolean ledStatus;
    private CameraManager cameraManager;
    private String cameraId;
    FusedLocationProviderClient fusedLocationProviderClient;
    int PERMISSION_ID = 44;
    private TextView xv, yz, zv;
    float xant, yant, zant;
    float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitudeTextView = findViewById(R.id.latTextView);
        longitTextView = findViewById(R.id.lonTextView);
        updateLocation = findViewById(R.id.updateLocation);
        toggleButton = findViewById(R.id.onOffFlashlight);
        this.setXv(findViewById(R.id.textXValue));
        this.setYz(findViewById(R.id.textYValue));
        this.setZv(findViewById(R.id.textZValue));
        xant = yant = zant = -1;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, sensor, 1000 * 1000 * 100);

        boolean isFlashAvailable;
        isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
        if(!isFlashAvailable){
            showNoErrorFlash();
        }
        this.setLedStatus(false);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            //cameraId = cameraManager.getCameraIdList()[1];
            cameraId = cameraManager.getCameraIdList()[0]; // celular real
        } catch (CameraAccessException e){
            e.printStackTrace();
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchFlashLight(b);
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        updateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });
    }

    public TextView getXv() {
        return xv;
    }

    public void setXv(TextView xv) {
        this.xv = xv;
    }

    public TextView getYz() {
        return yz;
    }

    public void setYz(TextView yz) {
        this.yz = yz;
    }

    public TextView getZv() {
        return zv;
    }

    public void setZv(TextView zv) {
        this.zv = zv;
    }

    private SensorEventListener listener = new SensorEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float xvalue = Math.abs(sensorEvent.values[0]);
            float yvalue = Math.abs(sensorEvent.values[1]);
            float zvalue = Math.abs(sensorEvent.values[2]);
            int praMovimentar = 20;

            float alpha = 0.8f;

            // Isolate the force of gravity with the low-pass filter.
            gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];

            // Remove the gravity contribution with the high-pass filter.
            linear_acceleration[0] = sensorEvent.values[0] - gravity[0];
            linear_acceleration[1] = sensorEvent.values[1] - gravity[1];
            linear_acceleration[2] = sensorEvent.values[2] - gravity[2];

            if( !(Math.abs(xvalue - xant) > 10
                && Math.abs(yvalue - yant) > 10
                && Math.abs(zvalue - zant) > 1e-1)){
                return;
            }

            Log.d("SENSOR_ACC", "Acceleration " +
                    linear_acceleration[0] + " "+
                    linear_acceleration[1] + " "+
                    linear_acceleration[2]);
            Log.d("SENSOR", "Moves: " + xvalue + " " + yvalue +
                    " " + zvalue);

            if(xvalue > praMovimentar
                && xvalue + yvalue > praMovimentar+3
                && zvalue < 1.0){
                Toast.makeText(MainActivity.this, "shake function activated",
                        Toast.LENGTH_SHORT).show();
                Log.d("SENSOR", "Balancei..." + xvalue + " "
                    + yvalue + " "
                    + zvalue);
                changeLedStatus();

                getXv().setText(String.format("%.2f", xvalue));
                getYz().setText(String.format("%.2f", yvalue));
                getZv().setText(String.format("%.2f", zvalue));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    public boolean isLedStatus() {
        return ledStatus;
    }

    public void setLedStatus(boolean ledStatus) {
        this.ledStatus = ledStatus;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorManager != null){
            sensorManager.unregisterListener(listener);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeLedStatus(){
        this.setLedStatus(!this.isLedStatus());
        switchFlashLight(this.isLedStatus());
        toggleButton.setChecked(this.isLedStatus());
    }

    public void showNoErrorFlash(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .create();
        alertDialog.setTitle("Ooops");
        alertDialog.setMessage("Flash nao tá disponível");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.show();
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchFlashLight(boolean ledStatus) {
        try{
            cameraManager.setTorchMode(cameraId, ledStatus);
            Log.d("SENSOR", "Status do led: " + String.valueOf(ledStatus));
        }catch (CameraAccessException exception){
            exception.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            Log.d("LOC_", "Permission ok");
            if (isLocationEnabled()) {
                Log.d("LOC_", "Location ok");
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @RequiresApi(api = Build.VERSION_CODES.S)
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            requestNewLocationData();
                            Log.d("LOC_", "Data ok: " + location.getLatitude() + " " + location.getLongitude());
                            latitudeTextView.setText(String.valueOf(location.getLatitude()) + "");
                            longitTextView.setText(String.valueOf(location.getLongitude()) + "");
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint({"MissingPermission", "RestrictedApi"})
    private void requestNewLocationData() {
        LocationRequest mLocationRequest;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }



}