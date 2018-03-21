package com.example.jorge.listanegra_v1;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Jorge on 08/03/2018.
 */

public class ActivityContactos extends AppCompatActivity {

    SimpleCursorAdapter mAdapter;
    MatrixCursor mMatrixCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mMatrixCursor = new MatrixCursor(new String[] {"_id","name","details"} );

        mAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.lv_contacts, null, new String[] {"name","details"},
                new int[] { R.id.tv_name,R.id.tv_details}, 0);

        ListView lstContacts = (ListView) findViewById(R.id.lst_contacts);

        lstContacts.setAdapter(mAdapter);

        final ListViewContactsLoader listViewContactsLoader = new ListViewContactsLoader();

        listViewContactsLoader.execute();

        lstContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String number = ""+mMatrixCursor.getString(2);
                String name = ""+mMatrixCursor.getString(1);

                //Toast.makeText(ActivityContactos.this, number+"\n"+name, Toast.LENGTH_LONG).show();

                Intent atras = new Intent();
                atras.putExtra("stringNumber", number);
                atras.putExtra("stringName", name);

                setResult(RESULT_OK, atras);
                finish();

                return false;

            }

        });

    }



    private class ListViewContactsLoader extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {

            Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;

            Cursor contactsCursor = getContentResolver().query(contactsUri, null, null, null,
                    ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

            if(contactsCursor.moveToFirst()){

                do{

                    long contactId = contactsCursor.getLong(contactsCursor.getColumnIndex("_ID"));

                    Uri dataUri = ContactsContract.Data.CONTENT_URI;

                    Cursor dataCursor = getContentResolver().query(dataUri, null, ContactsContract.Data.CONTACT_ID + "=" + contactId, null, null);

                    String displayName="";
                    String homePhone="";
                    String mobilePhone="";
                    String workPhone="";

                    if(dataCursor.moveToFirst()){

                        displayName = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME ));

                        do{

                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
                                switch(dataCursor.getInt(dataCursor.getColumnIndex("data2"))){
                                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME :
                                        homePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;
                                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE :
                                        mobilePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;
                                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK :
                                        workPhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;
                                }
                            }

                        }while(dataCursor.moveToNext());

                        String details = "";

                        if(homePhone != null && !homePhone.equals("") )
                            details = "" + homePhone;
                        if(mobilePhone != null && !mobilePhone.equals("") )
                            details += "" + mobilePhone;
                        if(workPhone != null && !workPhone.equals("") )
                            details += "" + workPhone;

                        mMatrixCursor.addRow(new Object[]{ Long.toString(contactId),displayName,details});

                    }

                }while(contactsCursor.moveToNext());

            }

            return mMatrixCursor;

        }

        @Override
        protected void onPostExecute(Cursor result) {

            mAdapter.swapCursor(result);

        }

    }

}
