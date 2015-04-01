package com.example.alert;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BackFragment extends Fragment {

	private Button fb_head;
	private Button fb_neck;
	private Button fb_back;
	private Button fb_bum;
	private Button fb_leftarm;
	private Button fb_rightarm;
	private Button fb_leftleg;
	private Button fb_rightleg;
	
	private String[] backParts = {"head", "neck","back","bum","leftarm","rightarm","leftleg","rightleg"};

	Body_Model bm;
	
	RelativeLayout mRelativeLayout;
	
	public static final int NUMBER_OF_COLOURS = 3;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.back_layout, container, false);

		if (savedInstanceState != null) {

			for (String i : backParts) {
				changePicture("back", i, false);
			}
		}
		
		fb_head = (Button) mRelativeLayout.findViewById(R.id.back_head);
		fb_neck = (Button) mRelativeLayout.findViewById(R.id.back_neck);
		fb_back = (Button) mRelativeLayout.findViewById(R.id.back_back);
		fb_bum = (Button) mRelativeLayout.findViewById(R.id.back_bum);
		fb_leftarm = (Button) mRelativeLayout.findViewById(R.id.back_leftarm);
		fb_rightarm = (Button) mRelativeLayout.findViewById(R.id.back_rightarm);
		fb_leftleg = (Button) mRelativeLayout.findViewById(R.id.back_leftleg);
		fb_rightleg = (Button) mRelativeLayout.findViewById(R.id.back_rightleg);

		fb_head.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "head", true);
			}
		});
		fb_neck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "neck", true);
			}
		});
		fb_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "back", true);
			}
		});
		fb_bum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "bum", true);
			}
		});
		fb_leftarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "leftarm", true);
			}
		});
		fb_rightarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "rightarm", true);

			}
		});
		fb_leftleg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "leftleg", true);

			}
		});
		fb_rightleg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("back", "rightleg", true);

			}
		});
		return mRelativeLayout;
	}
	
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		bm = (Body_Model) activity;
	}
	
	public void changePicture(String view, String part,  Boolean UpdateCount) {

		int count = 2;

		if (part.equals("head")) {
			count = bm.getCountBackhead();
		} else if (part.equals("neck")) {
			count = bm.getCountBackneck();
		} else if (part.equals("back")) {
			count = bm.getCountback();
		} else if (part.equals("bum")) {
			count = bm.getCountbum();
		} else if (part.equals("leftarm")) {
			count = bm.getCountBackleftarm();
		} else if (part.equals("rightarm")) {
			count = bm.getCountBackrightarm();
		} else if (part.equals("leftleg")) {
			count = bm.getCountBackleftleg();
		} else if (part.equals("rightleg")) {
			count = bm.getCountBackrightleg();
		}
		
		if (UpdateCount) {
			if (count < NUMBER_OF_COLOURS){
				count++;
			} else {
				count = 1;
			}

			if (part.equals("head")) {
				bm.setCountBackhead(count);
			} else if (part.equals("neck")) {
				bm.setCountBackneck(count);	
			} else if (part.equals("back")) {
				bm.setCountback(count);
			} else if (part.equals("bum")) {
				bm.setCountbum(count);
			} else if (part.equals("leftarm")) {
				bm.setCountBackleftarm(count);
			} else if (part.equals("rightarm")) {
				bm.setCountBackrightarm(count);
			} else if (part.equals("leftleg")) {
				bm.setCountBackleftleg(count);
			} else if (part.equals("rightleg")) {
				bm.setCountBackrightleg(count);
			}
		
		}

		int whiteId = mRelativeLayout.getResources().getIdentifier(
				view + "_white_" + part, "id",
				mRelativeLayout.getContext().getPackageName());
		int amberId = mRelativeLayout.getResources().getIdentifier(
				view + "_amber_" + part, "id",
				mRelativeLayout.getContext().getPackageName());
		int redId = mRelativeLayout.getResources().getIdentifier(
				view + "_red_" + part, "id",
				mRelativeLayout.getContext().getPackageName());
		
		

		ImageView white = (ImageView) mRelativeLayout.findViewById(whiteId);
		ImageView amber = (ImageView) mRelativeLayout.findViewById(amberId);
		ImageView red = (ImageView) mRelativeLayout.findViewById(redId);

		switch (count) {
		case 1:
			white.setVisibility(View.VISIBLE);
			amber.setVisibility(View.INVISIBLE);
			red.setVisibility(View.INVISIBLE);
			break;
		case 2:
			white.setVisibility(View.INVISIBLE);
			amber.setVisibility(View.VISIBLE);
			red.setVisibility(View.INVISIBLE);
			break;
		case 3:
			white.setVisibility(View.INVISIBLE);
			amber.setVisibility(View.INVISIBLE);
			red.setVisibility(View.VISIBLE);
			break;
		default:

		}
		
	}
}
