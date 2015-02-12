package com.example.mysearch;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener ,SearchView.OnCloseListener, Button.OnClickListener  {
	
	private SearchView mSearchView;
	private TextView mStatusView;
	private Button mOpenButton;
	private Button mCloseButton;
	    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        mStatusView = (TextView) findViewById(R.id.status_text);

        mOpenButton = (Button) findViewById(R.id.open_button);
        mCloseButton = (Button) findViewById(R.id.close_button);
        mOpenButton.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);
        
	}
	

    @SuppressLint("NewApi")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }
    
    @SuppressLint({ "NewApi", "NewApi" })
	private void setupSearchView(MenuItem searchItem) {
        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            mSearchView.setSearchableInfo(info);
        }
        mSearchView.setOnQueryTextListener(this);
    }


	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		mStatusView.setText("Query = " + arg0);
		return false;
	}


	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		mStatusView.setText("Query = " + query + " : submitted");
		return false;
	}
	
    public boolean onClose() {
        mStatusView.setText("Closed!");
        return false;
    }
	
    protected boolean isAlwaysExpanded() {
        return false;
    }


	@SuppressLint("NewApi")
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
        if (arg0 == mCloseButton) {
            mSearchView.setIconified(true);
        } else if (arg0 == mOpenButton) {
            mSearchView.setIconified(false);
        }
	}
    
}