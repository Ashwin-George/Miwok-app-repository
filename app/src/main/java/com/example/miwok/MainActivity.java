/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.miwok;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView numbers=(TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent numbersintent= new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numbersintent);

            }
        });


        TextView family=(TextView) findViewById(R.id.family);
       family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent familyintent= new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyintent);

            }
        });


        TextView colors=(TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent colorsintent= new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsintent);

            }
        });
        TextView phrases=(TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent phrasesintent= new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesintent);

            }
        });

    }
    public void openNumbersList(View v)
    {
        Intent i= new Intent(this,NumbersActivity.class);
          startActivity(i);
    }
}
