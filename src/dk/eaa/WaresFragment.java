package dk.eaa;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import dk.eaa.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Malik
 * Date: 04-10-12
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class WaresFragment extends Fragment {

    ArrayList<Ware> wares;
    ArrayList<Ware> selectedWares;

    ListView wareListView;
    ArrayAdapter<Ware> wareListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wares = new ArrayList<Ware>();
        selectedWares = new ArrayList<Ware>();
        setUpWares();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity().getApplicationContext(), "Fetching wares", Toast.LENGTH_LONG);

        wareListAdapter = new ArrayAdapter<Ware>(getActivity(),
                android.R.layout.simple_list_item_1, wares); // should probably be a SimpleCursorAdapter.

        wareListView = (ListView) getActivity().findViewById(R.id.wares_list);
        wareListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        wareListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                wares.get(position).setSelected(checked);
                if (checked) {
                    selectedWares.add(wares.get(position));
                } else {
                    selectedWares.remove(wares.get(position));
                }
                wareListAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.ware_select_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // do something when menu-item clicked
                boolean result = true;
                switch (menuItem.getItemId()) {
                    case R.id.wares_menu_delete:
                        break;
                    case R.id.wares_menu_edit:
                        // start Martin's Edit activity
                        Intent intent = new Intent(getActivity(), EditNewVare.class);
                        intent.putExtra("dk.eaa.ware_key", selectedWares);
                        startActivity(intent);
                        break;
                    default:
                        result = false;
                        break;
                }
                return result;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                for (Ware w : wares) {
                    w.setSelected(false);
                }
                selectedWares.clear();
            }
        });

        wareListView.setAdapter(wareListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wares_list_layout, null);
    }

    //Don't use unless you know how
    private void setUpWares(){
        Toast.makeText(getActivity().getApplicationContext(), "Fetching wares", Toast.LENGTH_LONG);
        DatabaseHelper db = new DatabaseHelper(getActivity());
//        db.onCreate(db.getWritableDatabase());
        Cursor cur = db.getAllWares();
        while(cur.moveToNext()){
            int waresId = cur.getInt(cur.getColumnIndex("_id"));

            Ware ware = db.getWare(waresId); // stupid, I know. Get list to show cursor in stead.
            Toast.makeText(getActivity().getApplicationContext(), ware.toString(), Toast.LENGTH_LONG);
            ware.setId(waresId);
            wares.add(ware);
//            cur.moveToNext();
        }
        db.close();
    }
}
