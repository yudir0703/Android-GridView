package com.yudi.test2.app.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yudi.test2.R
import com.yudi.test2.api.models.explore.DataExplore
import com.yudi.test2.app.common.setImage

/**
 * @author Yudi Rahmat
 */

class ExploreAdapter(layoutResId: Int, data: List<DataExplore?>?) :
    BaseQuickAdapter<DataExplore, BaseViewHolder>( layoutResId, data as MutableList<DataExplore>?) {

    override fun convert(helper: BaseViewHolder, item: DataExplore?) {
        setImage(helper.getView(R.id.iv_preview), item?.fileUrl.toString(), mContext)

        helper.setVisible(R.id.iv_label, true)
        when {
//            item?.fileType.equals("video") -> helper.setImageResource(R.id.iv_label, R.drawable.ic_video_outline)
            item?.fileType.equals("image_list") -> {
                var imgUrl: String? = item?.imageList?.get(0)
//                helper.setImageResource(R.id.iv_label, R.drawable.ic_multiple_pic_outline)
                setImage(helper.getView(R.id.iv_preview), imgUrl, mContext)
            }
            else -> helper.setVisible(R.id.iv_label, false)
        }

        helper.addOnClickListener(R.id.llRoot)
    }

}
