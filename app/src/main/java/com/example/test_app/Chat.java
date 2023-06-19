package com.example.test_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pkslow.ai.AIClient;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.pkslow.ai.domain.AnswerStatus;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Chat extends Activity {

    ImageButton input;
    Button button;
    EditText messageEditText;
    ChatAdapter chatAdapter;
    List<Item> itemList;
    TextView myMbti;
    private Button exam1;
    private Button exam2;
    private Button exam3;
    private Button exam4;
    private static String printChosenAnswer(Answer answer) {
        StringBuilder sb = new StringBuilder();

        if (answer.status() == AnswerStatus.OK) {


            StringTokenizer tokenizer = new StringTokenizer(answer.chosenAnswer(), "\n");
            int i=1;
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if(token.charAt(0)=='*'){
                    sb.append(i+". "+token.replace("*","")+'\n');
                    i++;
                    if(i>5)break;
                }
            }
        }
        System.out.println( sb.toString().replace("\n","\\n"));
        if(sb.toString()!="") {
            return sb.toString();
        }
        else{
            return "질문을 이해 할 수 없어요.";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidthPx = displayMetrics.widthPixels;
        int screenHeightPx = displayMetrics.heightPixels;

        getWindow().setLayout(screenWidthPx, screenHeightPx);


        exam1 = findViewById(R.id.example_button1);
        exam2 = findViewById(R.id.example_button2);
        exam3 = findViewById(R.id.example_button3);
        exam4 = findViewById(R.id.example_button4);

        myMbti= findViewById(R.id.mbti);

        messageEditText=findViewById(R.id.messageEditText);
        button=findViewById(R.id.sendButton);

        Intent intent = getIntent();

        String mbti= intent.getStringExtra("mbti");

        myMbti.setText(mbti+"정보 물어보기");
        ListView listView = findViewById(R.id.chatListView);
        itemList = new ArrayList<>();
        chatAdapter= new ChatAdapter(itemList);

        input = findViewById(R.id.ChatButton2);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            listView.setAdapter(chatAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {

                    messageEditText.setText("");
                    addMessage(new Item(message,true));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AIClient client = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                client = new GoogleBardClient("XAgI8QMf-39CaG8X9IGbdhrGD2FgX6l1j4UWx3E50Hu4HnDqoQGqrKoicnXUh9h_ThOR8A.", Duration.ofMinutes(1));
                            }
                            String sendMessage= message;
                            if(message.indexOf(mbti)==-1){
                                sendMessage=mbti+"의"+sendMessage;
                            }

                            Answer answer = client.ask(sendMessage);


//                Toast.makeText(getApplicationContext(), answer.chosenAnswer(),Toast.LENGTH_SHORT);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Pattern pattern = Pattern.compile("(?<=\n) \\*");
//                                    Matcher matcher = pattern.matcher(printChosenAnswer(answer));


                                    StringBuilder sb = new StringBuilder();
                                    System.out.println(answer.status().toString() + 54321);
                                    if (answer.status() == AnswerStatus.OK) {
                                        addMessage(new Item(printChosenAnswer(answer), false));
                                    }
                                }
                            });
                        }
                    }).start();
                }
//                Toast.makeText(getApplicationContext(), "버튼 클릭됨",Toast.LENGTH_SHORT);

            }


        });


        exam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mbti+"의 성격을 알려줘";
                if (!message.isEmpty()) {
                    addMessage(new Item(message,true));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AIClient client = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                client = new GoogleBardClient("XAgI8QMf-39CaG8X9IGbdhrGD2FgX6l1j4UWx3E50Hu4HnDqoQGqrKoicnXUh9h_ThOR8A.", Duration.ofMinutes(1));
                            }
                            Answer answer = client.ask(message);



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addMessage(new Item(printChosenAnswer(answer), false));
                                }
                            });
                        }
                    }).start();
                }
            }
        });
        exam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mbti+"의 연애방식을 알려줘";
                if (!message.isEmpty()) {
                    addMessage(new Item(message,true));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AIClient client = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                client = new GoogleBardClient("XAgI8QMf-39CaG8X9IGbdhrGD2FgX6l1j4UWx3E50Hu4HnDqoQGqrKoicnXUh9h_ThOR8A.", Duration.ofMinutes(1));
                            }
                            Answer answer = client.ask(message);



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addMessage(new Item(printChosenAnswer(answer), false));
                                }
                            });
                        }
                    }).start();
                }
            }
        });
        exam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mbti+"의 이상형을 알려줘";
                if (!message.isEmpty()) {
                    addMessage(new Item(message,true));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AIClient client = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                client = new GoogleBardClient("XAgI8QMf-39CaG8X9IGbdhrGD2FgX6l1j4UWx3E50Hu4HnDqoQGqrKoicnXUh9h_ThOR8A.", Duration.ofMinutes(1));
                            }
                            Answer answer = client.ask(message);



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addMessage(new Item(printChosenAnswer(answer), false));
                                }
                            });
                        }
                    }).start();
                }
            }
        });
        exam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mbti+"의 특징을 알려줘";
                if (!message.isEmpty()) {
                    addMessage(new Item(message,true));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AIClient client = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                client = new GoogleBardClient("XAgI8QMf-39CaG8X9IGbdhrGD2FgX6l1j4UWx3E50Hu4HnDqoQGqrKoicnXUh9h_ThOR8A.", Duration.ofMinutes(1));
                            }
                            Answer answer = client.ask(message);



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addMessage(new Item(printChosenAnswer(answer), false));
                                }
                            });
                        }
                    }).start();
                }
            }
        });


    }

    private void addMessage(Item message) {
        itemList.add(message);
        chatAdapter.notifyDataSetChanged();
    }
}
