package co.lowprofile.ooquo.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.AddAuthorCommand;
import co.lowprofile.ooquo.event.AddAuthorResponseEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.model.AppModel;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class NewAuthorFragment extends AbstractFragment
{
    private EditText firstNameInput;
    private EditText lastNameInput;
    private ImageButton photoButton;
    private Button saveButton;

    private Bitmap bitmap = null;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_new_author;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.new_author).toString());

        saveButton = (Button)view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        firstNameInput = (EditText)view.findViewById(R.id.firstNameInput);
        lastNameInput = (EditText)view.findViewById(R.id.lastNameInput);

        photoButton = (ImageButton)view.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        saveButton.setOnClickListener(null);
        saveButton = null;

        firstNameInput = null;
        lastNameInput = null;

        photoButton.setImageBitmap(null);
        photoButton.setOnClickListener(null);
        photoButton = null;

        bitmap = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        AppModel appModel = OoquoApplication.getAppModel();

        if(appModel.getBitmap() != null)
        {
            appModel.setAuthorBitmap(appModel.getBitmap());
            appModel.setBitmap(null);
        }

        if(appModel.getAuthorBitmap() != null)
        {
            bitmap = appModel.getAuthorBitmap();
            photoButton.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void onSaveAuthorClick()
    {
        String error = getError();
        if(error != null)
        {
            showAlert(error);
        }
        else
        {
            NewAuthor newAuthor = new NewAuthor();
            newAuthor.setFirstName(firstNameInput.getText().toString());
            newAuthor.setLastName(lastNameInput.getText().toString());

            new AddAuthorCommand(newAuthor, bitmap).executeInBackground();
        }
    }

    private void onPhotoClick()
    {
        EventBus.getDefault().post(new TakePhotoEvent());
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.saveButton:
                onSaveAuthorClick();
                break;

            case R.id.photoButton:
                onPhotoClick();
                break;
        }
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

    public void onEventMainThread(AddAuthorResponseEvent event)
    {
        ServiceResponse<Author> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
        }
        else
        {
            OoquoApplication.getAppModel().setAuthorBitmap(null);
            OoquoApplication.getAppModel().setSelectedAuthor(response.getData());

            onBackClick();
            onBackClick();
        }
    }

    @Override
    protected void onBackClick()
    {
        OoquoApplication.getAppModel().setAuthorBitmap(null);
        super.onBackClick();
    }
}
