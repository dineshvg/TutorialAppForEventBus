package com.mail.dinesh.tinybustest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mail.dinesh.tinybustest.R;
import com.mail.dinesh.tinybustest.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

// Source : http://greenrobot.org/eventbus/documentation/how-to-get-started/

public class MainActivity extends AppCompatActivity {



    public static final String TAG = "EventBus Tutorial App";
    EditText publisher;
    Button green_robot;
    TextView subscriber;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        green_robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(publisher.getText()!=null) {
                    String textToPublish = publisher.getText().toString();
                    EventBus.getDefault().post(new MessageEvent(textToPublish));
                }

            }
        });
    }

    /**
     *
     */
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     *
     */
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Method to initialize all the variables.
     */
    private void init() {

        publisher = (EditText) findViewById(R.id.user_entry);
        subscriber = (TextView) findViewById(R.id.green_robot_subscriber);
        green_robot = (Button) findViewById(R.id.green_robot);
    }

    /**
     * @param event
     */
    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        subscriber.setText(event.message);
        //Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }



}
