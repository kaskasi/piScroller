package de.fluchtwege.piscroller.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.databinding.PiQuizBinding;
import de.fluchtwege.piscroller.model.PiQuiz;
import de.fluchtwege.piscroller.viewmodel.PiQuizViewModel;

public class PiQuizDialog extends DialogFragment {

	public static final String KEY_QUIZ_STATE = "quiz_state";

	private PiQuizViewModel viewModel;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(KEY_QUIZ_STATE, viewModel.getPiQuiz());
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		PiQuizBinding binding = DataBindingUtil.inflate(inflater, R.layout.pi_quiz, container, false);
		PiQuiz piQuiz = retainPiQuiz(savedInstanceState);
		viewModel = new PiQuizViewModel(piQuiz);
		binding.setPiQuiz(viewModel);
		return binding.getRoot();
	}

	private PiQuiz retainPiQuiz(@Nullable Bundle savedInstanceState) {
		PiQuiz piQuiz;
		if (savedInstanceState != null) {
			piQuiz = (PiQuiz) savedInstanceState.getSerializable(KEY_QUIZ_STATE);
		} else {
			piQuiz = new PiQuiz(0);
		}
		return piQuiz;
	}
}
