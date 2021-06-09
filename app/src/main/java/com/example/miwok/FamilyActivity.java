package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener completionInstance = new MediaPlayer.OnCompletionListener() {
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
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Father", R.drawable.family_father, "әpә", R.raw.family_father));
        words.add(new Word("Mother", R.drawable.family_mother, "әṭa", R.raw.family_mother));
        words.add(new Word("Son", R.drawable.family_son, "angsi", R.raw.family_son));
        words.add(new Word("Daughter", R.drawable.family_daughter, "tune", R.raw.family_daughter));
        words.add(new Word("Older Brother", R.drawable.family_older_brother, "taachi", R.raw.family_older_brother));
        words.add(new Word("Younger Brother", R.drawable.family_younger_brother, "chalati", R.raw.family_younger_brother));
        words.add(new Word("Older Sister", R.drawable.family_older_sister, "tete", R.raw.family_older_sister));
        words.add(new Word("Younger Sister", R.drawable.family_younger_sister, "kolliti", R.raw.family_younger_sister));
        words.add(new Word("Grandmother", R.drawable.family_grandmother, "ama", R.raw.family_grandmother));
        words.add(new Word("Grandfather", R.drawable.family_grandfather, "paapa", R.raw.family_grandfather));
        WordAdapater itemsAdapter;
        itemsAdapter = new WordAdapater(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list_numbers);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int request= mAudioManager.requestAudioFocus(mOnAudioChangelistener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());

                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(completionInstance);
                }
            }
        });
    }

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
            Log.v("PhrasesActivity","abandoned focus");
        }
    }
}

