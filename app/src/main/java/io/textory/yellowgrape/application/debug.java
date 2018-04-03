package io.textory.yellowgrape.application;

import android.os.Environment;
import android.os.Handler;

import com.google.gson.annotations.Expose;

import java.io.File;

import rebeccapurple.Function;
import rebeccapurple.android.firebase.database.Subscription;
import rebeccapurple.exception.CancelledScheduleException;
import rebeccapurple.schedule.Time;
import rebeccapurple.schedule.Tick;
import rebeccapurple.schedule.Timeout;

/**
 *
 * @author      sean <hyunsik-park@textory.com>
 * @since       0.0.1
 */

public class debug {
    private static Handler __handler;

    public static <T extends rebeccapurple.scheduler.Task> void scheduler(T schedule, long delay, Function<T, Runnable> factory){
        try {
            __handler.postDelayed(factory.call(schedule), delay);
        } catch (Throwable e) {
            functional.log.e(schedule, e);
        }
    }

    private static class http {
        private static class response {
            public static void json(rebeccapurple.http.Response response){
                functional.log.e(response);
                if(response != null) {
                    functional.log.e(functional.json.from(new String(response.body())));
                }
            }
        }
    }

    public static class FirebaseExample {
        @Expose public String hello = "hello";
        @Expose public String world = "world";

        public FirebaseExample(String hello, String world){
            this.hello = hello;
            this.world = world;
        }

        public FirebaseExample(){}
    }

    public static void run(){
        __handler = new Handler();

//        functional.scheduler.dispatch(new Time(functional.timestamp.after(1000L), functional.scheduler::log));
//        functional.scheduler.dispatch(new Timeout(2000L, functional.scheduler::log));
//        scheduler(functional.scheduler.dispatch(new Tick(1000L, functional.scheduler::log)), 15000L, schedule -> ()->schedule.cancel(new CancelledScheduleException()));
//        scheduler(functional.scheduler.dispatch(new Timeout(3000L, functional.scheduler::log)), 1000L, schedule->()->schedule.cancel(new CancelledScheduleException()));
//        scheduler(functional.scheduler.dispatch(new Timeout(4000L, functional.scheduler::log)), 2000L, schedule->()->functional.scheduler.reset(schedule));
//
//        try {
//            functional.http.client.get("https://api.github.com/users/yellowgrape", http.response::json);
//        } catch (Throwable e) {
//            functional.log.e("fail to functional.http.client.get(...)",e);
//        }

//        Subscription<FirebaseExample> subscription = functional.android.firebase.database.subscribe("/", new FirebaseExample(), FirebaseExample.class, functional.log::e);
//
//        functional.android.firebase.database.set("/", new FirebaseExample());
//        functional.android.firebase.database.set("/hello", "hello2");
//        functional.android.firebase.database.get("/", FirebaseExample.class, functional.log::e);
//        functional.android.firebase.database.del("/hello");
//
//        functional.android.main.post(subscription::del, 1000L);
//        functional.android.main.post(()->subscription.get(functional.log::e), 2000L);
//        functional.android.main.post(()->subscription.set(new FirebaseExample("hello3", "world3")), 2000L);
        File downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        functional.android.firebase.storage.get("firebase:///hello/logo.png", downloads.getAbsolutePath() + "/logo.png", functional.log::e);
        functional.android.firebase.storage.set("fs://" + downloads.getAbsolutePath() + "/logo.png", "/hello/logo2.png", functional.log::e);

    }
}
