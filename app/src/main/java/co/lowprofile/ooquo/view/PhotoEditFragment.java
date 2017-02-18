package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edmodo.cropper.CropImageView;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class PhotoEditFragment extends AbstractFragment
{
    @Override
    protected int getLayout()
    {
        return R.layout.fragment_photo_edit;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.photo_edit).toString());

        final CropImageView cropImageView = (CropImageView) view.findViewById(R.id.imageView);
        cropImageView.setAspectRatio(1, 1);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
