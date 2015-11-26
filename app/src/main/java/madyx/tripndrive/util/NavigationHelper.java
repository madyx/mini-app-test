package madyx.tripndrive.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import madyx.tripndrive.R;
import madyx.tripndrive.ui.fragment.SearchCarFormFragment;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class NavigationHelper {

    public static void displayFragment(FragmentManager fragmentManager, int container, Fragment fragment, boolean shoudAddToBackStack) {

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(container, fragment);

        if (shoudAddToBackStack) {
            ft.addToBackStack(null);
        }

        ft.commit();
    }
}
