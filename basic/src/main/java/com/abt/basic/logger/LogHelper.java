package com.abt.basic.logger;

import android.util.Log;

import com.abt.basic.BuildConfig;

/**
 * @描述： @LogHelper
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public final class LogHelper {

    public final static void i(String tag,String arg){
        if(BuildConfig.DEBUG){
            Log.v(tag,arg);
        }
    }

    public final static void d(String tag,String arg){
        if(BuildConfig.DEBUG){
            Log.d(tag,arg);
        }
    }

    public final static void v(String tag,String arg){
        if(BuildConfig.DEBUG){
            Log.v(tag,arg);
        }
    }

    public final static void e(String tag,String arg){
        if(BuildConfig.DEBUG){
            Log.e(tag,arg);
        }
    }

    public final static void e(String tag,String arg,Throwable e){
        if(BuildConfig.DEBUG){
            Log.e(tag,arg,e);
        }
    }

    public final static void w(String tag,String arg){
        if(BuildConfig.DEBUG){
            Log.w(tag,arg);
        }
    }

    public final static void s(String message){
        if(BuildConfig.DEBUG){
            System.out.println(message);
        }
    }
}
