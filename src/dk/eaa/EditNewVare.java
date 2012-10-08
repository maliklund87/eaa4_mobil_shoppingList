package dk.eaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Martin R. Bundgaard
 * Date: 05-10-12
 * Time: 08:57
 * To change this template use File | Settings | File Templates.
 */
public class EditNewVare extends Activity
{

    private ArrayList<Ware> ware;
    private Ware currentWare;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.edit_new_vare);


        //this.ware = (ArrayList<Ware>) savedInstanceState.get("ware_key");
        Ware w1 = new Ware("varew1","kg", 2, 200);
        Ware w2 = new Ware("vare2", "ls", 7, 300);
        this.ware.add(w1);
        this.ware.add(w2);

        if (ware.size() != index)
        {
            currentWare = this.ware.get(index);
            insertData();
        }

    }

    private void insertData()
    {
        ((EditText) findViewById(R.id.name)).setText(currentWare.getName());
        ((EditText) findViewById(R.id.unit)).setText(currentWare.getUnit());
        ((EditText) findViewById(R.id.amount)).setText(currentWare.getAmount()+"");
        ((EditText) findViewById(R.id.price)).setText(currentWare.getPrice() + "");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_edit_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menuforword:
                if (ware.get(index + 1) != null)
                    this.index ++;
                    this.currentWare = this.ware.get(this.index);
                    insertData();
                if (Build.VERSION.SDK_INT >=11)
                {
                    invalidateOptionsMenu();
                }
                return true;

            case R.id.menuBackword:
                return true;

        }


        return false;
    }
}
