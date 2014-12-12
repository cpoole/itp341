package itp431.poole.connor.finals.project.app;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import itp341.poole.connor.finals.project.app.parallaxView.ParallaxRecyclerAdapter;
import itp431.poole.connor.finals.project.app.R;
import itp431.poole.connor.finals.project.app.listeners.RecyclerItemClickListener;
import itp431.poole.connor.finals.project.app.models.FruitModel;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MenuFragment extends Fragment {
	private RecyclerView recyclerView;
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MenuFragment newInstance(int sectionNumber) {
		MenuFragment fragment = new MenuFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public MenuFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.menu_fragment, container, false);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
		
		recyclerView.addOnItemTouchListener(
				new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
					
					@Override
					public void onItemClick(View view, int position) {
//						Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
//						intent.putExtra(ZBarConstants.SCAN_MODES,  new int[]{Symbol.QRCODE});
//						startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
//						//Log.d("itp341","hello from on click");
						
					}
				}));
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		
	}
	
	@Override
	public void  onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		final ArrayList<menuItem> foodEntries = menuManager.getMenuEntries();
		//set layoutManger
		LinearLayoutManager manager = new LinearLayoutManager(getActivity());
		manager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(manager);
		recyclerView.setHasFixedSize(true);
		
		
		ParallaxRecyclerAdapter<menuItem> mAdapter = new ParallaxRecyclerAdapter<>(menuManager.getMenuEntries());
		mAdapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods(){
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
				View itemLayoutView = (LayoutInflater.from(getActivity()).inflate(R.layout.item_row, viewGroup, false));
				SimpleViewHolder mViewHolder = new SimpleViewHolder(itemLayoutView);
				return mViewHolder;
			}
			
			@Override
			public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){
				((SimpleViewHolder) viewHolder).foodTitle.setText(foodEntries.get(position).getTitle());
				((SimpleViewHolder) viewHolder).foodDescription.setText(foodEntries.get(position).getDescription());
				float price = foodEntries.get(position).getPrice();
				String strPrice = String.valueOf(price);
				((SimpleViewHolder) viewHolder).foodPrice.setText(strPrice);
				
			}
			@Override
			public int getItemCount() {
				return foodEntries.size();
			}
		});
		
		mAdapter.setParallaxHeader(LayoutInflater.from(getActivity()).inflate(R.layout.parallax_header,recyclerView, false), recyclerView);
//		mAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
//			@Override
//			public void onParallaxScroll(float percentage, float offset, View parallax) {
//				 Drawable c = mToolbar.getBackground();
//				 c.setAlpha(Math.round(percentage * 255));
//				 mToolbar.setBackground(c);
//				
//			}
//		});
        //set adapter
        recyclerView.setAdapter(mAdapter);
        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
	}
	
	static class SimpleViewHolder extends RecyclerView.ViewHolder {
		public TextView foodTitle;
		public TextView foodDescription;
		public TextView foodPrice;
		public SimpleViewHolder(View itemView) {
			super(itemView);
			foodTitle = (TextView) itemView.findViewById(R.id.firstLine);
			foodDescription = (TextView) itemView.findViewById(R.id.secondLine);
			foodPrice = (TextView) itemView.findViewById(R.id.price);
		}
	}
}