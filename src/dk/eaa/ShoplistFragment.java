package dk.eaa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        ArrayAdapter<Ware> adapter = new ArrayAdapter<Ware>(getActivity(),
                android.R.layout.simple_list_item_1, wares);

        double price = 0;
        for(Ware w : wares){
            price = w.getPrice() * w.getAmount();
        }

        priceText.setText(Double.toString(price));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.shoplistfragment, null);

    }
}
