package challenge.juanaso.com.albumchallenge.utils.bindingadapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.net.Uri
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.widget.SwipeRefreshLayout
import challenge.juanaso.com.albumchallenge.R
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import challenge.juanaso.com.albumchallenge.model.Photo
import challenge.juanaso.com.albumchallenge.model.User
import challenge.juanaso.com.albumchallenge.utils.extension.getParentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

@BindingAdapter("photoUrl")
fun setPhotoUrl(view: ImageView, photo: MutableLiveData<Photo>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        photo?.observe(parentActivity, Observer { value ->
            if (!photo.value?.url!!.endsWith(".svg")) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)
                Glide.with(parentActivity).load(value?.url).apply(options).into(view)
            }else {
                GlideToVectorYou.justLoadImage(parentActivity, Uri.parse(photo.value?.url), view)
            }
        })
    }
}

@BindingAdapter("userName")
fun setUserName(view: TextView, user: MutableLiveData<User>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        user?.observe(parentActivity, Observer { value -> view.text =  value?.fullName})
    }
}

@BindingAdapter("userPhones")
fun setUserPhones(view: TextView, user: MutableLiveData<User>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        user?.observe(parentActivity, Observer { value ->
            val stringBuilder = StringBuilder()
            for (phone in user.value?.phones!!) {
                if (phone.number != null) {
                    stringBuilder.append(phone.numberOrdered())
                    stringBuilder.append(System.getProperty("line.separator"))
                }
            }
            view.text = stringBuilder.toString()
        })
    }
}

@BindingAdapter("userAddresses")
fun setUserAddresses(view: TextView, user: MutableLiveData<User>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        user?.observe(parentActivity, Observer { value ->
            val address = user.value?.addresses!![0]
            val stringBuilder = StringBuilder()
            if (address.home != null) {
                stringBuilder.append(address.getHomeDetailed())
                stringBuilder.append(System.getProperty("line.separator"))
            }
            if (address.work != null)
                stringBuilder.append(address.getWorkDetailed())
            view.text = stringBuilder.toString()
        })
    }
}

@BindingAdapter("photoData")
fun setPhotoData(view: TextView, photo: MutableLiveData<Photo>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        photo?.observe(parentActivity, Observer { value ->
            val stringBuilder = StringBuilder()
            stringBuilder.append("Album id: "+value?.albumId)
            stringBuilder.append(System.getProperty("line.separator"))
            stringBuilder.append("Photo id: "+value?.id)
            stringBuilder.append(System.getProperty("line.separator"))
            stringBuilder.append("photo id: "+value?.title)
            view.text = stringBuilder.toString()
        })
    }
}

@BindingAdapter("collapsingToolbarTittle")
fun setCollapsingToolbarTittle(view:CollapsingToolbarLayout, photo: MutableLiveData<Photo>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity!=null){
        photo?.observe(parentActivity,Observer{ value ->
            view.title = value?.title
        })
    }
}

@BindingAdapter("swipeToRefreshVisibility")
fun setSwipeToRefreshVisibility(view : SwipeRefreshLayout, swipeToRefreshVisibility: MutableLiveData<Boolean>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        swipeToRefreshVisibility?.observe(parentActivity, Observer { value -> view.isRefreshing = value!!})
    }
}

