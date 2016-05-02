package com.example.chim.yplayer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;


public class YPlayer extends Activity {

    private SurfaceView videoSurface;
    private MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        /*
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        */
        setContentView(R.layout.player);
        videoSurface = (SurfaceView) findViewById(R.id.videosurface);
        mPlayer = new MediaPlayer();
        SurfaceCallback callback = new SurfaceCallback();
        videoSurface.getHolder().addCallback(callback);


    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Log.e("xxx", "create changed");
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mPlayer.setDisplay(videoSurface.getHolder());

            try {
                Log.e("xxx", "create surface");
                File file = new File("/sdcard/01.mp4");
                if (!file.canRead()) {
                    Log.e("xxx", "file access fail ");
                    return;
                }
                mPlayer.setDataSource("/sdcard/01.mp4");
                mPlayer.prepare();
                mPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.e("xxx", "create destroyed");
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.stop();
            }
        }

    }


}
