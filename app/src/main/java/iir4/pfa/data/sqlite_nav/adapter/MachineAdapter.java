package iir4.pfa.data.sqlite_nav.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import iir4.pfa.data.sqlite_nav.R;
import iir4.pfa.data.sqlite_nav.bean.Machine;
import iir4.pfa.data.sqlite_nav.databinding.FragmentMachineItemBinding;
import iir4.pfa.data.sqlite_nav.ui.EditMachineFragment;
import iir4.pfa.data.sqlite_nav.ui.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {

    private final List<Machine> machines;
    private final androidx.fragment.app.FragmentManager fragmentManager;

    public MachineAdapter(List<Machine> items,androidx.fragment.app.FragmentManager fragmentManager) {
        machines = items;
        this.fragmentManager = fragmentManager;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentMachineItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.machine = machines.get(position);
        holder.id.setText(machines.get(position).getId()+"");
        holder.ref.setText(machines.get(position).getRefernce());
        holder.marque.setText(machines.get(position).getMarque());
        holder.salle.setText(machines.get(position).getSalle().getCode());


    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView id;
        public final TextView ref;
        public final TextView marque;
        public final TextView salle;
        public final ConstraintLayout layout;
        public Machine machine;

        public ViewHolder(FragmentMachineItemBinding binding) {
            super(binding.getRoot());
            id = binding.mItemId;
            ref = binding.mItemRef;
            marque = binding.mItemMarque;
            salle = binding.mItemSalle;
            layout = binding.machineContainer;


            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   // Toast.makeText(layout.getContext(),fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName(), Toast.LENGTH_SHORT).show();
                   loadEditMachine(fragmentManager,id.getText().toString());



                }
            });
        }

        @Override
        public String toString() {
            return "";
        }

        private void loadEditMachine(androidx.fragment.app.FragmentManager fragmentManager, String id){
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            Fragment fragment = new EditMachineFragment();
            fragment.setArguments(bundle);
            FragmentManager fm = fragmentManager;
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_machine, fragment);
            ft.commit();
        }
    }


}