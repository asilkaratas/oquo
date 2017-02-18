package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.SignUpCommand;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.SignUpResponseEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.model.SignUp;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class SignUpFragment extends AbstractFragment
{
    private static final String TAG = "SignUpFragment";

    public enum Mode
    {
        SIGN_UP,
        EDIT_PROFILE
    }

    private static final String MODE_KEY = "mode";
    public static Bundle createBundle(Mode mode)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MODE_KEY, mode);
        return bundle;
    }

    private Mode getMode()
    {
        return (Mode)getArguments().getSerializable(MODE_KEY);
    }

    private Button signUpButton;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText rePasswordInput;
    private ImageButton photoButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getHeaderTitle());

        signUpButton = (Button)view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        firstNameInput = (EditText)view.findViewById(R.id.firstNameInput);
        lastNameInput = (EditText)view.findViewById(R.id.lastNameInput);
        emailInput = (EditText)view.findViewById(R.id.emailInput);
        passwordInput = (EditText)view.findViewById(R.id.passwordInput);
        rePasswordInput = (EditText)view.findViewById(R.id.rePasswordInput);

        photoButton = (ImageButton)view.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(this);

        signUpButton.setText(getSignUpButtonText());
        emailInput.setEnabled(getMode() == Mode.SIGN_UP);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        signUpButton.setOnClickListener(null);
        signUpButton = null;

        firstNameInput = null;
        lastNameInput = null;
        emailInput = null;
        passwordInput = null;
        rePasswordInput = null;

        photoButton.setOnClickListener(null);
        photoButton = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        if(getMode() == Mode.EDIT_PROFILE)
        {
            showUserInfo();
        }

        if(OoquoApplication.getAppModel().getBitmap() != null)
        {
            photoButton.setImageBitmap(OoquoApplication.getAppModel().getBitmap());
        }
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
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.signUpButton:
                onSignUpClick();
                break;

            case R.id.photoButton:
                onPhotoClick();
                break;
        }
    }

    private void onPhotoClick()
    {
        EventBus.getDefault().post(new TakePhotoEvent());
    }

    private void onSignUpClick()
    {
        String error = getError();
        if(error != null)
        {
            showAlert(error);
        }
        else
        {
            signUp();
        }
    }

    private void signUp()
    {
        SignUp signUp = getSignUp();
        new SignUpCommand(signUp).executeInBackground();
    }

    private SignUp getSignUp()
    {
        SignUp signUp = new SignUp();
        signUp.setFirstName(firstNameInput.getText().toString());
        signUp.setLastName(lastNameInput.getText().toString());
        signUp.setEmail(emailInput.getText().toString());
        signUp.setPassword(passwordInput.getText().toString());
        signUp.setConfirmPassword(rePasswordInput.getText().toString());
        signUp.setBitmap(OoquoApplication.getAppModel().getBitmap());

        return signUp;
    }

    private String getError()
    {
        if(firstNameInput.getText().length() < 2)
        {
            return getText(R.string.first_name_error).toString();
        }

        if(lastNameInput.getText().length() < 2)
        {
            return getText(R.string.last_name_error).toString();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches())
        {
            return getText(R.string.email_error).toString();
        }

        if(passwordInput.getText().length() < 5)
        {
            return getText(R.string.password_error).toString();
        }

        if(!passwordInput.getText().toString().equals(rePasswordInput.getText().toString()))
        {
            return getText(R.string.password_match_error).toString();
        }

        return null;
    }

    public void onEventMainThread(SignUpResponseEvent event)
    {
        ServiceResponse<Boolean> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
            return;
        }

        showFragment(FeedFragment.class);
    }

    /*

    private void onUpdateProfile(GenericEvent<GenericResponse<User>> event)
    {
        GenericResponse<User> response = event.getData();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
        }
        else
        {
            showFragment(FeedFragment.class);
        }
    }
    */

    private void showUserInfo()
    {
        User user = OoquoApplication.getAppModel().getCurrentUser();

        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
        //emailInput.setText(user.getEmail());
        //statusInput.setText(user.getStatus());
    }

    private String getHeaderTitle()
    {
        return getMode() == Mode.EDIT_PROFILE ? getText(R.string.edit_profile).toString() :
                                        getText(R.string.sign_up).toString();
    }

    private String getSignUpButtonText()
    {
        return getMode() == Mode.EDIT_PROFILE ? getText(R.string.save).toString() :
                                        getText(R.string.sign_up).toString();
    }


}
