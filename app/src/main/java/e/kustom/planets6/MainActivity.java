package e.kustom.planets6;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import e.kustom.planets6.models.PlanetsResults;
import e.kustom.planets6.network.GetPlanet;

public class MainActivity extends AppCompatActivity {

     TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundPlanet().execute();

                nameTv = findViewById(R.id.nameTv);



            }
        });
    }


    private class BackgroundPlanet extends GetPlanet{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(PlanetsResults planetsResults) {
            if (planetsResults != null) {
                nameTv= findViewById(R.id.nameTv);
                nameTv.setText(planetsResults.getName());
            }
        }
    }


}
