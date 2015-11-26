package madyx.tripndrive.ui.fragment;

import android.support.v4.app.Fragment;

import com.octo.android.robospice.Jackson2GoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class BaseFragment extends Fragment {

    // ------------------------------
    // ATTRIBUTES
    // ------------------------------

    private SpiceManager spiceManager = new SpiceManager(Jackson2GoogleHttpClientSpiceService.class);

    // ------------------------------
    // LIFE CYCLE
    // ------------------------------

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }

    // ----------------------------------
    // PROTECTED
    // ----------------------------------

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
