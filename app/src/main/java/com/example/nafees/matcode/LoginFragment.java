package com.example.nafees.matcode;

import android.app.ProgressDialog;
import android.arch.core.executor.TaskExecutor;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginFragment extends Fragment {

    EditText email,password;
    TextView login;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();

        return inflater.inflate(R.layout.login_tab, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                    JSONObject json = convertToJson(email.getText().toString(),password.getText().toString());

                    new CheckUser(json,context).execute();

                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public JSONObject convertToJson (String name, String password){

        JSONObject json = new JSONObject();

        try {

            json.accumulate("username",name);
            json.accumulate("password",password);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  json;
    }

    public boolean validate(){

        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            email.setError("Enter valid Email address!");
            return false;
        }

        if(password.length() == 0)
        {
            password.setError("Enter valid Password!");
            return false;
        }

        return true;
    }

}

 class CheckUser extends AsyncTask<Void,Void,Void>{

    JSONObject json;
    ProgressDialog dialog;
    Context context;
    String token;

    CheckUser(JSONObject json,Context context){
        this.json = json;
        this.context = context;
    }

     @Override
     protected void onPreExecute() {

         dialog = new ProgressDialog(context);
         dialog.setMessage("Working ...");
         dialog.setCancelable(false);
         dialog.show();

         super.onPreExecute();
     }

     @Override
    protected Void doInBackground(Void... voids) {

        HttpHelper helper = new HttpHelper();

        try {

            token = helper.Post("https://decodeapp1.herokuapp.com/api/login/",json.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

     @Override
     protected void onPostExecute(Void aVoid) {
         dialog.dismiss();
         super.onPostExecute(aVoid);
     }
 }