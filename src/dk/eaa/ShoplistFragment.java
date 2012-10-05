package dk.eaa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 10/5/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShoplistFragment extends Fragment{

    private ArrayList<Ware> wares = new ArrayList<Ware>();
    private ListView wareList = (ListView) getActivity().findViewById(R.id.itemListView);

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    public void onStart(){
        ArrayAdapter<Ware> adapter = new ArrayAdapter<Ware>(getActivity(),
                android.R.layout.simple_list_item_1, wares);

        double price = 0;
        for(Ware w : wares){
            price = w.getPrice() * w.getAmount();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.shoplistfragment, container, false);

    }
}
