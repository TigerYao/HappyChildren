package com.dachuwang.software.yaohu.happyeducation.util;


/**
 * 描述：常量.
 */
public class ConstantUtils {
    /**
     * 调试模式为true  上线模式为false
     */
    public static final boolean DEBUG = true;

    /**
     * 显示Toast.
     */
    public static final int SHOW_TOAST = 0;

    /**
     * 显示进度框.
     */
    public static final int SHOW_PROGRESS = 1;

    /**
     * 删除进度框.
     */
    public static final int REMOVE_PROGRESS = 2;

    /**
     * 删除底部进度框.
     */
    public static final int REMOVE_DIALOGBOTTOM = 3;

    /**
     * 删除中间进度框.
     */
    public static final int REMOVE_DIALOGCENTER = 4;

    /**
     * Dialog的类型.
     */
    public static final int DIALOGPROGRESS = 0;

    /**
     * The Constant DIALOGBOTTOM.
     */
    public static final int DIALOGBOTTOM = 1;

    /**
     * The Constant DIALOGCENTER.
     */
    public static final int DIALOGCENTER = 2;
    /**
     * 登录请求request
     */
    public static final int REQUEST_CODE_LOGIN = 100;
    /**
     * onFail
     */
    public static final int FAIL = 0;
    //customer service phonenumber
    public static final String SERVICE_PHONENUM = "4008199491";
    //myorders
    public static final String UNCOMFIRED="1";
    public static final String UNRECEIVED="11";
    public static final String COMPLETED="21";
    public static final String CLOSED="31";

    //buy align
    public static final String BUY_ALIGN_PARENT="1";
    public static final String BUY_ALIGN_CHILDREN="2";

    //----------------------自定义事件ID -----------------------------//

    /***
     * 事件有两种
     * 一种是只统计点击计数点击，只是简单的统计点击量，调用StatisticsUtil.onItemClick(Context,ID)即可
     * 另外一种是点击事件，可以添加hashMap对不同类别进行统计，调用onItemClick(Context, ID, map, time)
     */

    /**点击使用使用首页关注按钮，只统计计数事件 调用StatisticsUtil.onItemClick(Context,ID) */
    public static final String USE_MAIN_ATTENTION_CLICK = "useMainAttentionClick";
    /**点击使用使用首页经常购买按钮，只统计计数事件 调用StatisticsUtil.onItemClick(Context,ID) */
    public static final String USE_MAIN_OFFTENBUY_CLICK = "useMainOfftenBuyClick";
    /**点击使用优惠券，只统计计数事件 调用StatisticsUtil.onItemClick(Context,ID) */
    public static final String USE_COUPON_CLICK = "useCouponClick";
    /**点击用户详情，只统计计数事件,调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String PROFILE_CLICK = "profileClick";
    /**点击经常购买按钮，只统计数字,调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String OFTEN_BUY = "oftenBuy";
    /**个人中心页面点击，点击优惠劵统计，只统计数字,调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String PERSONAL_COUPON_CLICK = "personalCouponClick";
    /**修改密码点击，只统计计数事件,调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String CHANGE_PASSWD_CLICK = "changePasswdClick";
    /**登出操作点击，只统计计数事件，调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String LOGOUT_CLICK = "logoutClick";

    /**统计banner点击次数，调用onItemClick(Context, ID, map, time)*/
    public static final String BANNER_CLICK = "bannerClick";
    /**统计分类页面，调用onItemClick(Context, ID, map, time)*/
    public static final String CLASSIFY_STATISTICS = "classifyStatistics";
    /**统计主页搜索，调用onItemClick(Context, ID, map, time)*/
    public static final String MAIN_SEARCH = "mainSearch";
    /**分类搜索页面统计，调用onItemClick(Context, ID, map, time)*/
    public static final String CLASSIFY_SEARCH = "classifySearch";
    /**订单状态点击统计，调用onItemClick(Context, ID, map, time)*/
    public static final String ORDER_STATUS = "orderStatus";
    /**展示详情统计，调用onItemClick(Context, ID, map, time)*/
    public static final String DETAIL_SHOW = "detailShow";
    /**统计从详情类中加入购物车的点击数量，调用onItemClick(Context, ID, map, time)*/
    public static final String DETAIL_ADD_SHOP_MART = "detailAddShopMart";


    /**分类页面浏览时间统计，调用onItemClick(Context, ID, map, time)，填入time值，单位毫秒*/
    public static final String CLASSIFY_TIME = "classifyTime";
    /**购物车浏览时间，调用onItemClick(Context, ID, map, time)，填入time值，单位毫秒*/
    public static final String SHOP_MART_TIME = "shopMartTime";
    /**确认订单页面停留事件，调用onItemClick(Context, ID, map, time)，填入time值，单位毫秒*/
    public static final String ENSURE_ORDER_TIME = "ensureOrderTime";
    /**首页底部点击分类按钮，调用StatisticsUtil.onItemClick(Context,ID)*/
    public static final String MAIN_ACTIVITY_BOTTOM_CLASSIFY_Click = "mainActivityBottomClassifyClick";

    //统计banner点击加入购物车
    public static final String MAIN_ACTIVITY = "mainActivity";
    public static final String SUBJECT_ACTIVITY = "subjectActivity";
    public static final String SUBJECT_SHOP_MART_CLICK = "subjectShopMartClick";
    /**点击首页分类按钮事件*/
    public static final String MAIN_ACTIVITY_CLASSIFY_CLICK = "mainActivityClassifyClick";
    /**分类页面添加到购物车*/
    public static final String CLASSIFY_ADD_SHOP_MART = "classifyAddShopMart";
    /**在搜索页面加入购物车*/
    public static final String SEARCH_ADD_SHOP_MART = "searchAddShopMart";
    /**首页点击推荐任意一个*/
    public static final String RECOMMENT_CLICK = "recommendClick";
    /**从经常购买中加入购物车*/
    public static final String OFTEN_BUY_ADD_SHOP_MART = "oftenBuyAddShopMart";
    /**从关注页面中加入购物车*/
    public static final String ATTENTION_ADD_SHOP_MART = "attentionAddShopcart";
}
