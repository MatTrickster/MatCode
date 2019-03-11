package com.example.nafees.matcode;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nafees.matcode.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterFragment extends Fragment {

    EditText name,email,password;
    TextView register;

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.register_tab, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        register = view.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                    JSONObject json = convertToJson(name.getText().toString(),email.getText().toString(),password.getText().toString());

                    new RegisterUser(json,context).execute();

                }
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }

    public boolean validate(){

        if(name.getText().length() < 2){
            name.setError("Enter valid Name");
            return false;
        }

        if(TextUtils.isEmpty(email.getText().toString()) && !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Enter valid Email address!");
            return false;
        }

        if(password.length() < 7) {
            password.setError("Password too short!");
            return false;
        }

        return true;
    }

    public JSONObject convertToJson(String name,String email,String password){

        JSONObject json = new JSONObject();
        try {

            json.accumulate("name",name);
            json.accumulate("email",email);
            json.accumulate("password",password);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json;
    }

}

class RegisterUser extends AsyncTask<Void,Void,Void>{

    private String data;
    ProgressDialog dialog;
    Context context;

    RegisterUser(JSONObject json,Context context){
        this.data = json.toString();
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

            String token = helper.Post("https://decodeapp1.herokuapp.com/api/profile/",data);

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
