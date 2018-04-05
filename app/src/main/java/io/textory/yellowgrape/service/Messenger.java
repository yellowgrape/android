package io.textory.yellowgrape.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import functional.android.messenger.operator;
import functional.log;
import rebeccapurple.android.message.sms.Content;
import rebeccapurple.android.message.sms.Receiver;
import rebeccapurple.android.messenger.Server;

public class Messenger extends Service {
    private Server __server = null;
    private Receiver __receiver = null;


    @Override
    public void onCreate(){
        super.onCreate();
        log.e("create");
        __server = new Server();
        __receiver = new Receiver();

        Thread thread = new Thread(){
            @Override
            public void run(){
                functional.log.e(functional.android.message.sms.all(Messenger.this));
                functional.log.e(functional.android.message.sms.all(Messenger.this, Content.DB.SORT.OLDEST));
                functional.log.e(functional.android.message.sms.all(Messenger.this, Content.DB.SORT.RECENTLY, cursor -> functional.android.message.sms.id(cursor)!=606));
            }
        };

        thread.start();

        operator.init(__server);

        __server.listen();
        __receiver.listen(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        functional.log.e("start");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        functional.log.e("bind");
        return __server != null ? __server.binder() : null;
    }

    @Override
    public void onDestroy(){
        __server.close();
        __receiver.close(this);
        functional.log.e("destroy");
        super.onDestroy();
    }
}
