package io.textory.yellowgrape.application;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.DatabaseError;
import com.google.gson.GsonBuilder;


/**
 *
 * @author      sean <hyunsik-park@textory.com>
 * @since       0.0.1
 * @date        2018. 3. 23.
 * @stereotype  class
 */

public class Application extends MultiDexApplication {

    private static void InitializeJson(GsonBuilder builder){
        builder.registerTypeAdapter(android.os.Message.class, new functional.android.message.serializer());
        builder.registerTypeAdapter(android.os.Message.class, new functional.android.message.deserializer());
        builder.registerTypeAdapter(DatabaseError.class, new rebeccapurple.android.firebase.database.Serializer());
    }


    @Override
    public void onCreate(){
        super.onCreate();

        functional.log.tag("rebeccapurple");
        functional.log.date(false);
        functional.log.stacktrace(false);
        functional.log.add(functional.android.log.get());
        functional.log.depth(5);

        functional.android.main.init();
        functional.json.init(Application::InitializeJson);
        functional.scheduler.init(rebeccapurple.android.Scheduler.Get());
        functional.android.http.client.init(this);
        functional.http.client.init(functional.android.http.client.get(), rebeccapurple.android.http.Client::Factory);
        functional.android.firebase.database.init();

        functional.scheduler.on();

        io.textory.yellowgrape.application.debug.run();
    }

//        rebeccapurple.android.Scheduler.On();
//        Scheduler scheduler = rebeccapurple.android.Scheduler.Get();
//        goldenrod.log.e("hello");
//        scheduler.dispatch(System.currentTimeMillis() + 5000L, ()-> goldenrod.log.e("hello"));
////        rebeccapurple.android.Scheduler.Dispatch()
//        rebeccapurple.android.http.volley.Client.init(this);                                /** initialize android volley client */
//        rebeccapurple.http.Client.init(rebeccapurple.android.http.volley.Client.get());     /** initialize default http client */
//        rebeccapurple.json.init();

    @Override
    public void onTerminate(){
//        rebeccapurple.android.Scheduler.Off();
        /** scheduler off */
        functional.scheduler.off();

        super.onTerminate();
    }
}
