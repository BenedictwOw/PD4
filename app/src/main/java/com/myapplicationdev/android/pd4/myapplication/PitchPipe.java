package com.myapplicationdev.android.pd4.myapplication;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.myapplicationdev.android.pd4.myapplication.R;

public class PitchPipe extends AppCompatActivity {
    private SoundPool soundPool;
    private int C,CSHARP,D,DSHARP,E,F,FSHARP,G,GSHARP,A,ASHARP,B;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitchpipe);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build();
        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        C = soundPool.load(this,R.raw.c4,1);
        CSHARP = soundPool.load(this,R.raw.c_4,1);
        D = soundPool.load(this,R.raw.d4,1);
        DSHARP = soundPool.load(this,R.raw.d_4,1);
        E = soundPool.load(this,R.raw.e4,1);
        F = soundPool.load(this,R.raw.f4,1);
        FSHARP = soundPool.load(this,R.raw.f_4,1);
        G = soundPool.load(this,R.raw.g4,1);
        GSHARP = soundPool.load(this,R.raw.g_4,1);
        A  = soundPool.load(this,R.raw.a5,1);
        ASHARP = soundPool.load(this,R.raw.a_5,1);
        B = soundPool.load(this,R.raw.b5,1);

    }
    public void playSound(View v){
        switch (v.getId()){
            case R.id.C:
                soundPool.play(C, 1,1, 0,0, 1);
                break;
            case R.id.CSHARP:
                soundPool.play(CSHARP, 1,1, 0,0, 1);
                break;
            case R.id.D:
                soundPool.play(D, 1,1, 0,0, 1);
                break;
            case R.id.DSHARP:
                soundPool.play(DSHARP, 1,1, 0,0, 1);
                break;
            case R.id.E:
                soundPool.play(E, 1,1, 0,0, 1);
                break;
            case R.id.F:
                soundPool.play(F, 1,1, 0,0, 1);
                break;
            case R.id.FSHARP:
                soundPool.play(FSHARP, 1,1, 0,0, 1);
                break;
            case R.id.G:
                soundPool.play(G, 1,1, 0,0, 1);
                break;
            case R.id.GSHARP:
                soundPool.play(GSHARP, 1,1, 0,0, 1);
                break;
            case R.id.A:
                soundPool.play(A, 1,1, 0,0, 1);
                break;
            case R.id.ASHARP:
                soundPool.play(ASHARP, 1,1, 0,0, 1);
                break;
            case R.id.B:
                soundPool.play(B, 1,1, 0,0, 1);
                break;
        }
    }

    protected void onDestory(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}