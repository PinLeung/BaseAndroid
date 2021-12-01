package com.youyu.common.router

/**
 *@author YouYu
 *@date 2021/3/11
 *@description
 */
object
PathConfig {

    //ARouter路劲至少需要两级
    //主页
    const val TO_MAIN = "/home/main"


    const val TO_PAY_MONEY = "/home/pay_money"

    //登录
    const val TO_LOGIN = "/login/main"


    //登录
    const val TO_LOGIN_NEW = "$TO_LOGIN/new_user"
    //登录
    const val TO_GUIDE = "$TO_LOGIN/guide"

    //输入验证码
    const val TO_INPUT_VERIFY_CODE = "$TO_LOGIN/input_verify_code"

    //输入图片验证码
    const val TO_INPUT_IMG_VERIFY_CODE = "$TO_LOGIN/input_img_verify_code"

    //忘记密码
    const val TO_FORGET_PASSWORD = "$TO_LOGIN/forget_password"



    //设置密码
    const val TO_SET_PASSWORD = "$TO_LOGIN/set_password"

    //微信绑定手机号码
    const val TO_BIND_PHONE = "$TO_LOGIN/wx_bind_phone"

    //隐私政策
    const val TO_PRIVACY = "$TO_LOGIN/privacy"
    //隐私政策
    const val TO_PIC_PRIVACY = "$TO_LOGIN/pic_privacy"

    //用户协议
    const val TO_USER_PRIVACY = "$TO_LOGIN/uesr_privacy"

    //用户协议
    const val TO_UN_REGISTER_PRIVACY = "$TO_LOGIN/unregister_privacy"

    //绑定房屋
    const val TO_BINDING_HOUSE = "$TO_MAIN/binding_house"

    //绑定房屋
    const val TO_BINDING_HOUSE_Face = "$TO_MAIN/binding_house_face"

    //人脸
    const val TO_MINE_FACE = "$TO_MAIN/mine_face"


    //人脸_失败
    const val TO_MINE_FACE_FAIL = "$TO_MAIN/mine_face_fail"

    //人脸_成功
    const val TO_MINE_FACE_SUCCESS = "$TO_MAIN/mine_face_success"

    //人脸_认证中
    const val TO_MINE_FACE_ING = "$TO_MAIN/mine_face_ing"

    //选择小区
    const val TO_SELECT_COMMUNITY = "$TO_MAIN/select_community"

    //首页小区切换选择小区c
    const val TO_HOME_COMMUNITY = "$TO_MAIN/choose_community"

    //我的家
    const val TO_MY_HOME = "$TO_MAIN/my_home"
    //Vr
    const val TO_MY_VR = "$TO_MAIN/my_home_vr"



    //搜索
    const val TO_MY_SEARCH = "$TO_MAIN/my_search"

    //选择城市
    const val TO_SELECT_CITY = "$TO_MAIN/select_city"

    //选择房间号
    const val TO_SELECT_HOUSE_NUM = "$TO_SELECT_COMMUNITY/select_house_num"

    //提交申请
    const val TO_SUBMIT_SUCCESS = "$TO_BINDING_HOUSE/submit_success"

    //人脸识别
    const val TO_FACE_RECOGNITION = "$TO_BINDING_HOUSE/face_recognition"


    //账单缴费
    const val TO_BILL = "$TO_MAIN/bill"
    //账单缴费
    const val TO_BILL_PAYMENT = "$TO_MAIN/bill_payment"

    //物业缴费
    const val TO_PROPERTY_PAYMENT = "$TO_BILL_PAYMENT/property_payment"

    //物业费预缴
    const val TO_PROPERTY_PREPAYMENT = "$TO_PROPERTY_PAYMENT/prepayment"

    //缴费结果
    const val TO_PAY_RESULT = "$TO_BILL_PAYMENT/pay_result"

    //停车缴费
    const val TO_PARKING_PAYMENT = "$TO_BILL_PAYMENT/parking_payment"

    //报事报修
    const val TO_REPORT_REPAIR = "$TO_MAIN/report_repair"

    //报事报修-费用
    const val TO_REPORT_FEES = "$TO_MAIN/report_fees"

    //访客通行
    const val TO_VISITOR = "$TO_MAIN/visitor"

    //智慧教育
    const val TO_WIT_EDU = "$TO_MAIN/wit_edu"

    //智慧点餐
    const val TO_WIT_DINNER = "$TO_MAIN/wit_dinner"


    //投票决策
    const val TO_MAIN_VOTE = "$TO_MAIN/vote"

    //车位信息
    const val TO_MAIN_PARK = "$TO_MAIN/park"
    //车位信息详情
    const val TO_MAIN_PARK_DETAIL = "$TO_MAIN/park_detail"

    //投票决策
    const val TO_MAIN_VOTE_DETAIL = "$TO_MAIN/vote_detail"
    //智慧养老
    const val TO_WIT_OLD = "$TO_MAIN/wit_old"

    //智慧政务
    const val TO_WIT_NEWS = "$TO_MAIN/wit_news"

    //访客记录
    const val TO_VISITOR_RECORD = "$TO_MAIN/visitor_record"
    //访客记录详情
    const val TO_VISITOR_RECORD_DETAIL = "$TO_MAIN/visitor_record_detail"

    //访客记录详情
    const val TO_VISITOR_RECORD_DETAIL_QRCODE = "$TO_MAIN/visitor_record_detail_qrcode"


    //活动详情
    const val TO_ACT_DETAIL = "$TO_MAIN/act_detail"

    //我参与的获得
    const val TO_ACT_MINE = "$TO_MAIN/act_mine"

    //报事报修记录
    const val TO_REPORT_REPAIR_RECORD = "$TO_REPORT_REPAIR/report_repair_record"

    //添加报事报修
    const val TO_ADD_REPORT_REPAIR = "$TO_REPORT_REPAIR/add_report_repair"

    //选择报事报修
    const val TO_CHOOSE_REPORT_TYPE = "$TO_REPORT_REPAIR/choose_report_type"

    //报事详情
    const val TO_REPORT_DETAIL = "$TO_REPORT_REPAIR/report_detail"

    //发布评价
    const val TO_PUBLISH_RATING = "$TO_REPORT_REPAIR/publish_rating"

    //设置中心
    const val TO_SETTING = "$TO_MAIN/setting"

    //账号注销
    const val TO_UN_REGISTER = "$TO_MAIN/setting_un_register"

    //账号注销-输入验证码
    const val TO_UN_REGISTER_CODE = "$TO_MAIN/setting_un_register_code"

    //账号注销
    const val TO_UN_REGISTER_FAIL = "$TO_MAIN/setting_un_register_fail"

    //更换手机号
    const val TO_MODIFY_PHONE = "$TO_SETTING/modify_phone"

    //输入新手机号
    const val TO_INPUT_NEW_PHONE = "$TO_MODIFY_PHONE/input_new_phone"

    //更换成功
    const val TO_MODIFY_SUCCESS = "$TO_MODIFY_PHONE/modify_success"

    //发送验证码
    const val TO_INPUT_NEW_VERIFY_CODE = "$TO_MODIFY_PHONE/input_new_verify_code"

    //修改密码
    const val TO_MODIFY_PASSWORD = "$TO_SETTING/modify_password"

    //设置密码
    const val TO_MODIFY_SET_PASSWORD = "$TO_MODIFY_PASSWORD/modify_set_password"

    //关于我们
    const val TO_ABOUT_ME = "$TO_MAIN/about_me"

    //通知公告详情
    const val TO_NOTICE = "$TO_MAIN/notice"


    //通知公告详情
    const val TO_NOTICE_DETAIL = "$TO_MAIN/notice_detail"

    //反馈
    const val TO_FEEDBACK = "$TO_ABOUT_ME/feedback"

    //常见问题
    const val TO_COMMON_PROBLEM = "$TO_ABOUT_ME/common_problem"

    //常见问题详情
    const val TO_COMMON_PROBLEM_DET = "$TO_ABOUT_ME/common_problem_detail"

    //满意度调查
    const val TO_SATISFACTION_SURVEY = "$TO_ABOUT_ME/satisfaction_survey"

    //个人信息
    const val TO_PERSONAL_INFO = "$TO_MAIN/personal_info"

    //修改昵称
    const val TO_MODIFY_NICK_NAME = "$TO_PERSONAL_INFO/modify_nick_name"

    //我的房屋
    const val TO_MY_HOUSE = "$TO_MAIN/my_house"

    //我的房屋
    const val TO_MY_ORDER = "$TO_MAIN/my_order"

    //消息中心
    const val TO_MY_MSG= "$TO_MAIN/my_msg"

    //消息中心列表
    const val TO_MY_MSG_List= "$TO_MAIN/my_msg_list"
    //开门
    const val OPEN_DOOR = "$TO_MAIN/open_door"

    //开门
    const val HOME_MALL = "$TO_MAIN/mall"

    //我的收货地址
    const val TO_MY_ADDRESS = "$TO_MAIN/my_address"
    //我的收货地址
    const val TO_MY_ADD_ADDRESS = "$TO_MAIN/my_add_address"

    //添加地址
    const val TO_ADD_ADDRESS = "$TO_MY_ADDRESS/add_address"

    //审核详情
    const val TO_VERIFY_DETAIL = "$TO_MY_HOUSE/verify_detail"

    //房屋详情
    const val TO_HOUSE_DETAIL = "$TO_MY_HOUSE/house_detail"

    //权限详情
    const val TO_PERMS_DETAIL = "$TO_MY_HOUSE/perms_detail"

    //装修须知
    const val TO_DECORATION_NOTICE = "$TO_MAIN/decoration_notice"

    //装修申请
    const val TO_DECORATION_APPLY = "$TO_DECORATION_NOTICE/decoration_apply"

    //装修申请添加
    const val TO_DECORATION_APPLY_ADD = "$TO_DECORATION_APPLY/decoration_apply_add"

    //装修申请详情
    const val TO_DECORATION_DETAIL = "$TO_DECORATION_APPLY/decoration_detail"

    //申请验收
    const val TO_DECORATION_APPLY_ACCEPTANCE = "$TO_DECORATION_APPLY/decoration_apply_acceptance"

    //装修施工证
    const val TO_DECORATION_CONSTRUCTION_PERMIT =
        "$TO_DECORATION_APPLY/decoration_construction_permit"

    //聊天
    const val TO_CHACT = "$TO_MAIN/chact"

    //家政服务
    const val TO_HOUSE_SERVICE = "$TO_MAIN/house_service"

    //健康档案
    const val TO_HEALTHY = "$TO_MAIN/healthy"

    //健康档案_列表
    const val TO_HEALTHY_LIST = "$TO_MAIN/healthy_List"

    //健康档案_详情
    const val TO_HEALTHY_DETAIL = "$TO_MAIN/healthy_detail"

    //健康档案
    const val TO_HEALTHY_ADD = "$TO_MAIN/healthy_add"


    //家政详情
    const val TO_HOUSE_SERVICE_DETAIL = "$TO_HOUSE_SERVICE/house_service_detail"

    //确认订单
    const val TO_HOUSE_SERVICE_CONFIRM = "$TO_HOUSE_SERVICE/house_service_confirm"

}