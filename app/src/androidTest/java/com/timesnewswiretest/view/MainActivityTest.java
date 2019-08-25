package com.timesnewswiretest.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.timesnewswiretest.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import Model.ApiResponseParser;
import Model.NewsModel;
import connection.ApiClient;
import connection.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Utils;
import view.NewsDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    List<NewsModel> newsList= null;
    @Rule
    public ActivityTestRule<NewsDetailsActivity> mActivityTestRule = new ActivityTestRule<>(NewsDetailsActivity.class);

    @Test
    public void mainActivityTest() {

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

    }

    @Test
    public void ApilengthTest(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponseParser> call = apiService.getNewsDetails(Utils.API_KEY);
        call.enqueue(new Callback<ApiResponseParser>() {
            @Override
            public void onResponse(Call<ApiResponseParser> call, Response<ApiResponseParser> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    newsList = response.body().getResults();
                    assertEquals(20, newsList.size());


                }
            }

            @Override
            public void onFailure(Call<ApiResponseParser> call, Throwable t) {

            }


        });

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
