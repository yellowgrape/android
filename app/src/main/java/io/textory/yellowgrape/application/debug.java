package io.textory.yellowgrape.application;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.telephony.SmsManager;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import rebeccapurple.Function;

import static android.content.Intent.ACTION_SEND;

/**
 *
 * @author      sean <hyunsik-park@textory.com>
 * @since       0.0.1
 */

public class debug {
    private static Handler __handler;
    private static AtomicInteger __integer = new AtomicInteger(0);

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

    private static int __subscription = 1024;
    private static short __port = 8091;

    public static void send(Context context, Uri content, String destination){
        SmsManager manager = SmsManager.getDefault();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager.sendMultimediaMessage(context, content, null, null, null);
        } else {
            functional.log.e("implement this");
        }
    }

    public static void send(Context context, Uri content, String destination, int request, rebeccapurple.Operator.On<Integer> sent){
        SmsManager manager = SmsManager.getDefault();
        PendingIntent intent = PendingIntent.getBroadcast(context, request, new Intent(ACTION_SEND), 0);
        context.registerReceiver(new rebeccapurple.android.telephony.sms.intent.Sent(context, null, sent), new IntentFilter(ACTION_SEND));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager.sendMultimediaMessage(context, content, null, null, intent);
        } else {
            functional.log.e("implement this");
        }
    }


    public static void run(Context context){
        __handler = new Handler();

        send(context, Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "logo.png")), "01087197281", 1024, functional.log::e);
//        try {
//            send("01087197281", "hello world data".getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            send(context, "01087197281", "OPERATOR BY CONTEXT data".getBytes("UTF-8"), __integer.incrementAndGet(), functional.log::e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String text = "The LLVM Project is a collection of modular and reusable compiler and toolchain technologies. Despite its name, LLVM has little to do with traditional virtual machines. The name \"LLVM\" itself is not an acronym; it is the full name of the project.\n" +
//                "\n" +
//                "LLVM began as a research project at the University of Illinois, with the goal of providing a modern, SSA-based compilation strategy capable of supporting both static and dynamic compilation of arbitrary programming languages. Since then, LLVM has grown to be an umbrella project consisting of a number of subprojects, many of which are being used in production by a wide variety of commercial and open source projects as well as being widely used in academic research. Code in the LLVM project is licensed under the \"UIUC\" BSD-Style license.\n" +
//                "\n" +
//                "The primary sub-projects of LLVM are:\n" +
//                "\n" +
//                "The LLVM Core libraries provide a modern source- and target-independent optimizer, along with code generation support for many popular CPUs (as well as some less common ones!) These libraries are built around a well specified code representation known as the LLVM intermediate representation (\"LLVM IR\"). The LLVM Core libraries are well documented, and it is particularly easy to invent your own language (or port an existing compiler) to use LLVM as an optimizer and code generator.\n" +
//                "\n" +
//                "Clang is an \"LLVM native\" C/C++/Objective-C compiler, which aims to deliver amazingly fast compiles (e.g. about 3x faster than GCC when compiling Objective-C code in a debug configuration), extremely useful error and warning messages and to provide a platform for building great source level tools. The Clang Static Analyzer is a tool that automatically finds bugs in your code, and is a great example of the sort of tool that can be built using the Clang frontend as a library to parse C/C++ code.\n" +
//                "\n" +
//                "The LLDB project builds on libraries provided by LLVM and Clang to provide a great native debugger. It uses the Clang ASTs and expression parser, LLVM JIT, LLVM disassembler, etc so that it provides an experience that \"just works\". It is also blazing fast and much more memory efficient than GDB at loading symbols.\n" +
//                "\n" +
//                "The libc++ and libc++ ABI projects provide a standard conformant and high-performance implementation of the C++ Standard Library, including full support for C++11.\n" +
//                "\n" +
//                "The compiler-rt project provides highly tuned implementations of the low-level code generator support routines like \"__fixunsdfdi\" and other calls generated when a target doesn't have a short sequence of native instructions to implement a core IR operation. It also provides implementations of run-time libraries for dynamic testing tools such as AddressSanitizer, ThreadSanitizer, MemorySanitizer, and DataFlowSanitizer.\n" +
//                "\n" +
//                "The OpenMP subproject provides an OpenMP runtime for use with the OpenMP implementation in Clang.\n" +
//                "\n" +
//                "The polly project implements a suite of cache-locality optimizations as well as auto-parallelism and vectorization using a polyhedral model.\n" +
//                "\n" +
//                "The libclc project aims to implement the OpenCL standard library.\n" +
//                "\n" +
//                "The klee project implements a \"symbolic virtual machine\" which uses a theorem prover to try to evaluate all dynamic paths through a program in an effort to find bugs and to prove properties of functions. A major feature of klee is that it can produce a testcase in the event that it detects a bug.\n" +
//                "\n" +
//                "The SAFECode project is a memory safety compiler for C/C++ programs. It instruments code with run-time checks to detect memory safety errors (e.g., buffer overflows) at run-time. It can be used to protect software from security attacks and can also be used as a memory safety error debugging tool like Valgrind.\n" +
//                "\n" +
//                "The lld project aims to be the built-in linker for clang/llvm. Currently, clang must invoke the system linker to produce executables.\n" +
//                "\n" +
//                "In addition to official subprojects of LLVM, there are a broad variety of other projects that use components of LLVM for various tasks. Through these external projects you can use LLVM to compile Ruby, Python, Haskell, Java, D, PHP, Pure, Lua, and a number of other languages. A major strength of LLVM is its versatility, flexibility, and reusability, which is why it is being used for such a wide variety of different tasks: everything from doing light-weight JIT compiles of embedded languages like Lua to compiling Fortran code for massive super computers.\n" +
//                "\n" +
//                "As much as everything else, LLVM has a broad and friendly community of people who are interested in building great low-level tools. If you are interested in getting involved, a good first place is to skim the LLVM Blog and to sign up for the LLVM Developer mailing list. For information on how to send in a patch, get commit access, and copyright and license topics, please see the LLVM Developer Policy.";
//         functional.android.telephony.sms.send(context, "01087197281", "OPERATOR BY CONTEXT", __integer.incrementAndGet(), functional.log::e);
//         functional.android.telephony.sms.send("01087197281", "HELLO WORLD");
//
//        functional.android.telephony.sms.send(context, "01087197281", text, __integer.incrementAndGet(), functional.log::e);
//        functional.android.telephony.sms.send("01087197281", text);




    }

    public static void run(){




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
    }
}
