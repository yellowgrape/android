package io.textory.yellowgrape.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import functional.android.messenger.operator;
import functional.log;
import rebeccapurple.android.telephony.sms.Content;
import rebeccapurple.android.telephony.sms.Receiver;
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

//        Thread thread = new Thread(){
//            @Override
//            public void run(){
//
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.DRAFT);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.OUTBOX);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.SENT);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.mms.Content.DB.DRAFT);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.mms.Content.DB.INBOX);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.mms.Content.DB.OUTBOX);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.mms.Content.DB.SENT);
//                functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.Thread.DB.CONTENT);
//
//
//
//                functional.android.telephony.sms.draft.all(Messenger.this, functional.log::e);
//                functional.android.telephony.sms.sent.all(Messenger.this, functional.log::e);
//                functional.android.telephony.sms.outbox.all(Messenger.this, functional.log::e);
//                functional.android.telephony.sms.inbox.all(Messenger.this, functional.log::e);
//                functional.android.telephony.sms.inbox.all(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.SORT.OLDEST, null, functional.log::e);
//                functional.android.telephony.sms.inbox.all(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.SORT.RECENTLY, cursor -> functional.android.telephony.sms.id(cursor)!=606, functional.log::e);
//
////                functional.android.telephony.mms.draft.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.sent.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.inbox.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.outbox.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.address.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.part.all(Messenger.this, functional.log::e);
////                functional.android.telephony.mms.rate.all(Messenger.this, functional.log::e);
//
//
//                functional.android.telephony.msg.content.conversation.all(Messenger.this, functional.log::e);
//                functional.android.telephony.msg.content.draft.all(Messenger.this, functional.log::e);
//                functional.android.telephony.msg.content.locked.all(Messenger.this, functional.log::e);
//                functional.android.telephony.msg.content.all(Messenger.this,  rebeccapurple.android.telephony.msg.Content.DB.DEFAULT, functional.log::e);
//                functional.android.telephony.msg.pending.all(Messenger.this, functional.log::e);
//                functional.android.telephony.thread.content.all(Messenger.this, functional.log::e);
//
//                // functional.android.telephony.msg.content.byphone.all(Messenger.this, functional.log::e);
//                // functional.android.telephony.msg.content.undelivered.all(Messenger.this, functional.log::e);
//                // functional.android.telephony.msg.content.search.all(Messenger.this, functional.log::e);
//                // functional.android.telephony.thread.obsolete.all(Messenger.this, functional.log::e);
//                // functional.android.telephony.carrier.all(Messenger.this, functional.log::e);
//                // functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.sms.Content.DB.CONVERSATION);
//                // functional.android.sql.columns(Messenger.this, rebeccapurple.android.telephony.Thread.DB.OBSOLETE);
//                // functional.android.telephony.sms.conversation.all(Messenger.this, functional.log::e);
//            }
//        };



        // thread.start();

//        functional.android.telephony.sms.send(this, "01087197281", "OPERATOR BY CONTEXT", 0, functional.log::e);
//        functional.android.telephony.sms.send("01087197281", "HELLO WORLD");

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
