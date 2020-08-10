package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Vector;

public class ActivityDefence extends AppCompatActivity {

    Toolbar toolbar5;

    RecyclerView recyclerView;
    Vector<youtube> youtubeVideos = new Vector<youtube>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence);

        toolbar5=(Toolbar)findViewById(R.id.defence_toolbar);
        setSupportActionBar(toolbar5);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M4_8PoRQP8w\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0UqK3tfuu8Q\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/k9Jn0eP-ZVg\" frameborder=\"0\" allowfullscreen></iframe>") );

        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KVpxP3ZZtAc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9IG_h_2T-3Q\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/T7aNSRoDCmg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KiAsbTIcPcY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CKaa19kpqzM\" frameborder=\"0\" allowfullscreen></iframe>") );

        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VIu9sCogmrs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2fb8ztTAPG8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youtube("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Gx3_x6RH1J4\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

