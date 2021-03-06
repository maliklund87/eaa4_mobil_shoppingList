package dk.eaa;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Browser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dk.eaa.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 10/5/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShoplistFragment extends Fragment{

    private ArrayList<Ware> wares;
    private ListView wareList;
    private TextView priceText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart(){
        super.onStart();
        wares = new ArrayList<Ware>();
        wareList = (ListView) getActivity().findViewById(R.id.itemListView);
        priceText = (TextView) getActivity().findViewById(R.id.priceTxtPriceId);

        setUpWares();

        ArrayAdapter<Ware> adapter = new ArrayAdapter<Ware>(getActivity(),
                android.R.layout.simple_list_item_1, wares);

        double price = 0;
        for(Ware w : wares){
            price = w.getPrice() * w.getAmount();
        }

        priceText.setText(Double.toString(price));



    }


    //Don't use unleash you know how
    private void setUpWares(){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Cursor cur = db.getShoppingList();

        while(cur.moveToNext()){

            int waresId = cur.getInt(cur.getColumnIndex("shoppingListWareId"));
            wares.add(db.getWare(waresId));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.shoplistfragment, null);

    }
}
