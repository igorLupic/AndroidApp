package com.example.pmsumail;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsumail.model.Message;
import com.example.pmsumail.model.Tag;
import com.example.pmsumail.model.requestbody.MessageCreateRequestBody;
import com.example.pmsumail.service.MessageService;
import com.example.pmsumail.service.ServiceUtils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEmailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarText;
    private ImageView btnSend;
    private ImageView btnCancel;
    private EditText tagText;
    private static Tag tag = new Tag();
    private MessageService messageService;
    private static Tag tagBody = new Tag();
    private static Message messageBody;
    private Button buttonSend;

    private EditText sendTo;
    private EditText subject;
    private EditText ccEdit;
    private EditText tagCreate;
    private EditText contentEdit;
    private SharedPreferences sharedPreferences;

    public CreateEmailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_email);
        messageService = ServiceUtils.messageService;
        initView();


        setSupportActionBar(toolbar);
    }


    private void initView() {
        sendTo = findViewById(R.id.send_to);
        subject = findViewById(R.id.subject);
        ccEdit = findViewById(R.id.cc_edit);
        tagCreate = findViewById(R.id.tagCreate);
        contentEdit = findViewById(R.id.content_edit);

        toolbar = findViewById(R.id.toolbar);
        btnSend = findViewById(R.id.button_one);
        btnCancel = findViewById(R.id.button_two);
        toolbarText = findViewById(R.id.toolbar_text);
        buttonSend = findViewById(R.id.button_send);
        sharedPreferences = getSharedPreferences(LoginActivity.MyPres, Context.MODE_PRIVATE);

        btnSend.setImageDrawable(getResources().getDrawable(R.drawable.ic_send));
        btnCancel.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel));
        toolbarText.setText("Create message");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageCreateRequestBody messageCreateRequestBody = new MessageCreateRequestBody();
                messageCreateRequestBody.setBcc(ccEdit.getText().toString());
                messageCreateRequestBody.setCc(ccEdit.getText().toString());
                messageCreateRequestBody.setContent(contentEdit.getText().toString());
                messageCreateRequestBody.setSubject(subject.getText().toString());
                messageCreateRequestBody.setFrom(sharedPreferences.getString(LoginActivity.Username, "User"));
                messageCreateRequestBody.setMessageTag(22.2);
                messageCreateRequestBody.setDateTime(new Date());
                messageCreateRequestBody.setTo(sendTo.getText().toString());


                Call<Message> call = messageService.createMessage(messageCreateRequestBody);
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        Log.e("test", "test");
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
