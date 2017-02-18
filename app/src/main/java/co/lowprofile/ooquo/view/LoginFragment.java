package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.LoginCommand;
import co.lowprofile.ooquo.event.LoginResponseEvent;
import co.lowprofile.ooquo.model.Login;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class LoginFragment extends AbstractFragment
{
    private static final String TAG = "LoginFragment";

    private EditText emailInput;
    private EditText passwordInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        view.findViewById(R.id.loginButton).setOnClickListener(this);
        view.findViewById(R.id.signUpButton).setOnClickListener(this);

        emailInput = (EditText)view.findViewById(R.id.emailInput);
        passwordInput = (EditText)view.findViewById(R.id.passwordInput);

        if(OoquoApplication.isMocking)
        {
            emailInput.setText(getText(R.string.mock_email).toString());
            passwordInput.setText(getText(R.string.mock_password).toString());
        }

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.loginButton).setOnClickListener(null);
        getView().findViewById(R.id.signUpButton).setOnClickListener(null);

        emailInput = null;
        passwordInput = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.loginButton:
                onLoginClick();
                break;

            case R.id.signUpButton:
                onSignUpClick();
                break;
        }
    }

    private void onHelpClick()
    {
        showFragment(PhotoEditFragment.class);
    }

    private void onLoginClick()
    {
        Login login = new Login();
        login.setEmail(emailInput.getText().toString());
        login.setPassword(passwordInput.getText().toString());

        new LoginCommand(login).executeInBackground();
    }

    public void onEventMainThread(LoginResponseEvent event)
    {
        ServiceResponse<Boolean> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
            return;
        }

        showFragment(FeedFragment.class);
        //showFragment(SearchBookFragment.class);
        //showFragment(NewBookFragment.class);
        //showFragment(SearchBookFragment.class);
        //showFragment(FeedFragment.class);
    }

    private void onSignUpClick()
    {
        showFragment(SignUpFragment.class, SignUpFragment.createBundle(SignUpFragment.Mode.SIGN_UP));
    }
}
