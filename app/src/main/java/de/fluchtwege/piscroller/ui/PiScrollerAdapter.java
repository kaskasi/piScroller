package de.fluchtwege.piscroller.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.databinding.PiDigitBinding;
import de.fluchtwege.piscroller.viewmodel.PiDigitViewModel;
import de.fluchtwege.piscroller.viewmodel.PiScrollerViewModel;

public class PiScrollerAdapter extends RecyclerView.Adapter<PiScrollerAdapter.PiDigitViewHolder> {

    private final PiScrollerViewModel piScrollerViewModel;

    public PiScrollerAdapter(PiScrollerViewModel piScrollerViewModel) {
        this.piScrollerViewModel = piScrollerViewModel;
    }

    @Override
    public PiDigitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PiDigitBinding binding = DataBindingUtil.inflate(inflater, R.layout.pi_digit, parent, true);
        PiDigitViewHolder viewHolder = new PiDigitViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PiDigitViewHolder holder, int position) {
        PiDigitViewModel digit = new PiDigitViewModel(position);
        holder.binding.setViewModel(digit);
    }

    @Override
    public int getItemCount() {
        return piScrollerViewModel.getNumberOfDigitsOfPi();
    }

    public class PiDigitViewHolder extends RecyclerView.ViewHolder {

        private PiDigitBinding binding;

        public PiDigitViewHolder(PiDigitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
