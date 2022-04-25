package iir4.pfa.data.sqlite_nav.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iir4.pfa.data.sqlite_nav.R;
import iir4.pfa.data.sqlite_nav.bean.Machine;
import iir4.pfa.data.sqlite_nav.bean.Salle;
import iir4.pfa.data.sqlite_nav.service.MachineService;
import iir4.pfa.data.sqlite_nav.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMachineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMachineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMachineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMachineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMachineFragment newInstance(String param1, String param2) {
        AddMachineFragment fragment = new AddMachineFragment();
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

    Spinner spinner;
    EditText ref;
    EditText marque;
    Button add_machine;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_machine, container, false);
        spinner = v.findViewById(R.id.s_salle);
        ref = v.findViewById(R.id.m_ref);
        marque = v.findViewById(R.id.m_marque);
        add_machine = v.findViewById(R.id.btn_add_machine);
        SalleService s_service = new SalleService(getContext());
        MachineService m_service = new MachineService(getContext());

        ArrayAdapter<String> adapter;
        List<String> liste= new ArrayList<String>();
        for(Salle salle : s_service.findAll()) {
            liste.add(salle.getCode());
        }
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, liste);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        add_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(marque.getText().toString().matches("") ||
                        ref.getText().toString().matches("")){
                    Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "CHAMPS OBLIGATOIRE", Toast.LENGTH_SHORT).show();

                    return;
                }

                m_service.add(new Machine(marque.getText().toString(),ref.getText().toString(), s_service.findByCode(spinner.getSelectedItem().toString())));
                marque.setText("");
                ref.setText("");
                spinner.setSelection(0);
                Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "LA MACHINE EST AJOUTEE AVEC SUCCEE", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
}