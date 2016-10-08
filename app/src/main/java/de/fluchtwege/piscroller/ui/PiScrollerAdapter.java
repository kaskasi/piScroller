package de.fluchtwege.piscroller.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.fluchtwege.piscroller.BR;
import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.databinding.PiDigitBinding;
import de.fluchtwege.piscroller.viewmodel.PiScrollerViewModel;

public class PiScrollerAdapter extends RecyclerView.Adapter<PiScrollerAdapter.PiScrollerViewHolder> {

    private final PiScrollerViewModel piScrollerViewModel;

    public PiScrollerAdapter(PiScrollerViewModel piScrollerViewModel) {
        this.piScrollerViewModel = piScrollerViewModel;
    }

    @Override
    public PiScrollerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PiDigitBinding binding = DataBindingUtil.inflate(inflater, R.layout.pi_digit, parent, true);
        PiScrollerViewHolder viewHolder = new PiScrollerViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PiScrollerViewHolder holder, int position) {
        String piDigitsForPosition = piScrollerViewModel.getDigitOfPi(position);
        holder.binding.setVariable(BR.piDigit, piDigitsForPosition);
    }

    @Override
    public int getItemCount() {
        return piScrollerViewModel.getNumberOfDigitsOfPi();
    }

    public class PiScrollerViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public PiScrollerViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
