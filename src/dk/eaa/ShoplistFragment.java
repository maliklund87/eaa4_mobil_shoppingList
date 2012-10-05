package dk.eaa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 10/5/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShoplistFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.shoplistfragment, container, false);

    }
}
