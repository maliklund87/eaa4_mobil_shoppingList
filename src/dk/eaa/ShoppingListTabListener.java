package dk.eaa;

import android.*;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created with IntelliJ IDEA.
 * User: Malik
 * Date: 05-10-12
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */
public class ShoppingListTabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment fragment;
    private final Activity parentActivity;
    private final String tag;
    private final Class<T> fragmentClass;

    public ShoppingListTabListener(Activity parentActivity, String tag, Class<T> fragmentClass) {
        this.parentActivity = parentActivity;
        this.tag = tag;
        this.fragmentClass = fragmentClass;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragment == null) {
            fragment = Fragment.instantiate(parentActivity, fragmentClass.getName());
            fragmentTransaction.add(android.R.id.content, fragment, tag);
        } else {
            fragmentTransaction.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragment != null) {
            fragmentTransaction.detach(fragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
