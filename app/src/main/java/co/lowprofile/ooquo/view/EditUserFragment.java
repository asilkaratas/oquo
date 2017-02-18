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

import com.squareup.picasso.Picasso;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.SignUpCommand;
import co.lowprofile.ooquo.controller.UpdateProfileCommand;
import co.lowprofile.ooquo.event.SignUpResponseEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.event.UpdateProfileResponseEvent;
import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.SignUp;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class EditUserFragment extends AbstractFragment
{
    private static final String TAG = "EditUserFragment";

    private Button signUpButton;
    private EditText firstNameInput;
    private EditText lastNameInput;
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

        photoButton = (ImageButton)view.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(this);

        signUpButton.setText(getSignUpButtonText());

        return view;
    }

    @Override
    public void onDestroyView()
    {
        signUpButton.setOnClickListener(null);
        signUpButton = null;

        firstNameInput = null;
        lastNameInput = null;

        photoButton.setOnClickListener(null);
        photoButton = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        showUserInfo();

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
        return R.layout.fragment_edit_user;
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
        updateProfile();
        return;
        /*
        String error = getError();
        if(error != null)
        {
            showAlert(error);
        }
        else
        {
            if(getMode() == Mode.EDIT_PROFILE)
            {
                updateProfile();
            }
            else
            {
                signUp();
            }
        }
        */
    }

    private void updateProfile()
    {
        NewUser newUser = new NewUser();
        newUser.setId(OoquoApplication.getAppModel().getCurrentUser().getId());
        newUser.setFirstName(firstNameInput.getText().toString());
        newUser.setLastName(lastNameInput.getText().toString());

        new UpdateProfileCommand(newUser, OoquoApplication.getAppModel().getBitmap()).executeInBackground();
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

        return null;
    }

    public void onEventMainThread(UpdateProfileResponseEvent event)
    {
        ServiceResponse<Boolean> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
            return;
        }

        onBackClick();
    }



    private void showUserInfo()
    {
        User user = OoquoApplication.getAppModel().getCurrentUser();

        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());

        photoButton.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(photoButton);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(user.getProfilePhoto())
                .into(photoButton);
    }

    private String getHeaderTitle()
    {
        return getText(R.string.edit_profile).toString();
    }

    private String getSignUpButtonText()
    {
        return getText(R.string.save).toString();
    }


}
