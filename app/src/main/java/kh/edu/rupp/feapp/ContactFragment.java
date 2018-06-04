package kh.edu.rupp.feapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * FEApp
 * Created by leapkh on 4/23/18.
 */
public class ContactFragment extends Fragment {

    private TextView txtRuppAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_contact, container, false);
        return fragmentView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtRuppAddress = view.findViewById(R.id.txt_rupp_contact);
        txtRuppAddress.setText("Rupp Address");
    }

}
