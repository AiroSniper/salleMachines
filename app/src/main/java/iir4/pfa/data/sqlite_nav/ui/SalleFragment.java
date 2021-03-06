package iir4.pfa.data.sqlite_nav.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iir4.pfa.data.sqlite_nav.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalleFragment newInstance(String param1, String param2) {
        SalleFragment fragment = new SalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }
    Button newSalle, listeSalle;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_salle, container, false);
        newSalle = v.findViewById(R.id.new_salle);
        listeSalle = v.findViewById(R.id.list_salle);

        loadListSalles();

        newSalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAddSalle();
            }
        });

        listeSalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListSalles();
            }
        });
        return v;
    }

    private void loadAddSalle(){
        fragment = new AddSalleFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_salle, fragment);
        ft.commit();
    }

    private void loadListSalles(){
        fragment = new SallesFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_salle, fragment);
        ft.commit();
    }

}