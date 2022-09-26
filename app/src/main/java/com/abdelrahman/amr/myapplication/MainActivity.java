package com.abdelrahman.amr.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity  {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.tv) ;

        String TAG = "log";



        /*
        الفانكشن دي بتعمل توكين للجهاز بتاعك اول ما ترن الابليكشن هتعملك توكين وهيظهرلك في التيكست فيو اللي في الmain ودا انت نحتاجه لكل جهاز عندك
        لو خدت التوكن دا وجربت تبعت بيه مسدج من الفاير بيز نفسها هتلاقيه بعت  بس النوتيفيكشن عشان تظهرلك لازم التطبيق يبقي في الbackground
        طب لو عاوز تستقبلها وتظهر والتطبيق في ال foreground  بتحتاج تعمل service
        الكود دا هتستخدمه اول ما اليوزر يفتح الابلكيشن ومكان التوست هتستدعي الفانكشن اللي بتبعت التوكن ترميه في الداتا بيز
        */

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d(TAG, token);
                        text.setText(token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

    }


}