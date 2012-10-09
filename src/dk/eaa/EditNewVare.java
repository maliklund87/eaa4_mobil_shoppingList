package dk.eaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import dk.eaa.db.DatabaseHelper;

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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Object o = bundle.get("dk.eaa.ware_key");
            setWareList((ArrayList<Ware>) o);
        }

        if (ware == null) {
            ware = new ArrayList<Ware>();
        }
//        this.ware = new ArrayList<Ware>();
        //Ware w1 = new Ware("vare1","kg", 2, 200);
        //Ware w2 = new Ware("vare2", "ls", 7, 300);
        //this.ware.add(w1);
        //this.ware.add(w2);

        if (ware.size() != index)
        {
            currentWare = this.ware.get(index);
            insertData();
        }

    }

    private void saveData()
    {
        DatabaseHelper db = new DatabaseHelper(this);

        db.updateWares(getWareList());
        getWareList().clear();
        finish();
    }

    private void insertData()
    {
        ((EditText) findViewById(R.id.name)).setText(currentWare.getName());
        ((EditText) findViewById(R.id.unit)).setText(currentWare.getUnit());
        ((EditText) findViewById(R.id.amount)).setText(currentWare.getAmount()+"");
        ((EditText) findViewById(R.id.price)).setText(currentWare.getPrice() + "");
    }

    private void createNewWare()
    {

        String name = ((EditText) findViewById(R.id.name)).getText() + "";
        String unit = ((EditText) findViewById(R.id.unit)).getText() + "";
        Double amount = Double.parseDouble(((EditText) findViewById(R.id.amount)).getText() + "");
        Double price = Double.parseDouble(((EditText) findViewById(R.id.price)).getText() + "");

        Ware new_ware = new Ware(name, unit, amount, price);

        DatabaseHelper db = new DatabaseHelper(this);

        db.saveWare(new_ware);
        db.close();
        finish();
    }

    private void saveChange()
    {
        boolean changed = false;
        Ware ware = getCurrentWare();
        String name = ((EditText) findViewById(R.id.name)).getText() + "";
        String unit = ((EditText) findViewById(R.id.unit)).getText() + "";
        Double amount = Double.parseDouble(((EditText) findViewById(R.id.amount)).getText() + "");
        Double price = Double.parseDouble(((EditText) findViewById(R.id.price)).getText() + "");

        if (ware.getName().compareTo(name) != 0)
        {
            ware.setName(name);
            changed = true;
        }

        if (ware.getUnit().compareTo(unit) != 0)
        {
            ware.setUnit(unit);
            changed = true;
        }

        if (ware.getAmount() != amount)
        {
            ware.setAmount(amount);
            changed = true;
        }

        if (ware.getPrice() != price)
        {
            ware.setPrice(price);
            changed = true;
        }

        if (changed)
        {
            setCurrentWare(ware);
            getWareList().set(getIndex(), ware);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (getWareList().size() <= 0)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.edit_new_vare_menu_new, menu);
            return true;
        }
        else
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.new_edit_menu, menu);
            return true;
        }

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case  R.id.menuNew:

                createNewWare();

                return true;

            case R.id.menuforword:
                if ((index + 1) < this.ware.size() )
                {
                    saveChange();

                    this.index ++;
                    this.currentWare = this.ware.get(this.index);
                    insertData();

                    if (Build.VERSION.SDK_INT >=11)
                    {
                        invalidateOptionsMenu();
                    }
                }

                return true;

            case R.id.menuBackword:

                if (index -1 < this.ware.size() && index -1 >= 0)
                {
                    saveChange();
                    this.index --;
                    this.currentWare = this.ware.get(this.index);
                    insertData();

                    if (Build.VERSION.SDK_INT >=11)
                    {
                        invalidateOptionsMenu();
                    }
                }
                return true;

            case R.id.menu_save:

                saveData();

                return true;
        }
        return false;
    }

    public ArrayList<Ware> getWareList()
    {
        return this.ware;
    }

    public void setWareList(ArrayList<Ware> ware)
    {
        this.ware = ware;
    }

    public Ware getCurrentWare()
    {
        return this.currentWare;
    }

    public void setCurrentWare(Ware ware)
    {
        this.currentWare = ware;
    }

    public int getIndex()
    {
        return this.index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
}
