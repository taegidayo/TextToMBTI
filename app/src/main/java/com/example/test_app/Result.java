package com.example.test_app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends Activity {

    ImageView resultImageView;
    ImageButton share;
    ImageButton chat;
    TextView mindTextView,energyTextView,natureTextView,tacticsTextView;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        scrollView = findViewById(R.id.scrollView);
        resultImageView = findViewById(R.id.resultImageView);
        TextView textView= findViewById(R.id.textview);
        chat = findViewById(R.id.outputButton2);
        share= findViewById(R.id.share);
        ImageButton shareButton = findViewById(R.id.share);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        textView.setText("내 MBTI:"+image.toUpperCase());
        double mind=intent.getDoubleExtra("mind",0.5);
        double energy=intent.getDoubleExtra("energy",0.5);
        double nature=intent.getDoubleExtra("nature",0.5);
        double tactics=intent.getDoubleExtra("tactics",0.5);
        mindTextView=findViewById(R.id.mind_text);
        energyTextView=findViewById(R.id.energy_text);
        natureTextView=findViewById(R.id.nature_text);
        tacticsTextView=findViewById(R.id.tactics_text);


        ProgressBar mindProgressBar = findViewById(R.id.mind);
        ProgressBar energyProgressBar = findViewById(R.id.energy);
        ProgressBar natureProgressBar = findViewById(R.id.nature);
        ProgressBar tacticsProgressBar = findViewById(R.id.tactics);

        mindTextView.setText(mind>0.5?("E:"+((int)(mind*100))+"%"):("I:"+(100-(int)(mind*100)))+"%");
        energyTextView.setText(energy>0.5?("N:"+((int)(energy*100))+"%"):("S:"+(100-(int)(energy*100)))+"%");
        natureTextView.setText(nature>0.5?("F:"+((int)(nature*100))+"%"):("T:"+(100-(int)(nature*100)))+"%");
        tacticsTextView.setText(tactics>0.5?("J:"+((int)(tactics*100)))+"%":("P:"+(100-(int)(tactics*100)))+"%");

        mindProgressBar.setProgress((int)(mind*100));
        energyProgressBar.setProgress((int)(energy*100));
        natureProgressBar.setProgress((int)(nature*100));
        tacticsProgressBar.setProgress((int)(tactics*100));

        if (image != null) {
            int drawableId = getResources().getIdentifier(image, "drawable", getPackageName());
            if (drawableId != 0) {
                resultImageView.setImageResource(drawableId);
                resultImageView.setVisibility(View.VISIBLE);
            }
        }

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Chat.class);
                intent.putExtra("mbti",image);
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                captureAndShare();

            }
        });



    }

    private void captureAndShare() {
        Bitmap screenshot = captureScrollView(scrollView);
        if (screenshot != null) {
            File imageFile = saveScreenshot(screenshot);
            if (imageFile != null) {
                shareImage(imageFile);
            } else {
                Toast.makeText(this, "이미지 저장 실패", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "스크린샷 캡쳐 실패", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap captureScrollView(ScrollView scrollView) {
        int width = scrollView.getWidth();
        int height = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            height += scrollView.getChildAt(i).getHeight();
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    private File saveScreenshot(Bitmap screenshot) {
        File file = new File(getExternalCacheDir(), "screenshot.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            screenshot.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void shareImage(File imageFile) {
        Uri imageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", imageFile);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "이미지 공유"));
    }
}

