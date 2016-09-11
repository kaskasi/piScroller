package de.fluchtwege.piscroller.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import de.fluchtwege.piscroller.viewmodel.PiScrollerViewModel;
import de.fluchtwege.piscroller.R;

public class PiScrollerActivity extends AppCompatActivity {

    public static final String PI_QUIZ_DIALOG = "pi_quiz_dialog";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pi_scroller);

        PiScrollerViewModel piScrollerViewModel = new PiScrollerViewModel();
        PiScrollerAdapter piScrollerAdapter = new PiScrollerAdapter(piScrollerViewModel);

        RecyclerView piDigits = (RecyclerView) findViewById(R.id.piScroller);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false);
        piDigits.setLayoutManager(layoutManager);
        piDigits.setAdapter(piScrollerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scroller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_quiz) {
            showQuiz();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showQuiz() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        PiQuizDialog quizDialog = new PiQuizDialog();
        quizDialog.show(fragmentManager, PI_QUIZ_DIALOG);
    }
}
