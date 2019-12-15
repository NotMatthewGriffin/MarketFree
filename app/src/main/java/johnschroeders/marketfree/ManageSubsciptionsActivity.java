package johnschroeders.marketfree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageSubsciptionsActivity extends AppCompatActivity {
    private static final String TAG = "SubscriptionsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subsciptions);
        //button referencing for managing subscriptions activity/view/layout
        Button subscriptionsBackButton = findViewById(R.id.manageSubscriptionsBackButton);
        Button addSubScriptionsButton = findViewById(R.id.manageSubscriptionsaddButton);

        subscriptionsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserMainPageActivity.class);
                startActivity(intent);
            }
        });

        //TODO add functionality to adding subcriptions to firestore
        addSubScriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Sub added", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Log.d(TAG, "loading data for subscriptions");
        //TODO need to load true subscriptions for each person from firestore
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Deborah");
        names.add("Useless");
        names.add("Brandumb");
        names.add("Richard");
        names.add("Idiot");
        Log.d(TAG, "loading data for subscriptions completed successfully");

        //
        Log.d(TAG, "creating recyclerview for the subscriptions view");
        RecyclerView recyclerView = findViewById(R.id.manageSubscriptionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d(TAG, "recyclerview created for subscriptions and recyclerlayout set to " + this);
        RecyclerView.Adapter mAdapter = new MyRecyclerViewAdapterforSubscriptions(this, names);
        Log.d(TAG, "adapter initialized for orders");
        recyclerView.setAdapter(mAdapter);
        Log.d(TAG, "adapter successfully setup");
    }
}