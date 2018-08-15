package com.example.user.appproviderrecycle;

import android.annotation.SuppressLint;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lvItems;
    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            return;
        }
        lvItems = (ListView) findViewById(R.id.lvItems);
        mAdapter = new DataAdapter(this, null, 0);
        lvItems.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, this);

    }
    @Override
    public Loader onCreateLoader(int loaderID, Bundle arg1) {
        //в этом методе мы создаем CursorLoader c определенным sql-запросом. В данном случае нам нужно выбрать все записи из таблицы Classes, и вместо условий выборки и сортировки задаем null.
        return new CursorLoader(
                this,
                ContractClass.Classes.CONTENT_URI, //uri для таблицы Classes
                ContractClass.Classes.DEFAULT_PROJECTION, //список столбцов, которые должны присутствовать в выборке
                null, // условие WHERE для выборки
                null, // аргументы для условия WHERE
                null); // порядок сортировки
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor newData) {
        //этот метод вызывается после получения данных из БД. Адаптеру
        //посылаются новые данные в виде Cursor и сообщение о том, что
        //данные обновились и нужно заново отобразить список.
        mAdapter.swapCursor(newData);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        //если в полученном результате sql-запроса нет никаких строк,
        //то говорим адаптеру, что список нужно очистить
        mAdapter.swapCursor(null);
    }


}
