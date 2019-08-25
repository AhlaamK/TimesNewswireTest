package view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.timesnewswiretest.R;
import com.timesnewswiretest.view.BaseApplication;
import com.timesnewswiretest.view.MainviewAdapter;

import java.lang.reflect.Type;
import java.util.List;

import Model.ApiResponseParser;
import Model.NewsModel;
import connection.ApiClient;
import connection.ApiInterface;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private Type typeList;
    private Gson gson;
    String gsonStr;
    private   SpotsDialog alertDialog;
    BaseApplication baseApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialViews();

        if(Utils.isNetworkConnected(this)){
          //  callApi();

             new AsyncApiCall().execute();
        }else{
            Toast.makeText(getApplicationContext(),"Sorry you don't have internet connection!!",Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
     /*   MenuItem searchViewItem = menu.findItem(R.id.action_search);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
       // searchView.setQueryHint("Search for Product,Brands...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by defaul

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**

                Toast.makeText(getApplicationContext(),"searchvalue :"+query,Toast.LENGTH_LONG).show();

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialViews() {

        typeList = new TypeToken<List<NewsModel>>() {
        }.getType();
        gson = new Gson();
        recyclerView =  findViewById(R.id.recylerview_news);
         alertDialog= new SpotsDialog(MainActivity.this);
        alertDialog.show();
    }

    private void callApi() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponseParser> call = apiService.getNewsDetails(Utils.API_KEY);
        call.enqueue(new Callback<ApiResponseParser>() {
            @Override
            public void onResponse(Call<ApiResponseParser> call, Response<ApiResponseParser> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    List<NewsModel> newsList = response.body().getResults();
                    saveData(newsList);
                } else {
                    showError("Server Problem. Try again!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponseParser> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void saveData(List<NewsModel> newsList) {
        //LIST DATA CONVERT TO GSON STRING

         gsonStr = gson.toJson(newsList, typeList);

        //SAVE IN SHARED-PREFERENCE
        baseApplication.setNewsList(this, gsonStr);

        getSavedData();
    }

    private void getSavedData() {
        //GET SAVED DATA
        String gsonList = baseApplication.getNewsList(this);

        if (!gsonStr.equals("n/a")) {
            //CONVERT TO LIST
            List<NewsModel> newsList = gson.fromJson(gsonList, typeList);

            setupRecycler(newsList);
        } else {
            showError("No saved news to display...!");
        }
    }

    private void setupRecycler(List<NewsModel> dataList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
       recyclerView.setAdapter(new MainviewAdapter(this, dataList));

        assert dataList != null;
        if (dataList.size() > 0) {
            dataVisible();
        } else {
            showError("No News..!");
        }
    }

   private void showError(String message) {

        alertDialog.dismiss();
        recyclerView.setVisibility(View.GONE);

    }

    private void dataVisible() {


        recyclerView.setVisibility(View.VISIBLE);
        alertDialog.dismiss();
    }

    private class AsyncApiCall extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

           callApi();


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            pdLoading.dismiss();
        }

    }
}

