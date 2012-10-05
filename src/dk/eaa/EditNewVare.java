package dk.eaa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created with IntelliJ IDEA.
 * User: Martin R. Bundgaard
 * Date: 05-10-12
 * Time: 08:57
 * To change this template use File | Settings | File Templates.
 */
public class EditNewVare extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.edit_new_vare);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_edit_menu, menu);
        return true;

    }
}
