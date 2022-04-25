package iir4.pfa.data.sqlite_nav.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import iir4.pfa.data.sqlite_nav.R;
import iir4.pfa.data.sqlite_nav.adapter.MachineAdapter;
import iir4.pfa.data.sqlite_nav.bean.Machine;
import iir4.pfa.data.sqlite_nav.bean.Salle;
import iir4.pfa.data.sqlite_nav.service.MachineService;
import iir4.pfa.data.sqlite_nav.ui.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class MachinesBySalleFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MachinesBySalleFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MachinesBySalleFragment newInstance(int columnCount) {
        MachinesBySalleFragment fragment = new MachinesBySalleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    MachineService ms;
    RecyclerView recyclerView;
    MachineAdapter adapter;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines_by_salle_list, container, false);
         id = Integer.parseInt(getArguments().getString("id"));
         ms = new MachineService(getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MachineAdapter(ms.findMachines(id),getFragmentManager());
            new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final AlertDialog dialog = new AlertDialog.Builder(getContext())
                    .setTitle("VOUS VOULEZ SUPPRIMER ?")
                    .setPositiveButton("OUI", null)
                    .setNegativeButton("NON", null)
                    .show();

            Button OUI = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button NON = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

            OUI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Machine machine = ms.findById(Integer.parseInt( ((TextView)viewHolder.itemView.findViewById(R.id.m_item_id)).getText().toString()));
                    ms.delete(machine);
                    adapter = new MachineAdapter(ms.findMachines(id), getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(getContext(), "MACHINE SUPPRIMEE AVEC SUCCEE", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }
            });

            NON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter = new MachineAdapter(ms.findMachines(id), getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
            });

        }
    };
}