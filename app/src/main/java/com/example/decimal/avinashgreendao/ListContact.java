package com.example.decimal.avinashgreendao;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ListContact extends AppCompatActivity {

    ListView mListContact;
    private SQLiteDatabase mSqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ContactDao mContactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Contact", null);
        mSqLiteDatabase=helper.getWritableDatabase();
        daoMaster = new DaoMaster(mSqLiteDatabase);
        daoSession = daoMaster.newSession();
        mContactDao = daoSession.getContactDao();
        mListContact= (ListView) findViewById(R.id.listView);
        new loadDataAsync(this).execute("");

    }

    class loadDataAsync extends AsyncTask<String,Integer,List<Contact>>{

        private ProgressDialog mProgressDialog;
        private Context mContext;

        public loadDataAsync(Context mContext){
            this.mContext=mContext;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setTitle("Please Wait.........");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgress(0);
            mProgressDialog.setMax(100);
            mProgressDialog.show();
        }

        @Override
        protected List<Contact> doInBackground(String... params) {
            List<Contact> fileObjList=mContactDao.loadAll();
            int progNumber=fileObjList.size();
            publishProgress(progNumber);
            return fileObjList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<Contact> fileObjList) {
            super.onPostExecute(fileObjList);
            mListContact.setAdapter(new ArrayAdapter<>(ListContact.this, android.R.layout.simple_list_item_1, fileObjList));
            mProgressDialog.dismiss();
        }
    }
}
