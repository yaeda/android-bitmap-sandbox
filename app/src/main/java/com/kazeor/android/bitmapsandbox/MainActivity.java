package com.kazeor.android.bitmapsandbox;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FILE_SELECTION = 1;

    private ImageView imageOriginalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageOriginalView = (ImageView)findViewById(R.id.image_original);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                requestFileSelection();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_FILE_SELECTION && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                showImage(data.getData());
            }
        }
    }

    private void requestFileSelection() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(intent, REQUEST_CODE_FILE_SELECTION);
    }

    private void showImage(Uri imageUri) {
        if (imageUri == null) {
            return;
        }

        Picasso.with(getApplicationContext())
                .load(imageUri)
                .resize(640, 480)
                .centerCrop()
                .into(imageOriginalView);
    }

}
