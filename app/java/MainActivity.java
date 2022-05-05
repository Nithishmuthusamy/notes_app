package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Name,Place;
    Button Viewall,Add;
    RecyclerView Recyclerview;
    ArrayList<String> nnr1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=findViewById(R.id.Name);
        Place=findViewById(R.id.Place);
        Viewall=findViewById(R.id.Viewall);
        Add=findViewById(R.id.Add);
        Recyclerview=findViewById(R.id.Recyclerview);
        RecyclerviewAdapter adapter=new RecyclerviewAdapter();
        Recyclerview.setAdapter(adapter);
        Recyclerview.setLayoutManager(new GridLayoutManager(this,1));
        if(Recyclerview==null)
            System.out.println("there is no problem");
        else
            System.out.println("there is problem");
        ArrayList<String> n=new ArrayList<>();
        Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Name.getText().toString().length()!=0) {
                    nnr1.add(Name.getText().toString());
                    //System.out.println(nnr1.size());
                    //System.out.println("oneenenn"+Name.getText().toString());
                    adapter.setarraylist(nnr1);
                    Recyclerview.setAdapter(adapter);
                    DBMS a = new DBMS(MainActivity.this);
                    /*boolean b = */ a.addon(Name.getText().toString());
                    /*if (b)
                        System.out.println(" this is true");
                    else
                        System.out.println("false");*/
                    Toast.makeText(MainActivity.this,"added",Toast.LENGTH_SHORT).show();
                    Name.setText(null);
                    Place.setText(null);
                    //System.out.println("gjhgjhjkg"+Name.getText().toString());
                }
                else
                    Toast.makeText(MainActivity.this,"please enter the name and place",Toast.LENGTH_SHORT).show();
            }
        });
        Viewall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBMS show=new DBMS(MainActivity.this);
                nnr1 =show.getEveryThing();
                //Toast.makeText(MainActivity.this,nrr.toString(), Toast.LENGTH_SHORT).show();
                //System.out.println("onenndshf"+nrr.get(20));
                adapter.setarraylist(nnr1);
                //nnr1.remove(0);
                Recyclerview.setAdapter(adapter);
            }
        });
    }
}
