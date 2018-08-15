package com.example.user.load;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.user.load.db.ModifyGroceryList;
import com.example.user.load.db.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Product> products = new ArrayList<>();
//    DaoSession daoSession;
    ArrayAdapter<Product> groceryArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
//       daoSession = ((AppController) getApplication()).getDaoSession();

        setupListView();
    }


    @Override
    protected void onResume() {
        super.onResume();

        fetchGroceryList();
    }


    private void setupListView() {
        groceryArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,products);
        listView.setAdapter(groceryArrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showOptions(position);

                return false;
            }
        });
    }


      private void showOptions(int position) {
     final Product selectedProductItem = products.get(position);
//
   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//
        String[] options = new String[2];
//
       options[0] = "Edit " + selectedProductItem.getName();
       options[1] = "Delete " + selectedProductItem.getName();
//
        alertDialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    proceedToUpdateItem(selectedProductItem);
                }else if(which == 1){
                    deleteProductItem(selectedProductItem.getId());
                }

            }

            private void proceedToUpdateItem(Product selectedProductItem) {
                private void proceedToUpdateItem(Product product){
                    // Pass grocery id to the next screen
                    Intent intent = new Intent(this,ModifyGroceryList.class);
                    intent.putExtra("create",false);
                    intent.putExtra("product", (Serializable) product);
                    startActivity(intent);
                }


            }

        });
        alertDialogBuilder.create().show();
    }


    private void fetchGroceryList(){
        products.clear();
        //// Get the entity dao we need to work with.
//        GroceryDao groceryDao = daoSession.getGroceryDao();

            Load all items
//        groceries.addAll(groceryDao.loadAll());

        /// Notify our adapter of changes
//        groceryArrayAdapter.notifyDataSetChanged();
    }


    private void deleteProducrItem(long id){
        //// Get the entity dao we need to work with.
        GroceryDao groceryDao = daoSession.getGroceryDao();
//        / perform delete operation
        groceryDao.deleteByKey(id);

        fetchGroceryList();
    }




            public void addNewItem(View view) {
//        // Go to add item activity
         Intent intent = new Intent(this,ModifyGroceryList.class);
         intent.putExtra("create",true);
         startActivity(intent);
    }
}









