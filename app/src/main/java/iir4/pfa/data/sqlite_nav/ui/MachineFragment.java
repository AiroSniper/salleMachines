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
 * Use the {@link MachineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MachineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MachineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MachineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MachineFragment newInstance(String param1, String param2) {
        MachineFragment fragment = new MachineFragment();
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

    Button newMachine, listeMachine;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_machine, container, false);

        newMachine = v.findViewById(R.id.new_machine);
        listeMachine = v.findViewById(R.id.list_machine);

        loadListMachines();

        newMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAddMachine();
            }
        });

        listeMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListMachines();
            }
        });

        return v;
    }

    private void loadAddMachine(){
        fragment = new AddMachineFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_machine, fragment);
        ft.commit();
    }

    private void loadListMachines(){
        fragment = new MachinesFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_machine, fragment);
        ft.commit();
    }
}