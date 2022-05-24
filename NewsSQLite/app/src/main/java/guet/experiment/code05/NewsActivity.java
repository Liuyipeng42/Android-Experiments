package guet.experiment.code05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    public static final String NEWS_ID = "news_id";

    private final List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initData();

        NewsAdapter newsAdapter = new NewsAdapter(NewsActivity.this, R.layout.list_item, newsList);

        ListView lvNewsList = findViewById(R.id.lv_news_list);
        lvNewsList.setAdapter(newsAdapter);
    }

    private void initData() {

        MySQLiteOpenHelper myDbHelper = new MySQLiteOpenHelper(NewsActivity.this);
        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                NewsContract.NewsEntry.TABLE_NAME,
                null, null, null, null, null, null
        );

        int titleIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_TITLE);
        int authorIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_AUTHOR);
        int imageIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_IMAGE);
        while (cursor.moveToNext()) {

            News news = new News();
            String title = cursor.getString(titleIndex);
            String author = cursor.getString(authorIndex);
            String image = cursor.getString(imageIndex);
            Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/" + image));

            news.setTitle(title);
            news.setAuthor(author);
            news.setImage(bitmap);
            newsList.add(news);
        }

    }
}
