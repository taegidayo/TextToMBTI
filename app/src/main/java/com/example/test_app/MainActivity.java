package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    String ipAddress = "http://10.20.36.179:5000/?text=";
    ImageButton btn_main;

    Button btn_log;

    EditText editText;


    DatabaseHelper dbHelper;

    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        btn_main = findViewById(R.id.inputButton1);
        editText = findViewById(R.id.editText);
        btn_log=findViewById(R.id.logButton);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Log.class);
                startActivity(intent);
            }
        });

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString().trim();
                String mbti = "";


                new Thread(new Runnable() {
                    @Override
                    public void run() {




                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

//                                    System.out.println(ipAddress + inputText);
//                                    URL url = new URL(ipAddress + inputText);
//                                    String encodeResult = URLEncoder.encode(inputText);
                                    // HttpURLConnection 생성 및 설정
//                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                                    conn.setRequestMethod("GET");

                                    // 응답 코드 확인
//                                    int responseCode = conn.getResponseCode();
//                                    if (responseCode == HttpURLConnection.HTTP_OK) {
                                    // 응답 데이터 읽기
//                                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                                        String line;
//                                        StringBuilder response = new StringBuilder();
//                                        while ((line = reader.readLine()) != null) {
//                                            response.append(line);
//                                        }
//                                        reader.close();

                                    // 응답 데이터 처리
//                                        String responseData = response.toString();
//                                        System.out.println(responseData);
//                                        JSONObject object = new JSONObject(responseData);

                                    String mbti = "";

                                    double mind = 0.64;
                                    double energy = 0.71;
                                    double nature = 0.24;
                                    double tactics = 0.58;

//                                        double mind = (double) object.getDouble("E");
//                                        double energy = (double) object.getDouble("N");
//                                        double nature = (double) object.getDouble("F");
//                                        double tactics = (double) object.getDouble("J");
                                    mbti += (mind > 0.5) ? "e" : "i";
                                    mbti += (energy > 0.5) ? "n" : "s";
                                    mbti += (nature > 0.5) ? "f" : "t";
                                    mbti += (tactics > 0.5) ? "j" : "p";

                                    sqlDB = dbHelper.getWritableDatabase();
                                    sqlDB.execSQL("INSERT INTO USER (text,mbti,mind,energy,nature,tactics)VALUES ('" + inputText + "','" + mbti + "','" + (Math.round(mind * 100.0) / 100.0) + "','" + (Math.round(energy * 100.0) / 100.0) + "','" + (Math.round(nature * 100.0) / 100.0) + "','" + (Math.round(nature * 100.0) / 100.0) + "')");
//                                        System.out.println("INSERT INTO USER (text,mbti,mind,energy,nature,tactics)VALUES ('" + inputText + "','" + mbti + "','" + (Math.round(mind * 100.0) / 100.0) + "','" + (Math.round(energy * 100.0) / 100.0) + "','"+(Math.round(nature * 100.0) / 100.0)+"','"+(Math.round(nature * 100.0) / 100.0)+"')");
                                    Intent intent = new Intent(getApplicationContext(), Result.class);


                                    intent.putExtra("image", mbti);
                                    intent.putExtra("mind", Math.round(mind * 100.0) / 100.0);
                                    intent.putExtra("energy", Math.round(energy * 100.0) / 100.0);
                                    intent.putExtra("nature", Math.round(nature * 100.0) / 100.0);
                                    intent.putExtra("tactics", Math.round(tactics * 100.0) / 100.0);
                                    startActivity(intent);

//                                    } else {
//                                        System.out.println("API 호출 실패: " + responseCode);
//                                    }

//                                    conn.disconnect();

                                }catch (Exception e){
                                    System.out.println("1");
                                }
//                                } catch (MalformedURLException e) {
//
//
//                                    throw new RuntimeException(e);
//                                } catch (ProtocolException e) {
//                                    throw new RuntimeException(e);
//                                } catch (IOException e) {
//                                    throw new RuntimeException(e);
//                                } catch (JSONException e) {
//                                    throw new RuntimeException(e);
//                                }
                            }
                        });
                    }
                }).start();

            }
        });

    }
}