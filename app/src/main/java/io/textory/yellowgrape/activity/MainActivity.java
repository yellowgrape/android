package io.textory.yellowgrape.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import functional.android.messenger.operator;
import functional.android.messenger.request;

import io.textory.yellowgrape.R;
import io.textory.yellowgrape.service.Messenger;

import rebeccapurple.android.messenger.Client;

public class MainActivity extends AppCompatActivity {

    private Client<Messenger> __client = null;

    private static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

        permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

        permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

        permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }


        __client = new Client<>(this, Messenger.class);

        operator.init(__client);

        __client.connect(client -> {
//            client.send(request.ping(functional.android.message::log));
//            client.send(request.ping(1L, functional.android.message::log));
//            functional.android.main.post(client.send(request.tick(functional.android.message::log)), 20000L, request->()->request.cancel(null));
        });
    }

    @Override
    protected void onDestroy(){
        if(__client != null){
            __client.disconnect();
            __client = null;
        }
        super.onDestroy();
    }
}
