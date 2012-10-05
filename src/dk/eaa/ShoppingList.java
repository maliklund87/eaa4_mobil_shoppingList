package dk.eaa;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;

public class ShoppingList extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab = actionBar.newTab()
                .setText(R.string.tab_shopping_list)
                .setTabListener(new ShoppingListTabListener<ShoplistFragment>(this, "List", ShoplistFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.tab_shopping_wares)
                .setTabListener(new ShoppingListTabListener<WaresFragment>(this, "Wares", WaresFragment.class));
        actionBar.addTab(tab);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void martin(View view)
    {
        Intent intent = new Intent(this, EditNewVare.class);
        startActivity(intent);

    }
}
