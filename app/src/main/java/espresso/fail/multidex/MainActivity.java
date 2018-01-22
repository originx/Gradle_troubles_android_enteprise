package espresso.fail.multidex;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import org.joda.time.DateTime;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebase(this);

        TextView txtPackage = findViewById(R.id.packetId);
        txtPackage.setText(BuildConfig.APPLICATION_ID);
        TextView txtRemoteConfig = findViewById(R.id.remoteConfig);
        Button btnConfig = findViewById(R.id.firebaseConfig);
        btnConfig.setOnClickListener(view -> {
            FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();
            long cacheExpiration = 0; // 1 hour in seconds.
//            if (config.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
//                cacheExpiration = 0;
//            }
            config.fetch(cacheExpiration).addOnCompleteListener(MainActivity.this, task -> {
                if (task.isSuccessful()) {

                    config.activateFetched();

                    FirebaseRemoteConfigInfo info = config.getInfo();
                    StringBuilder builder = new StringBuilder();
                    builder.append("SUCCESS\n");
                    builder.append("Info - last fetch: ").append(new DateTime(info.getFetchTimeMillis()));
                    builder.append("\nInfo - last status: ").append(info.getLastFetchStatus());
                    builder.append("\nInfo - settings: ").append(info.getConfigSettings());

                    Set<String> allKeys = config.getKeysByPrefix(null);
                    builder.append("\nInfo - keyCount: #").append(allKeys.size()).append("\n");
                    for (String key : allKeys) {
                        builder.append("\n").append(key).append(" = ").append(config.getValue(key).asString());
                    }

                    txtRemoteConfig.setText(builder.toString());
                } else {

                    try {
                        if (task.getException() != null) {
                            throw task.getException();
                        } else {
                            throw new Exception("Something went wrong");
                        }

                    } catch (FirebaseRemoteConfigFetchThrottledException ex) {
                        txtRemoteConfig.setText("ERROR:\n" + ex + "\n" + ex.getMessage() + "\nThrottle end time milis: " + ex.getThrottleEndTimeMillis());
                    } catch (FirebaseRemoteConfigException ex) {
                        txtRemoteConfig.setText("ERROR:\n" + ex + "\n" + ex.getMessage());
                    } catch (Exception ex) {
                        txtRemoteConfig.setText("ERROR:\n" + ex + "\n" + ex.getMessage());
                    }
                }
            });
        });
    }

    private void setupFirebase(Context context) {
        // we test only Austria
        FirebaseOptions.Builder builder = new FirebaseOptions.Builder();
        builder.setApplicationId("1:1093394223259:android:135c9bbacc945e22");
        builder.setApiKey("AIzaSyATyo6Xmq2lFJpQ3LP9KJvlo_vZE-xGnEk");
        builder.setDatabaseUrl("https://db-demo-b395f.firebaseio.com");
//        builder.setGcmSenderId("not set");
        builder.setStorageBucket("db-demo-b395f.appspot.com");
        FirebaseApp.initializeApp(context, builder.build());
    }

}
