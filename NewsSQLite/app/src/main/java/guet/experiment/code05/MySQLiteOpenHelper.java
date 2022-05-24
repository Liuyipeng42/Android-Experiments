package guet.experiment.code05;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            " CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                    NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY , " +
                    NewsContract.NewsEntry.COLUMN_NAME_TITLE + " VARCHAR (200) , " +
                    NewsContract.NewsEntry.COLUMN_NAME_AUTHOR + " VARCHAR (100) , " +
                    NewsContract.NewsEntry.COLUMN_NAME_CONTENT + " TEXT , " +
                    NewsContract.NewsEntry.COLUMN_NAME_IMAGE + " VARCHAR (100) " +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "news.db";

    private Context mContext;


    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        initDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private void initDb(SQLiteDatabase db) {
        Resources resources = mContext.getResources();
        String[] titles = resources.getStringArray(R.array.titles);
        String[] authors = resources.getStringArray(R.array.authors);
        String[] contents = resources.getStringArray(R.array.contents);
        @SuppressLint("Recycle") TypedArray images = resources.obtainTypedArray(R.array.images);

        int length;
        length = Math.min(titles.length, authors.length);
        length = Math.min(length, contents.length);
        length = Math.min(length, images.length());

        for (int i = 0; i < length; i++) {
            ContentValues values = new ContentValues();
            values.put(NewsContract.NewsEntry.COLUMN_NAME_TITLE, titles[i]);
            values.put(NewsContract.NewsEntry.COLUMN_NAME_AUTHOR, authors[i]);
            values.put(NewsContract.NewsEntry.COLUMN_NAME_CONTENT, contents[i]);
            values.put(NewsContract.NewsEntry.COLUMN_NAME_IMAGE, images.getString(i));

            long r = db.insert(
                    NewsContract.NewsEntry.TABLE_NAME,
                    null,
                    values);
        }
    }
}
