package br.com.kmg.bakingapp;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.kmg.bakingapp.model.Receipt;
import br.com.kmg.bakingapp.ui.MainActivity;
import br.com.kmg.bakingapp.ui.MasterReceiptList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MasterDetailViewTest {

    @Rule
    public ActivityTestRule<MasterReceiptList> mainActivityActivityTestRule
            = new ActivityTestRule<MasterReceiptList>(MasterReceiptList.class){
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.RECEIPT_EXTRA, new Receipt());
            return super.getActivityIntent();
        }
    };

    @Test
    public void testReceiptListFragment(){
        onView(withId(R.id.master_list_fragment)).check(matches(isDisplayed()));
    }
}
