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

public class FrontFragment extends Fragment {

	private Button fb_head;
	private Button fb_neck;
	private Button fb_chest;
	private Button fb_groin;
	private Button fb_leftarm;
	private Button fb_rightarm;
	private Button fb_leftleg;
	private Button fb_rightleg;

	private String[] frontParts = { "head", "neck", "chest", "groin",
			"leftarm", "rightarm", "leftleg", "rightleg" };

	Body_Model bm;

	RelativeLayout mRelativeLayout;

	public static final int NUMBER_OF_COLOURS = 3;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRelativeLayout = (RelativeLayout) inflater.inflate(
				R.layout.front_layout, container, false);
		
		mRelativeLayout.getBackground().setAlpha(50);
		
		if (savedInstanceState != null) {
			for (String i : frontParts) {

				changePicture("front", i, false);

			}
		}

		fb_head = (Button) mRelativeLayout.findViewById(R.id.front_head);
		fb_neck = (Button) mRelativeLayout.findViewById(R.id.front_neck);
		fb_chest = (Button) mRelativeLayout.findViewById(R.id.front_chest);
		fb_groin = (Button) mRelativeLayout.findViewById(R.id.front_groin);
		fb_leftarm = (Button) mRelativeLayout.findViewById(R.id.front_leftarm);
		fb_rightarm = (Button) mRelativeLayout.findViewById(R.id.front_rightarm);
		fb_leftleg = (Button) mRelativeLayout.findViewById(R.id.front_leftleg);
		fb_rightleg = (Button) mRelativeLayout.findViewById(R.id.front_rightleg);

		fb_head.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "head", true);
			}
		});
		fb_neck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "neck", true);
			}
		});
		fb_chest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "chest", true);
			}
		});
		fb_groin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "groin", true);
			}
		});
		fb_leftarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "leftarm", true);
			}
		});
		fb_rightarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "rightarm", true);

			}
		});
		fb_leftleg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "leftleg", true);

			}
		});
		fb_rightleg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePicture("front", "rightleg", true);

			}
		});
		return mRelativeLayout;
	}
	
	public void onResume() {
		super.onResume();
		
		for (String i : frontParts) {
		changePicture("front", i, false);
		}
	};
	
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		bm = (Body_Model) activity;
	}

	public void changePicture(String view, String part, Boolean UpdateCount) {

		int count = 2;

		if (part.equals("head")) {
			count = bm.getCounthead();
		} else if (part.equals("neck")) {
			count = bm.getCountneck();
		} else if (part.equals("chest")) {
			count = bm.getCountchest();
		} else if (part.equals("groin")) {
			count = bm.getCountgroin();
		} else if (part.equals("leftarm")) {
			count = bm.getCountleftarm();
		} else if (part.equals("rightarm")) {
			count = bm.getCountrightarm();
		} else if (part.equals("leftleg")) {
			count = bm.getCountleftleg();
		} else if (part.equals("rightleg")) {
			count = bm.getCountrightleg();
		}

		if (UpdateCount) {
			if (count < NUMBER_OF_COLOURS) {
				count++;
			} else {
				count = 1;
			}

			if (part.equals("head")) {
				bm.setCounthead(count);
			} else if (part.equals("neck")) {
				bm.setCountneck(count);
			} else if (part.equals("chest")) {
				bm.setCountchest(count);
			} else if (part.equals("groin")) {
				bm.setCountgroin(count);
			} else if (part.equals("leftarm")) {
				bm.setCountleftarm(count);
			} else if (part.equals("rightarm")) {
				bm.setCountrightarm(count);
			} else if (part.equals("leftleg")) {
				bm.setCountleftleg(count);
			} else if (part.equals("rightleg")) {
				bm.setCountrightleg(count);
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
