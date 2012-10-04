package dk.eaa;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Malik
 * Date: 04-10-12
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public class WaresFragmentTemp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wares_temp_activity_layout);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.wares_fragment_slot, new WaresFragment());
        ft.commit();
    }
}
