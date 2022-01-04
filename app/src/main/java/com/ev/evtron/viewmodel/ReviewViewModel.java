package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.ReviewDataManager;
import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.ReviewViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.ReviewViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.ReviewRequestModel;
import com.ev.evtron.pojo.jsonresponse.ReviewResponseModel;
import com.ev.evtron.utils.PreferenceManager;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ReviewViewModel extends ReviewBaseViewModel implements ReviewViewInterface {
    private ReviewDataManager reviewDataManager;
    private ReviewViewResponseInterface reviewViewResponseInterface;
    private Context mContext;
    private PreferenceManager preferenceManager;

    public ReviewViewModel(Context context, ReviewViewResponseInterface reviewViewResponseInterface) {
        this.mContext = context;
        this.reviewViewResponseInterface = reviewViewResponseInterface;
        this.reviewDataManager = new ReviewDataManager();
    }

    @Override
    public void sendReview() {
        sendReviewToServer();
    }

    private void sendReviewToServer() {
        ReviewRequestModel reviewRequestModel = new ReviewRequestModel();
        reviewRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
        reviewRequestModel.bookingInternalId = getBookingInternalId();
        reviewRequestModel.rating = getRating();
        reviewRequestModel.reviews = getReviews();

        reviewDataManager.callReviewPostEnqueue(reviewRequestModel, new ResponseHandler<ReviewResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(ReviewResponseModel item, String message) {
                reviewViewResponseInterface.getReviewStatus(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                reviewViewResponseInterface.onFailure(errorBody, statusCode);
            }
        });
    }


    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

}
