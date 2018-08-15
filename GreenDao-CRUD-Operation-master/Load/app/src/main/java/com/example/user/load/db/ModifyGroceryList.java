package com.example.user.load.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akinsete.ourgrocerylist.db.Grocery;
import com.example.user.load.R;

public class ModifyGroceryList extends AppCompatActivity {

	Product product;
	DaoSession daoSession;

	EditText name,year;
	Button btn_save;

	boolean createNew = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_grocery_list);

		name = (EditText)findViewById(R.id.name);
		year = (EditText)findViewById(R.id.year);
		btn_save = (Button) findViewById(R.id.btn_save);

		daoSession = ((AppController) getApplication()).getDaoSession();

		handleIntent(getIntent());

		setClickEventListener();
	}


	private void handleIntent(Intent intent) {
		createNew = intent.getBooleanExtra("create",false);

		//// This means we are editing a grocery item
		if(!createNew){
			product = (Product) intent.getSerializableExtra("grocery");
			name.setText(product.getName());
			year.setText(product.getYear());
		}
	}


	private void setClickEventListener() {
		btn_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(createNew){
					insetItem();
				}else{
					updateItem(product.getId());
				}
			}
		});
	}


	private void updateItem(long id){
		GroceryDao groceryDao = daoSession.getGroceryDao();
		product product = new Product();
		product.setId(id);
		product.setName(name.getText().toString());
		product.setYear(Integer.parseInt(year.getText().toString()));
		groceryDao.saveInTx(product);
		Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
		finish();
	}

	private void insetItem(){
		GroceryDao groceryDao = daoSession.getGroceryDao();
		product product = new Product();
		product.setName(name.getText().toString());
		product.setYear(Integer.parseInt(year.getText().toString()));
		groceryDao.insert(product);
		Toast.makeText(this, "Item inserted", Toast.LENGTH_SHORT).show();
		finish();
	}
}
