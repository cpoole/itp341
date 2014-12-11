package itp431.poole.connor.finals.project.app;
import itp431.poole.connor.finals.project.app.R;
import itp431.poole.connor.finals.project.app.ZBar.ZBarScannerActivity;
import itp431.poole.connor.finals.project.app.ZBar.ZBarConstants;
import itp431.poole.connor.finals.project.app.listeners.RecyclerItemClickListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.dm.zbar.android.scanner.ZBarScannerActivity

public class PlaceholderFragment extends Fragment {
	private RecyclerView recyclerView;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private static final int ZBAR_QR_SCANNER_REQUEST = 1;
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
		recyclerView.addOnItemTouchListener(
				new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
					
					@Override
					public void onItemClick(View view, int position) {
						Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
						startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
						Log.d("itp341","hello from on click");
						
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
		FruitModel fruitsData[] = { 
				new FruitModel("Apple"),
                new FruitModel("Banana"),
                new FruitModel("Orange"),
                new FruitModel("Pineapple"),
                new FruitModel("Mango"),
                new FruitModel("Watermelon"),
                new FruitModel("Strawberry"),
                new FruitModel("Grapes"),
                new FruitModel("Jackfruit"),
                new FruitModel("Carrot"),
                new FruitModel("Fig")
			};
		//set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(fruitsData);
        //set adapter
        recyclerView.setAdapter(mAdapter);
        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
	}
}