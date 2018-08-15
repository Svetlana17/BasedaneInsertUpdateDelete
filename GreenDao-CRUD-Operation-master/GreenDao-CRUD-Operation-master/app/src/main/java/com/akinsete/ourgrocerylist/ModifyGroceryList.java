package com.akinsete.ourgrocerylist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akinsete.ourgrocerylist.db.DaoSession;
import com.akinsete.ourgrocerylist.db.Grocery;
import com.akinsete.ourgrocerylist.db.GroceryDao;

public class ModifyGroceryList extends AppCompatActivity {

	Grocery grocery;
	DaoSession daoSession;
    EditText id;
	EditText last_name;
	EditText first_name;

	Button btn_save;

	boolean createNew = false;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_grocery_list);
        id=(EditText)findViewById(R.id.id);
		last_name = (EditText) findViewById(R.id.last_name);
		first_name = (EditText) findViewById(R.id.first_name);



		btn_save = (Button) findViewById(R.id.btn_save);

		daoSession = ((AppController) getApplication()).getDaoSession();

		handleIntent(getIntent());

		setClickEventListener();
	}


	private void handleIntent(Intent intent) {
		createNew = intent.getBooleanExtra("create", false);

		//// This means we are editing a grocery item
		if (!createNew) {
			grocery = (Grocery) intent.getSerializableExtra("grocery");
			last_name.setText(grocery.getName());
			first_name.setText(grocery.getName());


		}
	}


	private void setClickEventListener() {
		btn_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (createNew) {
					insetItem();
				} else {
					updateItem(grocery.getId());
				}
			}
		});
	}


	private void updateItem(long id){
		GroceryDao groceryDao = daoSession.getGroceryDao();
		Grocery grocery = new Grocery();
		grocery.setId(id);
		grocery.setName(last_name.getText().toString());
//		grocery.setFirst_name(Integer.parseInt(first_name.getText().toString()));
		groceryDao.saveInTx(grocery);
		Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
		finish();
	}

	private void insetItem(){
		GroceryDao groceryDao = daoSession.getGroceryDao();
		Grocery grocery = new Grocery();
		grocery.setName(last_name.getText().toString());
//		grocery.setFirst_name(Integer.parseInt(first_name.getText().toString()));
		groceryDao.insert(grocery);
		Toast.makeText(this, "Item inserted", Toast.LENGTH_SHORT).show();
		finish();
	}



}
