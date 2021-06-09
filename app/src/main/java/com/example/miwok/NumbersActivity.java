package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity  {
 private MediaPlayer mMediaPlayer;
 private AudioManager mAudioManager;
 private MediaPlayer.OnCompletionListener completionInstance=new MediaPlayer.OnCompletionListener() {
     @Override
     public void onCompletion(MediaPlayer mp) {
         releaseMediaPlayer();

     }
 };

 private AudioManager.OnAudioFocusChangeListener mOnAudioChangelistener = new AudioManager.OnAudioFocusChangeListener() {
     @Override
     public void onAudioFocusChange(int focusChange) {
         if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
             mMediaPlayer.pause();
             mMediaPlayer.seekTo(0);
         }else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
             mMediaPlayer.start();
         }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
             releaseMediaPlayer();
         }
     }
 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("one",R.drawable.number_one,"Lutti",R.raw.number_one));
        words.add(new Word("two",R.drawable.number_two,"otiiko",R.raw.number_two));
        words.add(new Word("three",R.drawable.number_three,"tolookosu",R.raw.number_three));
        words.add(new Word("four",R.drawable.number_four,"oyyisa",R.raw.number_four));
        words.add(new Word("five",R.drawable.number_five,"massoka",R.raw.number_five));
        words.add(new Word("six",R.drawable.number_six,"temmokka",R.raw.number_six));
        words.add(new Word("seven",R.drawable.number_seven,"kenekaku",R.raw.number_seven));
        words.add(new Word("eight",R.drawable.number_eight,"kawinta",R.raw.number_eight));
        words.add(new Word("nine",R.drawable.number_nine,"wo'e",R.raw.number_nine));
        words.add(new Word("ten",R.drawable.number_ten,"na'aacha",R.raw.number_ten));
        
       WordAdapater itemsAdapter;
        itemsAdapter = new WordAdapater(this, words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list_numbers);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);

                int request=mAudioManager.requestAudioFocus(mOnAudioChangelistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                Log.v("NumberActivity","Requestasked");
                if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(completionInstance);
                }
            }
        });


    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioChangelistener);

        }
    }
    }
