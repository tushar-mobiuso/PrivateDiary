package com.mobiuso.mine.ui;


import com.mobiuso.mine.privatediary.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

public class FragmentAudio extends Fragment {
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_audionotes, container, false);
       return rootView;
   }
}