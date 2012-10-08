package dk.eaa;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dk.eaa.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Malik
 * Date: 04-10-12
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class WaresFragment extends Fragment {

    ArrayList<Ware> wares;
    ListView wareList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wares = new ArrayList<Ware>();
        setUpWares();

    }

    @Override
    public void onStart(){
        super.onStart();
        wares = new ArrayList<Ware>();
        wareList = (ListView) getActivity().findViewById(R.id.wares_list);


        setUpWares();

        ArrayAdapter<Ware> adapter = new ArrayAdapter<Ware>(getActivity(),
                android.R.layout.simple_list_item_1, wares);




    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // ListView listView = (ListView) getActivity().findViewById(R.id.wares_list);
       // listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.wares_list_item_layout, R.id.layout_item_content,tempWares));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wares_list_layout, null);
    }

    //Don't use unleash you know how
    private void setUpWares(){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Cursor cur = db.getAllWares();

        while(cur.moveToNext()){
            int waresId = cur.getInt(1);
            String waresName = cur.getString(2);
            double waresPrice = cur.getDouble(3);
            String wareUnit = cur.getString(4);
            double waresAmount = cur.getDouble(5);

            Ware ware = new Ware(waresName);
            ware.setPrice(waresPrice);
            ware.setAmount(waresAmount);
            ware.setId(waresId);
            ware.setUnit(wareUnit);
            wares.add(ware);



        }
    }
}
