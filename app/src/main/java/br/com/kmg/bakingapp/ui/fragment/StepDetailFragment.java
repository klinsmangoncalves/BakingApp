package br.com.kmg.bakingapp.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import br.com.kmg.bakingapp.R;
import br.com.kmg.bakingapp.model.ReceiptStep;

public class StepDetailFragment extends Fragment {

    private final String RECEIPT_STEP_STATE = "receipt_step_key";
    private ReceiptStep receiptStep;

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private Context mContext;

    public StepDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        TextView tvStepDescription = view.findViewById(R.id.tv_step_description);
        mPlayerView = view.findViewById(R.id.playerView);

        if(savedInstanceState != null && savedInstanceState.containsKey(RECEIPT_STEP_STATE)){
            receiptStep = (ReceiptStep) savedInstanceState.getSerializable(RECEIPT_STEP_STATE);
        }

        tvStepDescription.setText(receiptStep.getDescription());
        if(receiptStep.getVideoURL() != null){
            initializePlayer(Uri.parse(receiptStep.getVideoURL()), mContext);
        } else if (receiptStep.getThumbnailURL() != null){
            initializePlayer(Uri.parse(receiptStep.getThumbnailURL()), mContext);
        }

        return view;
    }

    public ReceiptStep getReceiptStep() {
        return receiptStep;
    }

    public void setReceiptStep(ReceiptStep receiptStep) {
        this.receiptStep = receiptStep;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RECEIPT_STEP_STATE, receiptStep);
    }

    private void initializePlayer(Uri mediaUri, Context context) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(context, "BakingAppUdacity");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(context, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

}
