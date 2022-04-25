package iir4.pfa.data.sqlite_nav.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iir4.pfa.data.sqlite_nav.R;
import iir4.pfa.data.sqlite_nav.bean.Salle;
import iir4.pfa.data.sqlite_nav.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSalleFragment newInstance(String param1, String param2) {
        AddSalleFragment fragment = new AddSalleFragment();
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

    Button btn_add;
    EditText code, libelle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_salle, container, false);

        btn_add = v.findViewById(R.id.btn_add_salle);
        code = v.findViewById(R.id.salle_code);
        libelle = v.findViewById(R.id.salle_libelle);
        SalleService service = new SalleService(getContext());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code.getText().toString().matches("") ||
                        libelle.getText().toString().matches("")){
                    Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "CHAMPS OBLIGATOIRE", Toast.LENGTH_SHORT).show();

                    return;
                }

                service.add(new Salle(code.getText().toString(),libelle.getText().toString()));
                code.setText("");
                libelle.setText("");
                Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "LA SALLE EST AJOUTEE AVEC SUCCEE", Toast.LENGTH_SHORT).show();


            }
        });


        return v;

    }
}