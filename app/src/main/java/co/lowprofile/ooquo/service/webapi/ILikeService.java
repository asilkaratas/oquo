package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface ILikeService
{
    ServiceResponse<Boolean> like(int quoteId);
    ServiceResponse<Boolean> dislike(int quoteId);
    ServiceResponse<Integer> getLikeCount(int quoteId);
}
