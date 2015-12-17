package com.example.decimal.avinashgreendao;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mSqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ContactDao mContactDao;
    private Cursor cursor;
    private Button mButton,mButtonBulk,mListContact;
    private ProgressDialog mProgressDialog;
    int bulk=20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= (Button) findViewById(R.id.AddButton);
        mButtonBulk= (Button) findViewById(R.id.btn_bulkInsert);
        mListContact= (Button) findViewById(R.id.listContact);


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Contact", null);
        mSqLiteDatabase=helper.getWritableDatabase();
        daoMaster = new DaoMaster(mSqLiteDatabase);
        daoSession = daoMaster.newSession();
        mContactDao = daoSession.getContactDao();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        mButtonBulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BulkInsertAsync(MainActivity.this).execute("Sample");
//                FastEntry();
//                    thread.start();
            }

        });
        mListContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListContact.class));
            }
        });
    }

    private void addContact() {
        String FirstName = "Avinash";
        String LastName = "Pandey";
        Contact mContactInsert = new Contact(null, FirstName,LastName);
        mContactDao.insert(mContactInsert);
        Log.v("Avinash", "Inserted new note, ID: " + mContactInsert.getId());
    }

    public void FastEntry(){

        Contact[] newsArticles = new Contact[bulk+1];
        Contact testContact;
        for (int i = 0; i <=bulk; i++) {
            testContact = new Contact(null, "Avinash" + i,"Pandey" + i);
            newsArticles[i] = testContact;
            Log.v("Avinash",String.valueOf(i));
            mProgressDialog.setProgress(i);
        }
        mContactDao.insertInTx(newsArticles);
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                FastEntry();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    class BulkInsertAsync extends AsyncTask<String,Integer,String>{



        private Context mContext;

        public BulkInsertAsync(Context mContext){
            this.mContext=mContext;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setTitle("Bulk Insert Off Main Thread");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            mProgressDialog.setMax(bulk);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
//            thread.start();
            FastEntry();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressDialog.dismiss();

        }
    }
}
