package com.cooki.tiger.childbook.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class AudioPlayService extends Service {

    MediaPlayer mMediaPlayer;
    MediaPlayInterface  mMediaPlayInterface;

    @Override
    public IBinder onBind(Intent intent) {
       return new MediaBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        setListener();
    }

    private  void playMedia(String path){
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource("/mnt/sdcard/god.mp3");
            mMediaPlayer.prepare();//缓冲
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setListener(){
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                if(mMediaPlayInterface!=null){
                    mMediaPlayInterface.onCompletion(mp);
                }
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mMediaPlayer.release();
                mMediaPlayInterface.onError(mp, what, extra);
                return false;
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public class MediaBind extends Binder{
        AudioPlayService getAudiPlayService(){
            return AudioPlayService.this;
        }
    }

    public void setMediaPlayInterface(MediaPlayInterface mediaPlayInterface) {
        mMediaPlayInterface = mediaPlayInterface;
    }
}
