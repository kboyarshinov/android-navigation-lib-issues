package bugs.aacnav.crash1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_a.view.*

class FragmentA : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.next_b_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.to_fragment_b)
        )

        view.findViewById<Button>(R.id.next_c_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.to_fragment_c)
        )
    }

}
