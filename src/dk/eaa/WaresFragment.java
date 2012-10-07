package dk.eaa;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    List<String> tempWares;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tempWares = new ArrayList<String>();
        tempWares.add("A");
        tempWares.add("B");
        tempWares.add("C");
        tempWares.add("D");
        tempWares.add("E");
        tempWares.add("F");
        tempWares.add("G");
        tempWares.add("H");
        tempWares.add("I");
        tempWares.add("J");
        tempWares.add("K");
        tempWares.add("L");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) getActivity().findViewById(R.id.wares_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.wares_list_item_layout, R.id.layout_item_content,tempWares));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wares_list_layout, null);
    }
}
