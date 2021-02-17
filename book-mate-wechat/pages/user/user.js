// pages/user/user.js
Page({
  data: {
    userInfo: {},
    realName: "",
    bookOrderCount: 0,
    user:null
  },
  onShow: function (options) {
    var that = this;
    wx.getUserInfo({
      success: function (res) {
        var userInfo = res.userInfo;
        that.setData({
          userInfo: userInfo
        });
      }
    });
    let u = getApp().globalData.user;
    if (u != null) {
      that.setData({
        user: u
      });
      wx.request({
        url: getApp().globalData.url + 'api-order-book-count/' + u.userId + '/' + 1,
        data: {},
        method: 'GET',
        success: function (res) {
          that.setData({
            bookOrderCount: res.data
          });
        }
      });
      let realName = u.realName;
      that.setData({
        realName: realName
      });
    }


  },
  exitBtnOnClick: function () {
    wx.request({
      url: getApp().globalData.url + 'api-user-exit',
      data: {},
      method: 'GET',
      success: function (res) {}
    })
    getApp().globalData.user = null;
    wx.redirectTo({
      url: '/pages/index/index'
    })
  },
  bookOrderBtn: function () {
    wx.navigateTo({
      url: '/pages/book-order/book-order'
    })
  },
  userBorrowBtn: function () {
    wx.navigateTo({
      url: '/pages/user-order/user-order'
    })
  },
  userISBNBtn: function () {
    wx.navigateTo({
      url: '/pages/user-isbn/user-isbn'
    })
  },
  userSettingBtn: function () {
    wx.navigateTo({
      url: '/pages/user-setting/user-setting'
    })
  }
})