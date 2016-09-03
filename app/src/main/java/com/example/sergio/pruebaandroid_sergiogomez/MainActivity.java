package com.example.sergio.pruebaandroid_sergiogomez;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //creacion de las variables
    EditText name;
    EditText email;
    EditText date;
    EditText pass;
    Button login;

    //cursor que apuntara a las posiciones de la base de datos
    Cursor cur;
    //
    public String data_name;
    public String data_email;
    public String data_date;
    public String data_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.eName);
        email = (EditText) findViewById(R.id.eEmail);
        date = (EditText) findViewById(R.id.eDate);
        pass = (EditText) findViewById(R.id.ePass);
        login = (Button) findViewById(R.id.bLogin);
        final Intent intent = new Intent(this, menu.class);

        //creacion de la variable que va a administrar la base de datos
        final UserDBHandler db = new UserDBHandler(this);

        db.open();
        cur = db.getAllContacts();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.login), Toast.LENGTH_SHORT).show();
                data_name = name.getText().toString();
                data_email = email.getText().toString();
                data_date = date.getText().toString();
                data_pass = pass.getText().toString();

                //analiza si alguno de los campos está vacio
                if ((data_name != "") && (data_email != "") && (data_date != "") && (data_pass != "")) {
                    //cur = db.getContactbyname(data_name);
                    //long temp_id;
                    //temp_id = db.insertUser(data_name, data_email, data_date, data_pass);
                    startActivity(intent);
                    //show.setText(d_name+"\n"+d_email+"\n"+d_date+"\n");
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.blank_field), Toast.LENGTH_SHORT).show();
                }//end if
            }
        });//end listener
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }//end oncreate method

}
