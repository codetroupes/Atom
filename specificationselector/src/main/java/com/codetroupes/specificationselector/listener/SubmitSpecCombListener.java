package com.codetroupes.specificationselector.listener;

import com.codetroupes.specificationselector.bean.SpecBean;
import com.codetroupes.specificationselector.sku.ProductModel;

import java.util.List;


/**
 * Created by wongxd on 2018/2/8.
 * <p>
 * 点击规格选择界面的 确定 按钮，提交所选规格组合的监听
 */

public interface SubmitSpecCombListener {

    /**
     * @param combBean          所选规格的信息  {@link com.codetroupes.specificationselector.bean.SpecBean.CombsBean}
     * @param num               选择的数量
     * @param statusRestoreList 用来展示一个选中的组合，在 {@link com.codetroupes.specificationselector.SpecSelectFragment} 的 showDialog() 中会用到
     */
    void onSubmit(SpecBean.CombsBean combBean, int num, List<ProductModel.AttributesEntity.AttributeMembersEntity> statusRestoreList);
}
