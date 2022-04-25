package iir4.pfa.data.sqlite_nav.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import iir4.pfa.data.sqlite_nav.R;
import iir4.pfa.data.sqlite_nav.bean.Salle;
import iir4.pfa.data.sqlite_nav.databinding.FragmentSalleItemBinding;
import iir4.pfa.data.sqlite_nav.ui.EditMachineFragment;
import iir4.pfa.data.sqlite_nav.ui.EditSalleFragment;
import iir4.pfa.data.sqlite_nav.ui.MachinesBySalleFragment;
import iir4.pfa.data.sqlite_nav.ui.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SalleAdapter extends RecyclerView.Adapter<SalleAdapter.ViewHolder> {

    private final List<Salle> salles;
    private final androidx.fragment.app.FragmentManager fragmentManager;

    public SalleAdapter(List<Salle> items,androidx.fragment.app.FragmentManager fragmentManager) {
        salles = items;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSalleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.salle = salles.get(position);
        holder.id.setText(salles.get(position).getId()+"");
        holder.code.setText(salles.get(position).getCode());
        holder.libelle.setText(salles.get(position).getLibelle());
    }

    @Override
    public int getItemCount() {
        return salles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView code;
        public final TextView libelle;
        public final TextView id;
        public final ConstraintLayout layout;
        public Salle salle;


        public ViewHolder(FragmentSalleItemBinding binding) {
            super(binding.getRoot());
            id = binding.sItemId;
            code = binding.sItemCode;
            libelle = binding.sItemLibelle;
            layout = binding.salleContainer;

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog dialog = new AlertDialog.Builder(layout.getContext())
                            .setPositiveButton("Modifier", null)
                            .setNegativeButton("Liste dea machines", null)
                            .setNeutralButton("Annuler",null)
                            .show();

                    Button modifier = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button machines = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    Button annuler = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);

                    annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    modifier.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadEditSalle(fragmentManager,id.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    machines.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadMachinesBySalle(fragmentManager,id.getText().toString());
                            dialog.dismiss();
                        }
                    });

                }
            });
        }

        @Override
        public String toString() {
            return "";
        }

        private void loadEditSalle(androidx.fragment.app.FragmentManager fragmentManager, String id){
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Fragment fragment = new EditSalleFragment();
            fragment.setArguments(bundle);
            FragmentManager fm = fragmentManager;
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_salle, fragment);
            ft.commit();
        }

        private void loadMachinesBySalle(androidx.fragment.app.FragmentManager fragmentManager, String id){
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Fragment fragment = new MachinesBySalleFragment();
            fragment.setArguments(bundle);
            FragmentManager fm = fragmentManager;
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_salle, fragment);
            ft.commit();
        }
    }
}