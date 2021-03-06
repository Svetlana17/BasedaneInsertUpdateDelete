package com.akinsete.ourgrocerylist.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GROCERY".
*/
public class GroceryDao extends AbstractDao<Grocery, Long> {

    public static final String TABLENAME = "GROCERY";

    /**
     * Properties of entity Grocery.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property last_name = new Property(0, Long.class, "last_name", true, "_last_name");
        public final static Property First_mane = new Property(1, String.class, "first_name", false, "first_name");

    }


    public GroceryDao(DaoConfig config) {
        super(config);
    }
    
    public GroceryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GROCERY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"QUANTITY\" INTEGER NOT NULL ," + // 2: quantity
                "\"STATUS\" TEXT);"); // 3: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GROCERY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Grocery entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
//        stmt.bindLong(3, entity.getFirst_name());
 
//        String status = entity.getStatus();
//        if (status != null) {
//            stmt.bindString(4, status);
//        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Grocery entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
//        stmt.bindLong(3, entity.getFirst_name());
 
//        String status = entity.getStatus();
//        if (status != null) {
//            stmt.bindString(4, status);
        }
//    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Grocery readEntity(Cursor cursor, int offset) {
        Grocery entity = new Grocery( //
                cursor.getString(offset + 1), // name

            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // id

            cursor.getString(offset + 2) // quantity

        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Grocery entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setFirst_name(cursor.getString(offset + 2));

     }
    
    @Override
    protected final Long updateKeyAfterInsert(Grocery entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Grocery entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Grocery entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
