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
import iir4.pfa.data.sqlite_nav.bean.Machine;
import iir4.pfa.data.sqlite_nav.bean.Salle;
import iir4.pfa.data.sqlite_nav.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditSalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditSalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditSalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditSalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditSalleFragment newInstance(String param1, String param2) {
        EditSalleFragment fragment = new EditSalleFragment();
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


    Button btn_edit;
    EditText code, libelle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_salle, container, false);

        btn_edit= v.findViewById(R.id.btn_edit_salle);
        code = v.findViewById(R.id.edit_salle_code);
        libelle = v.findViewById(R.id.edit_salle_libelle);
        SalleService service = new SalleService(getContext());

        int id = Integer.parseInt(getArguments().getString("id"));

        Salle salle = service.findById(id);

        code.setText(salle.getCode());
        libelle.setText(salle.getLibelle());

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code.getText().toString().matches("") ||
                        libelle.getText().toString().matches("")){
                    Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "CHAMPS OBLIGATOIRE", Toast.LENGTH_SHORT).show();

                    return;
                }

                service.update(new Salle(id,code.getText().toString(),libelle.getText().toString()));
              //  code.setText("");
               // libelle.setText("");
                Toast.makeText(getParentFragment().getActivity().getApplicationContext(), "LA SALLE EST MODIFIEE AVEC SUCCEE", Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }
}