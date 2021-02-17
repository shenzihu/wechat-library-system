// pages/register/register.js
Page({
  data: {
    errorStatus: 0,
    departments: ["公司领导", "办公室（党委办公室）", "发展策划部", "财务资产部", "安全监察部（保卫部）", "设备管理部", "营销部（农电工作部）", "科技部", "建设部", "互联网部", "物资部（招投标管理中心）", "集体企业管理办公室", "党委宣传部（对外联络部）", "审计部", "经济法律部（体改办）", "党委组织部（人事董事部）", "人力资源部（社保中心）", "离退休工作部", "后勤工作部", "党委党建部（思想政治工作部、机关党委办公室、团委）", "监察部（纪委办公室、巡察办）", "电力调度控制中心", "工会", "企协分会", "巡察组", "电力交易中心", "综合服务中心"]
  },
  onLoad: function (option) {
    let errorStatus = option.errorStatus;
    if (errorStatus == 1) {
      this.setData({
        errorStatus: 1
      });
    }
  },
  formSubmit: function (e) {
    let phone = e.detail.value.phone;
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    if (!myreg.test(phone)) {
      wx.showToast({
        title: '手机号有误！',
        icon: 'failed',
        duration: 1500
      })
      return false;
    }
    let username = e.detail.value.username;
    let realName = e.detail.value.realName;
    let password = e.detail.value.password;
    let password2 = e.detail.value.password2;
    let check = e.detail.value.agree;

    if (check.length == 0) {
      wx.showModal({
        title: '提示',
        content: '请先阅读服务协议',
        success: function (res) {
          return;
        }
      })
      return;
    }
    let depart;
    try {
      depart = this.data.departments[parseInt(e.detail.value.depart)];
    } catch (error) {
      wx.redirectTo({
        url: '/pages/register/register?errorStatus=1'
      })
    }


    //执行注册
    wx.request({
      url: getApp().globalData.url + 'api-user-register',
      data: {
        phone: phone,
        username: username,
        realName: realName,
        password: password,
        password2: password2,
        department: depart
      },
      method: 'GET',
      success: function (res) {
        if (res.data == 1) {
          //注册成功
          wx.redirectTo({
            url: '/pages/login/login'
          })
        } else {
          //注册失败
          wx.redirectTo({
            url: '/pages/register/register?errorStatus=1'
          })
        }
      }
    });
  },
  navBtnOnClick: () => {
    wx.redirectTo({
      url: '/pages/login/login'
    })
  },
  toAgreement: () => {
    wx.navigateTo({
      url: '../service-agreement/service-agreement',
    })
  },

  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    })
  },
})